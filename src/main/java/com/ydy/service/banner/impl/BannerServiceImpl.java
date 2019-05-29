/**
 * 
 */
package com.ydy.service.banner.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.constant.SystemConstant;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
import com.ydy.mapper.BannerMapper;
import com.ydy.model.Banner;
import com.ydy.service.banner.BannerService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.ienum.EnumSystem;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 上午8:35:26
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerMapper bannerMapper;

	@Override
	public PageVo<Banner> select(Banner banner, Integer page, Integer size) {
		PageVo<Banner> vo = new PageVo<Banner>(page, size);
		Page<Banner> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<Banner> list = bannerMapper.select(banner);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PageVo<Banner> list(Banner banner, Integer page, Integer size) {
		PageVo<Banner> vo = new PageVo<Banner>(page, size);
		Page<Banner> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "inx asc");
		banner.setUseStatus(SystemConstant.USE_STATUS_ON);
		List<Banner> list = bannerMapper.select(banner);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public Banner saveOrUpdate(Banner banner) {
		if (banner == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(banner);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		// 新增信息
		if (banner.getId() == null) {
			banner.setUseStatus(SystemConstant.USE_STATUS_OFF);
			banner.setCreateTime(new Date());
			bannerMapper.insertSelective(banner);
		} else {// 根据id更新信息
			Banner temp = bannerMapper.selectByPrimaryKey(banner.getId());
			if (temp == null) {
				throw new MyException(EnumSystem.DATA_NOT_FOUND);
			}
			bannerMapper.updateByPrimaryKeySelective(banner);
		}
		return banner;
	}

	@Override
	public BaseVo delete(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Banner temp = bannerMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumSystem.DATA_NOT_FOUND);
		}
		bannerMapper.deleteByPrimaryKey(id);
		return new ResultVo();
	}

	@Override
	public BaseVo status(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Banner temp = bannerMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumSystem.DATA_NOT_FOUND);
		}
		Banner banner = new Banner();
		banner.setId(id);
		if (Objects.equals(SystemConstant.USE_STATUS_ON, temp.getUseStatus())) {
			banner.setUseStatus(SystemConstant.USE_STATUS_OFF);
		} else {
			banner.setUseStatus(SystemConstant.USE_STATUS_ON);
		}
		bannerMapper.updateByPrimaryKeySelective(banner);
		return new ResultVo();
	}

}
