package com.ydy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ydy.mapper.base.BaseMapper;
import com.ydy.model.Admin;

public interface AdminMapper extends BaseMapper<Admin> {
	List<Admin> selectData(Admin admin);

	Integer exsitUsername(@Param("username") String username);

	Admin checkAdminByUsernameAndPwd(@Param("username") String username, @Param("password") String password);

	Integer selectCountByIdAndUsername(@Param("id") Integer id, @Param("username") String username);

}