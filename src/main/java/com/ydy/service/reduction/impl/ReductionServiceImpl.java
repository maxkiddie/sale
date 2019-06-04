/**
 * 
 */
package com.ydy.service.reduction.impl;

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
import com.ydy.exception.DataNotFoundException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumReduction;
import com.ydy.mapper.ReductionMapper;
import com.ydy.model.Reduction;
import com.ydy.service.reduction.ReductionService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

import tk.mybatis.mapper.entity.Example;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 下午6:43:33
 */
@Service
@Transactional
public class ReductionServiceImpl implements ReductionService {
	private final static Logger log = LoggerFactory.getLogger(ReductionServiceImpl.class);
	@Autowired
	private ReductionMapper reductionMapper;

	@Override
	public PageVo<Reduction> select(Reduction reduction, Integer page, Integer size) {
		PageVo<Reduction> vo = new PageVo<Reduction>(page, size);
		Page<Reduction> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<Reduction> list = reductionMapper.select(reduction);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public Reduction saveOrUpdate(Reduction reduction) {
		if (reduction == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(reduction);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		// 新增信息
		if (reduction.getId() == null) {
			reduction.setCreateTime(new Date());
			reductionMapper.insertSelective(reduction);
			log.info("新增优惠信息成功:" + reduction.getId());
		} else {// 根据id更新信息
			Reduction temp = reductionMapper.selectByPrimaryKey(reduction.getId());
			if (temp == null) {
				throw new DataNotFoundException(EnumReduction.DATA_NOT_FOUND);
			}
			reductionMapper.updateByPrimaryKeySelective(reduction);
			log.info("保存优惠信息成功:" + reduction.getId());
		}
		return reduction;
	}

	@Override
	public BaseVo delete(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Reduction temp = reductionMapper.selectByPrimaryKey(id);
		if (temp == null) {
			log.info("找不到优惠信息:" + id);
			throw new DataNotFoundException(EnumReduction.DATA_NOT_FOUND);
		}
		reductionMapper.deleteByPrimaryKey(id);
		return new ResultVo();
	}

	@Override
	public Reduction selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Reduction temp = reductionMapper.selectByPrimaryKey(id);
		if (temp == null) {
			log.info("找不到优惠信息:" + id);
			throw new DataNotFoundException(EnumReduction.DATA_NOT_FOUND);
		}
		return temp;
	}

	@Override
	public List<Reduction> listReductionOn() {
		Date now = new Date();
		PageHelper.orderBy("limit_num desc");
		Example example = new Example(Reduction.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andLessThan("startTime", now);
		criteria.andGreaterThan("endTime", now);
		List<Reduction> list = reductionMapper.selectByExample(example);
		return list;
	}

}
