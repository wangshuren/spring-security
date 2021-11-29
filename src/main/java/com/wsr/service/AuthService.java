package com.wsr.service;

import cn.hutool.core.util.IdUtil;
import com.wsr.config.SystemYmlConfig;
import com.wsr.constants.CS;
import com.wsr.dao.TSysEntitlementDao;
import com.wsr.dao.TSysUserRoleRelaDao;
import com.wsr.entity.SysUser;
import com.wsr.entity.TSysUserRoleRela;
import com.wsr.jwt.JWTPayload;
import com.wsr.jwt.JWTUtils;
import com.wsr.secruity.JeeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：wangsr
 * @description：
 * @date ：Created in 2021/11/26 14:48
 */
@Service
public class AuthService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private SystemYmlConfig systemYmlConfig;

    @Autowired
    private TSysUserRoleRelaDao sysUserRoleRelaDao;

    @Autowired
    private TSysEntitlementDao sysEntitlementDao;

    /**
     * 认证
     * **/
    public String auth(String username, String password){

        //1. 生成spring-security usernamePassword类型对象
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);

        //spring-security 自动认证过程；
        // 1. 进入 JeeUserDetailsServiceImpl.loadUserByUsername 获取用户基本信息；
        //2. SS根据UserDetails接口验证是否用户可用；
        //3. 最后返回loadUserByUsername 封装的对象信息；
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(upToken);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
//        catch (JeepayAuthenticationException jex) {
//            throw jex.getBizException() == null ? new BizException(jex.getMessage()) : jex.getBizException();
//        } catch (BadCredentialsException e) {
//            throw new BizException("用户名/密码错误！");
//        } catch (AuthenticationException e) {
//            log.error("AuthenticationException:", e);
//            throw new BizException("认证服务出现异常， 请重试或联系系统管理员！");
//        }

        JeeUserDetails jeeUserDetails = (JeeUserDetails) authentication.getPrincipal();

        //验证通过后 再查询用户角色和权限信息集合

        SysUser sysUser = jeeUserDetails.getSysUser();

//        //非超级管理员 && 不包含左侧菜单 进行错误提示
//        if(sysUser.getIsAdmin() != CS.YES && sysEntitlementMapper.userHasLeftMenu(sysUser.getSysUserId(), CS.SYS_TYPE.MGR) <= 0){
//            throw new BizException("当前用户未分配任何菜单权限，请联系管理员进行分配后再登录！");
//        }

        // 放置权限集合 查询用户所拥有权限，并全部塞入集合-
        jeeUserDetails.setAuthorities(getUserAuthority(sysUser));

        //生成token
        String cacheKey = CS.getCacheKeyToken(sysUser.getSysUserId(), IdUtil.fastUUID());

        //生成iToken 并放置到缓存
        ITokenService.processTokenCache(jeeUserDetails, cacheKey); //处理token 缓存信息

        //将信息放置到Spring-security context中
        UsernamePasswordAuthenticationToken authenticationRest = new UsernamePasswordAuthenticationToken(jeeUserDetails, null, jeeUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationRest);

        //返回JWTToken
        return JWTUtils.generateToken(new JWTPayload(jeeUserDetails), systemYmlConfig.getJwtSecret());
    }

    public List<SimpleGrantedAuthority> getUserAuthority(SysUser sysUser){

        //用户拥有的角色集合  需要以ROLE_ 开头,  用户拥有的权限集合
        List<TSysUserRoleRela> sysUserRoleRelaList = sysUserRoleRelaDao.getById(sysUser.getSysUserId());
        List<String> roleList = new ArrayList<>();
        sysUserRoleRelaList.stream().forEach(r -> roleList.add(r.getRoleId()));

        // 1启用
        List<String> entList = sysEntitlementDao.selectByTypeAndState(sysUser.getSysType(), 1);

        List<SimpleGrantedAuthority> grantedAuthorities = new LinkedList<>();
        roleList.stream().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
        entList.stream().forEach(ent -> grantedAuthorities.add(new SimpleGrantedAuthority(ent)));
        return grantedAuthorities;
    }
}
