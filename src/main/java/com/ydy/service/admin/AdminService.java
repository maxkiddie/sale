package com.ydy.service.admin;

import com.ydy.model.Admin;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.token.AdminTokenVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月17日 下午8:50:56
 */
public interface AdminService {
	PageVo<Admin> select(Admin admin, Integer page, Integer size);

	Admin selectById(Integer id);

	AdminTokenVo checkAdmin(Admin admin);

	Admin saveOrUpdate(Admin admin);

	BaseVo resetPassWord(Integer id);

	BaseVo modifyPassword(Integer id, String username, String password, String newPassword);

	BaseVo updateUseStatus(Integer id);
}
