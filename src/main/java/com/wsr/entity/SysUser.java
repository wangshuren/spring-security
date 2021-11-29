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

import java.util.Date;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2021-04-23
 */
@Data
public class SysUser{

    private static final long serialVersionUID=1L;

    /**
     * 系统用户ID
     */
    private Long sysUserId;

    /**
     * 登录用户名
     */
    private String loginUsername;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 性别 0-未知, 1-男, 2-女
     */
    private Byte sex;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 员工编号
     */
    private String userNo;

    /**
     * 是否超管（超管拥有全部权限） 0-否 1-是
     */
    private Byte isAdmin;

    /**
     * 状态 0-停用 1-启用
     */
    private Byte state;

    /**
     * 所属系统： MGR-运营平台, MCH-商户中心
     */
    private String sysType;

    /**
     * 所属商户ID / 0(平台)
     */
    private String belongInfoId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
