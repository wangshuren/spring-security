package com.wsr.controller;

import cn.hutool.core.codec.Base64;
import com.wsr.entity.base.BaseResp;
import com.wsr.entity.base.ResponseUtil;
import com.wsr.entity.request.UserValidateRequestParam;
import com.wsr.entity.response.UserTokenResponse;
import com.wsr.secruity.JeeUserDetails;
import com.wsr.service.AuthService;
import com.wsr.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：wangsr
 * @description：
 * @date ：Created in 2021/11/26 14:12
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/te")
    @PreAuthorize("hasAuthority( 'SSSS' )") // 会校验用户是否含有相应权限
    public void test() {
        System.out.println("999");
    }

    /** 用户信息认证 获取iToken  **/
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public BaseResp<UserTokenResponse> validate(@RequestBody UserValidateRequestParam param) throws Exception {


        String username = Base64.decodeStr(param.getUsername());
        String password = Base64.decodeStr(param.getPassword());
//        String vercode = Base64.decodeStr(getValStringRequired("vc"));	//验证码 vercode,  已做base64处理
//        String vercodeToken = Base64.decodeStr(getValStringRequired("vt"));	//验证码token, vercode token ,  已做base64处理
//
//        String cacheCode = RedisUtil.getString(CS.getCacheKeyImgCode(vercodeToken));
//        if(StringUtils.isEmpty(cacheCode) || !cacheCode.equalsIgnoreCase(vercode)){
//            throw new BizException("验证码有误！");
//        }

        // 返回前端 accessToken
        String accessToken = authService.auth(username, password);

//        // 删除图形验证码缓存数据
//        RedisUtil.del(CS.getCacheKeyImgCode(vercodeToken));
        System.out.println(accessToken);
//        return ApiRes.ok4newJson(CS.ACCESS_TOKEN_NAME, accessToken);
        UserTokenResponse resp = new UserTokenResponse();
        resp.setToken(accessToken);
        return ResponseUtil.success(resp);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public BaseResp logout() throws Exception{

        ITokenService.removeIToken(getCurrentUser().getCacheKey(), getCurrentUser().getSysUser().getSysUserId());
        return ResponseUtil.success();
    }

    /**
     * 获取当前登录账户 可提取到公共类中
     * @return
     */
    protected JeeUserDetails getCurrentUser(){

        return (JeeUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
