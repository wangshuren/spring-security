package com.wsr.dao;

import com.wsr.entity.TSysUserRoleRela;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TSysUserRoleRelaDao {
    int deleteByPrimaryKey(TSysUserRoleRela key);

    int insert(TSysUserRoleRela record);

    int insertSelective(TSysUserRoleRela record);

    List<TSysUserRoleRela> getById(@Param("userId")Long userId);
}
