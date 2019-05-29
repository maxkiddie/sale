/**
 * 
 */
package com.ydy.service.good;

import com.ydy.model.Sku;
import com.ydy.model.Spu;
import com.ydy.vo.SpuVo;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午3:02:19
 */
public interface GoodService {

	PageVo<Spu> select(Spu spu, Integer page, Integer size);

	SpuVo selectSpuVoById(Long spuId);

	PageVo<Spu> list(Spu spu, Integer page, Integer size);

	PageVo<Sku> select(Sku sku, Integer page, Integer size);

	PageVo<Sku> list(Sku sku, Integer page, Integer size);

	Spu saveOrUpdateSpu(Spu spu);

	Sku saveOrUpdateSku(Sku sku);

	BaseVo statusSpu(Long spuId);

	BaseVo deleteSpu(Long spuId);

	BaseVo deleteSku(Long skuId);

}
