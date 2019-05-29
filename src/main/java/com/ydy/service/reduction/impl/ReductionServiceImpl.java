/**
 * 
 */
package com.ydy.service.reduction.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
import com.ydy.mapper.ReductionMapper;
import com.ydy.mapper.SkuMapper;
import com.ydy.mapper.SpuMapper;
import com.ydy.model.Reduction;
import com.ydy.model.Sku;
import com.ydy.model.Spu;
import com.ydy.service.reduction.ReductionService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.ienum.EnumGood;
import com.ydy.vo.ienum.EnumReduction;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 下午6:43:33
 */
@Service
@Transactional
public class ReductionServiceImpl implements ReductionService {

	@Autowired
	private ReductionMapper reductionMapper;
	@Autowired
	private SpuMapper spuMapper;
	@Autowired
	private SkuMapper skuMapper;

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
		Spu spu = spuMapper.selectByPrimaryKey(reduction.getSpuId());
		if (spu == null) {
			throw new MyException(EnumGood.SPU_NOT_FOUND);
		}
		Sku sku = skuMapper.selectByPrimaryKey(reduction.getSkuId());
		if (sku == null) {
			throw new MyException(EnumGood.SKU_NOT_FOUND);
		}
		if (!Objects.equals(sku.getSpuId(), spu.getSpuId())) {
			throw new MyException(EnumGood.SPU_SKU_NOT_RELATION);
		}
		// 新增信息
		if (reduction.getId() == null) {
			reduction.setCreateTime(new Date());
			reductionMapper.insertSelective(reduction);
		} else {// 根据id更新信息
			Reduction temp = reductionMapper.selectByPrimaryKey(reduction.getId());
			if (temp == null) {
				throw new MyException(EnumReduction.DATA_NOT_FOUND);
			}
			reductionMapper.updateByPrimaryKeySelective(reduction);
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
			throw new MyException(EnumReduction.DATA_NOT_FOUND);
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
			throw new MyException(EnumReduction.DATA_NOT_FOUND);
		}
		return temp;
	}

}
