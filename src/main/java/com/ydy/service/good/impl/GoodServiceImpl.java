/**
 * 
 */
package com.ydy.service.good.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.constant.SystemConstant;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
import com.ydy.mapper.ReductionMapper;
import com.ydy.mapper.SkuMapper;
import com.ydy.mapper.SpuDetailMapper;
import com.ydy.mapper.SpuMapper;
import com.ydy.model.Reduction;
import com.ydy.model.Sku;
import com.ydy.model.Spu;
import com.ydy.model.SpuDetail;
import com.ydy.service.good.GoodService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.SpuVo;
import com.ydy.vo.ienum.EnumGood;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午3:02:44
 */
@Service
@Transactional
public class GoodServiceImpl implements GoodService {

	@Autowired
	private SpuMapper spuMapper;
	@Autowired
	private SpuDetailMapper spuDetailMapper;
	@Autowired
	private ReductionMapper reductionMapper;
	@Autowired
	private SkuMapper skuMapper;

	@Override
	public PageVo<Spu> select(Spu spu, Integer page, Integer size) {
		PageVo<Spu> vo = new PageVo<Spu>(page, size);
		Page<Spu> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "spu_id desc");
		List<Spu> list = spuMapper.select(spu);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PageVo<Spu> list(Spu spu, Integer page, Integer size) {
		PageVo<Spu> vo = new PageVo<Spu>(page, size);
		Page<Spu> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "spu_id desc");
		spu.setSpuStatus(SystemConstant.SPU_ON);
		List<Spu> list = spuMapper.select(spu);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PageVo<Sku> select(Sku sku, Integer page, Integer size) {
		PageVo<Sku> vo = new PageVo<Sku>(page, size);
		Page<Sku> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "sku_id desc");
		List<Sku> list = skuMapper.select(sku);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PageVo<Sku> list(Sku sku, Integer page, Integer size) {
		PageVo<Sku> vo = new PageVo<Sku>(page, size);
		Page<Sku> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "sku_id desc");
		List<Sku> list = skuMapper.select(sku);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public SpuVo selectSpuVoById(Long spuId) {
		if (spuId == null) {
			throw new NullPointerException();
		}
		SpuVo vo = spuMapper.selectSpuById(spuId, SystemConstant.SPU_ON);
		if (vo == null) {
			throw new MyException(EnumGood.SPU_OFF);
		}
		Sku sku = new Sku();
		sku.setSpuId(spuId);
		List<Sku> list = skuMapper.select(sku);
		vo.setSkus(list);
		return vo;
	}

