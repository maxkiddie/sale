/**
 * 
 */
package com.ydy.service.good;

import com.ydy.model.Sku;
import com.ydy.model.Spu;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午3:02:19
 */
public interface GoodService {

	PageVo<Spu> selectData(Spu spu, Integer page, Integer size);

	Spu saveOrUpdateSpu(Spu spu);

	Sku saveOrUpdateSku(Sku sku);

}
