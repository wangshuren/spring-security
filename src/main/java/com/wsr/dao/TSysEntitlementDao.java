package com.wsr.dao;

import com.wsr.entity.TSysEntitlement;
import com.wsr.entity.TSysEntitlementKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TSysEntitlementDao {
    int deleteByPrimaryKey(TSysEntitlementKey key);

    int insert(TSysEntitlement record);

    int insertSelective(TSysEntitlement record);

    TSysEntitlement selectByPrimaryKey(TSysEntitlementKey key);

    int updateByPrimaryKeySelective(TSysEntitlement record);

    int updateByPrimaryKey(TSysEntitlement record);

    List<String> selectByTypeAndState(@Param("sysType")String sysType, @Param("state")Integer state);
}
