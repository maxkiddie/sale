/**
 * 
 */
package com.ydy.service.personInfo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Objects;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
import com.ydy.mapper.PersonInfoMapper;
import com.ydy.model.PersonInfo;
import com.ydy.service.personInfo.PersonInfoService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.ienum.EnumSystem;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月28日 上午8:40:56
 */
@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoMapper personInfoMapper;

	@Override
	public PageVo<PersonInfo> selectData(PersonInfo personInfo, Integer page, Integer size) {
		PageVo<PersonInfo> vo = new PageVo<PersonInfo>(page, 1000);
		Page<PersonInfo> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<PersonInfo> list = personInfoMapper.select(personInfo);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PersonInfo saveOrUpdate(PersonInfo personInfo) {
		if (personInfo == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(personInfo);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		// 新增信息
		if (personInfo.getId() == null) {
			personInfoMapper.insertSelective(personInfo);
		} else {// 根据id更新信息
			PersonInfo temp = personInfoMapper.selectByPrimaryKey(personInfo.getId());
			if (temp == null) {
				throw new MyException(EnumSystem.DATA_NOT_FOUND);
			}
			if (!Objects.equal(temp.getUserId(), personInfo.getUserId())) {
				throw new MyException(EnumSystem.NO_AUTH);
			}
			personInfoMapper.updateByPrimaryKeySelective(personInfo);
		}
		return personInfo;

	}

	@Override
	public PersonInfo selectById(Long id, Long userId) {
		if (id == null) {
			throw new NullPointerException();
		}
		PersonInfo info = personInfoMapper.selectByPrimaryKey(id);
		if (info != null) {
			if (Objects.equal(info.getUserId(), userId)) {
				return info;
			} else {
				throw new MyException(EnumSystem.NO_AUTH);
			}
		} else {
			throw new MyException(EnumSystem.DATA_NOT_FOUND);
		}
	}

}
