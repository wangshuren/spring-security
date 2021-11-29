package com.wsr.dao;


import com.wsr.entity.SysUserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TSysUserAuthDao {
    int deleteByPrimaryKey(Long authId);

    int insert(SysUserAuth record);

    int insertSelective(SysUserAuth record);

    SysUserAuth selectByPrimaryKey(Long authId);

    int updateByPrimaryKeySelective(SysUserAuth record);

    int updateByPrimaryKey(SysUserAuth record);

    SysUserAuth selectByLogin(@Param("identifier")String identifier, @Param("identifyType")String identifyType, @Param("sysType")String sysType);
}
