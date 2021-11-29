/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wsr.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统用户认证表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2021-04-23
 */
@Data
public class SysUserAuth implements Serializable {


    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private Long authId;

    /**
     * user_id
     */
    private Long userId;

    /**
     * 登录类型  1-昵称 2-手机号 3-邮箱  10-微信  11-QQ 12-支付宝 13-微博
     */
    private Byte identityType;

    /**
     * 认证标识 ( 用户名 | open_id )
     */
    private String identifier;

    /**
     * 密码凭证
     */
    private String credential;

    /**
     * salt
     */
    private String salt;

    /**
     * 所属系统： MGR-运营平台, MCH-商户中心
     */
    private String sysType;



}
