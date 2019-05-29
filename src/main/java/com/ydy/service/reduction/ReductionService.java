/**
 * 
 */
package com.ydy.service.reduction;

import com.ydy.model.Reduction;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 下午6:05:39
 */
public interface ReductionService {

	PageVo<Reduction> select(Reduction reduction, Integer page, Integer size);


	Reduction saveOrUpdate(Reduction reduction);

	BaseVo delete(Long id);

	Reduction selectById(Long id);
}
