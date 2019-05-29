/**
 * 
 */
package com.ydy.service.webInfo;

import com.ydy.model.WebInfo;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 下午4:41:57
 */
public interface WebInfoService {
	PageVo<WebInfo> select(WebInfo webInfo, Integer page, Integer size);

	WebInfo selectWebInfoByType(String type);

	WebInfo saveOrUpdate(WebInfo webInfo);

	BaseVo delete(Long id);
}