	public Spu saveOrUpdateSpu(Spu spu) {
		if (spu == null) {
			throw new NullPointerException("Spu不能为空");
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(spu);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		Date now = new Date();
		if (spu.getSpuId() == null) {
			// TODO 分类
			spu.setCategoryId(0);
			spu.setCreateTime(now);
			spu.setUpdateTime(now);
			spu.setSpuStatus(SystemConstant.SPU_OFF);
			spuMapper.insertSelective(spu);
			SpuDetail detail = new SpuDetail();
			detail.setDetail(spu.getDetail());
			detail.setSpuId(spu.getSpuId());
			detail.setImages(spu.getImages());
			spuDetailMapper.insertSelective(detail);
		} else {
			Spu temp = spuMapper.selectByPrimaryKey(spu.getSpuId());
			if (temp != null) {
				spu.setUpdateTime(now);
				spuMapper.updateByPrimaryKeySelective(spu);
				SpuDetail detail = new SpuDetail();
				detail.setDetail(spu.getDetail());
				detail.setSpuId(spu.getSpuId());
				detail.setImages(spu.getImages());
				spuDetailMapper.updateByPrimaryKeySelective(detail);
			} else {
				throw new MyException(EnumGood.SPU_NOT_FOUND);
			}
		}
		return spu;
	}

	@Override
	public Sku saveOrUpdateSku(Sku sku) {
		if (sku == null) {
			throw new NullPointerException("sku不能为空");
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(sku);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		Spu spu = spuMapper.selectByPrimaryKey(sku.getSpuId());
		if (spu == null) {
			throw new MyException(EnumGood.SPU_NOT_FOUND);
		}
		Date now = new Date();
		if (sku.getSkuId() == null) {
			sku.setCreateTime(now);
			sku.setUpdateTime(now);
			skuMapper.insertSelective(sku);
		} else {
			Sku temp = skuMapper.selectByPrimaryKey(sku.getSkuId());
			if (temp != null) {
				sku.setUpdateTime(now);
				skuMapper.updateByPrimaryKeySelective(sku);
			} else {
				throw new MyException(EnumGood.SKU_NOT_FOUND);
			}
		}
		Sku example = new Sku();
		example.setSpuId(spu.getSpuId());
		List<Sku> listSku = skuMapper.select(example);
		spuMapper.updateByPrimaryKeySelective(computePrice(spu.getSpuId(), listSku));
		return sku;
	}

	private Spu computePrice(Long spuId, List<Sku> list) {
		Spu updateSpu = new Spu();
		updateSpu.setSpuId(spuId);
		updateSpu.setUpdateTime(new Date());
		Long maxPrice = 0L;
		Long minPrice = 0L;
		Long nowMinPrice = 0L;
		Long nowMaxPrice = 0L;
		boolean initFlag = true;
		if (!CollectionUtils.isEmpty(list)) {
			for (Sku data : list) {
				if (initFlag) {
					maxPrice = data.getPrice();
					minPrice = data.getPrice();
					nowMaxPrice = data.getNowPrice();
					nowMinPrice = data.getNowPrice();
					initFlag = false;
					continue;
				}
				maxPrice = Math.max(data.getPrice(), maxPrice);
				minPrice = Math.min(data.getPrice(), minPrice);
				nowMaxPrice = Math.max(data.getNowPrice(), nowMaxPrice);
				nowMinPrice = Math.min(data.getNowPrice(), nowMinPrice);
			}
		}
		updateSpu.setMaxPrice(maxPrice);
		updateSpu.setMinPrice(minPrice);
		updateSpu.setNowMaxPrice(nowMaxPrice);
		updateSpu.setNowMinPrice(nowMinPrice);
		return updateSpu;
	}

	@Override
	public BaseVo statusSpu(Long spuId) {
		if (spuId == null) {
			throw new NullPointerException();
		}
		Spu temp = spuMapper.selectByPrimaryKey(spuId);
		if (temp == null) {
			throw new MyException(EnumGood.SPU_NOT_FOUND);
		}
		Spu spu = new Spu();
		spu.setSpuId(spuId);
		if (Objects.equals(SystemConstant.SPU_ON, temp.getSpuStatus())) {
			spu.setSpuStatus(SystemConstant.SPU_OFF);
		} else {
			spu.setSpuStatus(SystemConstant.SPU_ON);
		}
		spuMapper.updateByPrimaryKeySelective(spu);
		return new ResultVo();
	}

	@Override
	public BaseVo deleteSpu(Long spuId) {
		if (spuId == null) {
			throw new NullPointerException();
		}
		Spu temp = spuMapper.selectByPrimaryKey(spuId);
		if (temp == null) {
			throw new MyException(EnumGood.SPU_NOT_FOUND);
		}
		spuMapper.deleteByPrimaryKey(spuId);
		spuDetailMapper.deleteByPrimaryKey(spuId);
		Sku sku = new Sku();
		sku.setSpuId(spuId);
		skuMapper.delete(sku);
		Reduction red = new Reduction();
		red.setSpuId(spuId);
		reductionMapper.delete(red);
		return new ResultVo();
	}

	@Override
	public BaseVo deleteSku(Long skuId) {
		if (skuId == null) {
			throw new NullPointerException();
		}
		Sku temp = skuMapper.selectByPrimaryKey(skuId);
		if (temp == null) {
			throw new MyException(EnumGood.SKU_NOT_FOUND);
		}
		skuMapper.deleteByPrimaryKey(skuId);
		Sku example = new Sku();
		example.setSpuId(temp.getSpuId());
		List<Sku> listSku = skuMapper.select(example);
		spuMapper.updateByPrimaryKeySelective(computePrice(temp.getSpuId(), listSku));
		Reduction red = new Reduction();
		red.setSkuId(skuId);
		reductionMapper.delete(red);
		return new ResultVo();
	}

	@Override
	public Sku selectSkuById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Sku temp = skuMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumGood.SKU_NOT_FOUND);
		}
		return temp;
	}

}
