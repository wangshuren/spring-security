package com.wsr.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_sys_entitlement
 * @author 
 */
@Data
public class TSysEntitlementKey implements Serializable {
    /**
     * 权限ID[ENT_功能模块_子模块_操作], eg: ENT_ROLE_LIST_ADD
     */
    private String entId;

    /**
     * 所属系统： MGR-运营平台, MCH-商户中心
     */
    private String sysType;

    private static final long serialVersionUID = 1L;
}