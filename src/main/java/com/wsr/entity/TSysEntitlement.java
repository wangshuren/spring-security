package com.wsr.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_sys_entitlement
 * @author 
 */
@Data
public class TSysEntitlement extends TSysEntitlementKey implements Serializable {
    /**
     * 权限名称
     */
    private String entName;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单uri/路由地址
     */
    private String menuUri;

    /**
     * 组件Name（前后端分离使用）
     */
    private String componentName;

    /**
     * 权限类型 ML-左侧显示菜单, MO-其他菜单, PB-页面/按钮
     */
    private String entType;

    /**
     * 快速开始菜单 0-否, 1-是
     */
    private Byte quickJump;

    /**
     * 状态 0-停用, 1-启用
     */
    private Byte state;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 排序字段, 规则：正序
     */
    private Integer entSort;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}