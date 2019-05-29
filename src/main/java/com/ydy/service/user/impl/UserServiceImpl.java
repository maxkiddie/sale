/**
 * 
 */
package com.ydy.service.user.impl;

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
import com.ydy.mapper.UserMapper;
import com.ydy.model.User;
import com.ydy.service.user.UserService;
import com.ydy.utils.Md5Util;
import com.ydy.utils.TokenUtil;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.ienum.EnumUser;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.token.UserTokenVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午7:11:30
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageVo<User> select(User user, Integer page, Integer size) {
		PageVo<User> vo = new PageVo<User>(page, size);
		Page<User> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<User> list = userMapper.select(user);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public UserTokenVo checkUser(User user) {
		User example = new User();
		example.setUsername(user.getUsername());
		if (userMapper.selectCount(example) == 0) {
			log.info("找不到该用户:" + user.getUsername());
			throw new MyException(EnumUser.NOT_FOUND);
		}
		example.setPassword(Md5Util.getMD5(user.getPassword()));
		User temp = userMapper.selectOne(example);
		if (temp == null) {
			log.info("用户登录密码错误:" + user.getUsername());
			throw new MyException(EnumUser.PWD_ERROR);
		}
		if (SystemConstant.USE_STATUS_OFF.equals(temp.getUseStatus())) {
			log.info("用户不可用:" + user.getUsername());
			throw new MyException(EnumUser.CAN_NOT_USE_STATUS);
		}
		String token = TokenUtil.createUserToken(temp);
		UserTokenVo vo = new UserTokenVo();
		vo.setUtoken(token);
		vo.setTimestamp(System.currentTimeMillis());
		return vo;
	}

	@Override
	public User register(User user) {
		User example = new User();
		Map<String, String> validateInfo = ValidateUtil.validateEntity(user);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		example.setUsername(user.getUsername());
		if (userMapper.selectCount(example) > 0) {
			log.info("该用户名已存在:" + user.getUsername());
			throw new MyException(EnumUser.USERNAME_EXSIT);
		}
		user.setPassword(Md5Util.getMD5(user.getPassword()));
		user.setRegTime(new Date());
		user.setUseStatus(SystemConstant.USE_STATUS_ON);
		userMapper.insertSelective(user);
		return user;
	}

	@Override
	public User selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		User temp = userMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumUser.DATA_NOT_FOUND);
		}
		return temp;
	}
}
