package com.wsr.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_sys_user_role_rela
 * @author
 */
@Data
public class TSysUserRoleRela implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private String roleId;

    private static final long serialVersionUID = 1L;
}
