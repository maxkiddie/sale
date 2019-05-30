/**
 * 
 */
package com.ydy.service.reduction.impl;

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
import com.ydy.ienum.EnumGood;
import com.ydy.ienum.EnumReduction;
import com.ydy.mapper.ReductionMapper;
import com.ydy.mapper.SkuMapper;
import com.ydy.mapper.SpuMapper;
import com.ydy.model.Reduction;
import com.ydy.model.Sku;
import com.ydy.model.Spu;
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
			log.info("找不到SPU:" + reduction.getSpuId());
			throw new DataNotFoundException(EnumGood.SPU_NOT_FOUND);
		}
		Sku sku = skuMapper.selectByPrimaryKey(reduction.getSkuId());
		if (sku == null) {
			log.info("找不到SKU:" + reduction.getSkuId());
			throw new DataNotFoundException(EnumGood.SKU_NOT_FOUND);
		}
		if (!Objects.equals(sku.getSpuId(), spu.getSpuId())) {
			log.info("SKU:{}不属于SKU{}子商品", sku.getSpuId(), spu.getSpuId());
			throw new BusinessException(EnumGood.SPU_SKU_NOT_RELATION);
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
	public PageVo<Reduction> selectByskuId(Long skuId, Integer page, Integer size) {
		PageVo<Reduction> vo = new PageVo<Reduction>(page, size);
		Page<Reduction> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "limit_num desc");

		Date now = new Date();
		Example example = new Example(Reduction.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("skuId", skuId);
		criteria.andLessThan("startTime", now);
		criteria.andGreaterThan("endTime", now);
		List<Reduction> list = reductionMapper.selectByExample(example);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
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

}
