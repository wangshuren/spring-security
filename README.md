本项目是参考jeepay项目
https://gitee.com/jeequan/jeepay/


用于学习记录springsecurity
用户名、密码鉴权过程

/validate接口要加入白名单，不走token校验

进入authService.auth方法
authenticationManager.authenticate(upToken)
该方法会进入securit中的AbstractUserDetailsAuthenticationProvider类中的authenticate方法
会先从缓存中查询用户是否存在，
若不存在
user = this.retrieveUser(username, (UsernamePasswordAuthenticationToken)authentication);
会进入到 DaoAuthenticationProvider的retrieveUser方法
这时会跳入JeeUserDetailsServiceImpl implements UserDetailsService（实现了UsreDetailSerice）
类中的loadUserByUsername方法
返回用户信息并继续执行AbstractUserDetailsAuthenticationProvider类中的authenticate方法
this.preAuthenticationChecks.check(user);
this.additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken)authentication);
DaoAuthenticationProvider类中的additionalAuthenticationChecks
if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword()))
这两行会校验输入的密码和数据库中的作比较


密码名称验证成功后会存储token到redis
调接口时会解析token，然后查询redis中的token是否存在（失效）
失效则返回null，鉴权不成功
成功会续约(将token有限期设置为原定有效期)

不允许多用户登录，则登录时查询当前用户是否存在有效token，
存在则移除此有效token，并将此次登录token存储至redis



登出
将对应token移除redis
