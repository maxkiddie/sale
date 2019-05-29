/**
 * 
 */
package com.ydy.service.webInfo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
import com.ydy.mapper.WebInfoMapper;
import com.ydy.model.WebInfo;
import com.ydy.service.webInfo.WebInfoService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.ienum.EnumWebInfo;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 下午4:43:16
 */
@Service
@Transactional
public class WebInfoServiceImpl implements WebInfoService {

	@Autowired
	private WebInfoMapper webInfoMapper;

	@Override
	public PageVo<WebInfo> select(WebInfo webInfo, Integer page, Integer size) {
		PageVo<WebInfo> vo = new PageVo<WebInfo>(page, size);
		Page<WebInfo> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<WebInfo> list = webInfoMapper.select(webInfo);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public WebInfo saveOrUpdate(WebInfo webInfo) {
		if (webInfo == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(webInfo);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		Date now = new Date();
		webInfo.setType(webInfo.getType().toUpperCase());
		// 新增信息
		if (webInfo.getId() == null) {
			webInfo.setCreateTime(now);
			webInfo.setUpdateTime(now);
			webInfoMapper.insertSelective(webInfo);
		} else {// 根据id更新信息
			WebInfo temp = webInfoMapper.selectByPrimaryKey(webInfo.getId());
			if (temp == null) {
				throw new MyException(EnumWebInfo.DATA_NOT_FOUND);
			}
			webInfo.setUpdateTime(now);
			webInfoMapper.updateByPrimaryKeySelective(webInfo);
		}
		return webInfo;
	}

	@Override
	public BaseVo delete(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		WebInfo temp = webInfoMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumWebInfo.DATA_NOT_FOUND);
		}
		webInfoMapper.deleteByPrimaryKey(id);
		return new ResultVo();
	}

	@Override
	public WebInfo selectWebInfoByType(String type) {
		PageHelper.startPage(1, 1);
		WebInfo info = new WebInfo();
		info.setType(type);
		info = webInfoMapper.selectOne(info);
		if (info == null) {
			throw new MyException(EnumWebInfo.DATA_NOT_FOUND);
		}
		return info;
	}

	@Override
	public WebInfo selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		WebInfo temp = webInfoMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumWebInfo.DATA_NOT_FOUND);
		}
		return temp;
	}

}
