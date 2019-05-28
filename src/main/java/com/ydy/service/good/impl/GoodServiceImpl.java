/**
 * 
 */
package com.ydy.service.good.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.exception.MyException;
import com.ydy.mapper.SkuMapper;
import com.ydy.mapper.SpuDetailMapper;
import com.ydy.mapper.SpuMapper;
import com.ydy.model.Sku;
import com.ydy.model.Spu;
import com.ydy.model.SpuDetail;
import com.ydy.service.good.GoodService;
import com.ydy.vo.ienum.EnumSystem;
import com.ydy.vo.other.PageVo;

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
	private SkuMapper skuMapper;

	@Override
	public PageVo<Spu> selectData(Spu spu, Integer page, Integer size) {
		PageVo<Spu> vo = new PageVo<Spu>(page, size);
		Page<Spu> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "spu_id desc");
		List<Spu> list = spuMapper.select(spu);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	public Spu saveOrUpdateSpu(Spu spu) {
		if (spu == null) {
			throw new NullPointerException("Spu不能为空");
		}
		if (spu.getSpuId() == null) {
			spu.setCreateTime(new Date());
			spuMapper.insertSelective(spu);
			SpuDetail detail = new SpuDetail();
			detail.setDetail(spu.getDetail());
			detail.setSpuId(spu.getSpuId());
			spuDetailMapper.insertSelective(detail);
		} else {
			Spu temp = spuMapper.selectByPrimaryKey(spu.getSpuId());
			if (temp != null) {
				spu.setUpdateTime(new Date());
				spuMapper.updateByPrimaryKeySelective(spu);
				SpuDetail detail = new SpuDetail();
				detail.setDetail(spu.getDetail());
				detail.setSpuId(spu.getSpuId());
				spuDetailMapper.updateByPrimaryKeySelective(detail);
			} else {
				throw new MyException(EnumSystem.DATA_NOT_FOUND);
			}
		}
		return spu;
	}

	@Override
	public Sku saveOrUpdateSku(Sku sku) {
		if (sku == null) {
			throw new NullPointerException("sku不能为空");
		}
		if (sku.getSkuId() == null) {
			sku.setCreateTime(new Date());
			skuMapper.insertSelective(sku);
		} else {
			Sku temp = skuMapper.selectByPrimaryKey(sku.getSkuId());
			if (temp != null) {
				sku.setUpdateTime(new Date());
				skuMapper.updateByPrimaryKeySelective(sku);
			} else {
				throw new MyException(EnumSystem.DATA_NOT_FOUND);
			}
		}
		return sku;
	}

}
