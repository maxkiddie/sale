/**
 * 
 */
package com.ydy.service.faq;

import com.ydy.model.Faq;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午7:11:11
 */
public interface FaqService {

	PageVo<Faq> selectData(Faq faq, Integer page, Integer size);

	Faq saveOrUpdate(Faq faq);

	BaseVo delete(Long id);
}
