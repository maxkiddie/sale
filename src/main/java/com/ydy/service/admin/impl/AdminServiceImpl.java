package com.ydy.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.constant.SystemConstant;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
import com.ydy.mapper.AdminMapper;
import com.ydy.model.Admin;
import com.ydy.service.admin.AdminService;
import com.ydy.utils.Md5Util;
import com.ydy.utils.TokenUtil;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.ienum.EnumAdmin;
import com.ydy.vo.ienum.EnumSystem;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;
import com.ydy.vo.token.AdminTokenVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月17日 下午8:51:30
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	private final static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public PageVo<Admin> selectData(Admin admin, Integer page, Integer size) {
		PageVo<Admin> vo = new PageVo<Admin>(page, size);
		Page<Admin> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<Admin> list = adminMapper.selectData(admin);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public AdminTokenVo checkAdmin(Admin admin) {
		if (adminMapper.exsitUsername(admin.getUsername()) <= 0) {
			log.info("找不到该管理员:" + admin.getUsername());
			throw new MyException(EnumAdmin.NOT_FOUND);
		}
		Admin temp = adminMapper.checkAdminByUsernameAndPwd(admin.getUsername(), Md5Util.getMD5(admin.getPassword()));
		if (temp == null) {
			log.info("管理员登录密码错误:" + admin.getUsername());
			throw new MyException(EnumAdmin.PWD_ERROR);
		}
		if (SystemConstant.USE_STATUS_OFF.equals(temp.getUseStatus())) {
			log.info("管理员不可用:" + admin.getUsername());
			throw new MyException(EnumAdmin.CAN_NOT_USE_STATUS);
		}
		String token = TokenUtil.createAdminToken(temp);
		AdminTokenVo vo = new AdminTokenVo();
		vo.setToken(token);
		vo.setTimestamp(System.currentTimeMillis());
		return vo;
	}

	@Override
	public Admin saveOrUpdate(Admin admin) {
		if (admin == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(admin);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		// 新增管理员信息
		int flag = 0;// 标记
		if (admin.getId() == null) {
			flag = adminMapper.exsitUsername(admin.getUsername());
			if (flag > 0) {// 用户名已存在
				throw new MyException(EnumAdmin.USERNAME_EXSIT);
			}
			admin.setPassword(Md5Util.getMD5(SystemConstant.MANAGER_DEFAULT_PWD));// 设置默认密码
			admin.setUseStatus(SystemConstant.USE_STATUS_ON);
			admin.setRegTime(new Date());
			adminMapper.insertSelective(admin);
			log.info("新增管理员信息成功:" + admin.getUsername());
		} else {// 根据id更新信息
			flag = adminMapper.selectCountByIdAndUsername(admin.getId(), admin.getUsername());
			if (flag <= 0) {// 不存在该id信息
				throw new MyException(EnumAdmin.ADMIN_NOT_FOUND);
			}
			admin.setPassword(null);
			admin.setRegTime(null);
			adminMapper.updateByPrimaryKeySelective(admin);
			log.info("修改管理员信息成功:" + admin.getUsername());
		}
		return admin;
	}

	@Override
	public BaseVo resetPassWord(Integer id) {
		BaseVo vo = null;
		Admin admin = adminMapper.selectByPrimaryKey(id);
		if (admin != null) {
			Admin temp = new Admin();
			temp.setId(id);
			temp.setPassword(Md5Util.getMD5("123456a"));// 设置初始密码
			adminMapper.updateByPrimaryKeySelective(temp);
			vo = new ResultVo(EnumSystem.SUSS);
		} else {
			throw new MyException(EnumAdmin.ADMIN_NOT_FOUND);
		}
		return vo;
	}

	@Override
	public BaseVo updateUseStatus(Integer id) {
		BaseVo vo = null;
		Admin admin = adminMapper.selectByPrimaryKey(id);
		if (admin != null) {
			Admin temp = new Admin();
			temp.setId(id);
			if (SystemConstant.USE_STATUS_ON.equals(admin.getUseStatus())) {
				temp.setUseStatus(SystemConstant.USE_STATUS_OFF);
			} else {
				temp.setUseStatus(SystemConstant.USE_STATUS_ON);
			}
			adminMapper.updateByPrimaryKeySelective(temp);
			vo = new ResultVo(EnumSystem.SUSS);
		} else {
			throw new MyException(EnumAdmin.ADMIN_NOT_FOUND);
		}
		return vo;
	}

	@Override
	public Admin selectById(Integer id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Admin admin = adminMapper.selectByPrimaryKey(id);
		if (admin != null) {
			return admin;
		} else {
			throw new MyException(EnumSystem.DATA_NOT_FOUND);
		}
	}

}