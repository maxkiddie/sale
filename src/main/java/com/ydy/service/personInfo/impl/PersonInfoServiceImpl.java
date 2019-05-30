/**
 * 
 */
package com.ydy.service.personInfo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.exception.BusinessException;
import com.ydy.exception.DataNotFoundException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumPersonInfo;
import com.ydy.ienum.EnumSystem;
import com.ydy.mapper.PersonInfoMapper;
import com.ydy.model.PersonInfo;
import com.ydy.service.personInfo.PersonInfoService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月28日 上午8:40:56
 */
@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {
	private final static Logger log = LoggerFactory.getLogger(PersonInfoServiceImpl.class);
	@Autowired
	private PersonInfoMapper personInfoMapper;

	@Override
	public PageVo<PersonInfo> select(PersonInfo personInfo, Integer page, Integer size) {
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
			personInfo.setCreateTime(new Date());
			personInfoMapper.insertSelective(personInfo);
		} else {// 根据id更新信息
			PersonInfo temp = personInfoMapper.selectByPrimaryKey(personInfo.getId());
			if (temp == null) {
				log.info("找不到人物信息:" + personInfo.getId());
				throw new DataNotFoundException(EnumPersonInfo.DATA_NOT_FOUND);
			}
			if (!Objects.equals(temp.getUserId(), personInfo.getUserId())) {
				log.info(personInfo.getUserId() + "用户没权限操作人物信息:" + personInfo.getId());
				throw new BusinessException(EnumSystem.NO_AUTH);
			}
			personInfoMapper.updateByPrimaryKeySelective(personInfo);
		}
		log.info("保存人物信息成功:" + personInfo.getId());
		return personInfo;

	}

	@Override
	public PersonInfo selectById(Long id, Long userId) {
		if (id == null) {
			throw new NullPointerException();
		}
		PersonInfo info = personInfoMapper.selectByPrimaryKey(id);
		if (info != null) {
			if (Objects.equals(info.getUserId(), userId)) {
				return info;
			} else {
				throw new BusinessException(EnumSystem.NO_AUTH);
			}
		} else {
			log.info("找不到人物信息:" + id);
			throw new DataNotFoundException(EnumPersonInfo.DATA_NOT_FOUND);
		}
	}

	@Override
	public PersonInfo selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		PersonInfo temp = personInfoMapper.selectByPrimaryKey(id);
		if (temp == null) {
			log.info("找不到人物信息:" + id);
			throw new DataNotFoundException(EnumPersonInfo.DATA_NOT_FOUND);
		}
		return temp;
	}

}
