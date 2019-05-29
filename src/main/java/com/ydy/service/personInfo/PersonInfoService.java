/**
 * 
 */
package com.ydy.service.personInfo;

import com.ydy.model.PersonInfo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月28日 上午8:38:20
 */
public interface PersonInfoService {
	PageVo<PersonInfo> select(PersonInfo personInfo, Integer page, Integer size);

	PersonInfo saveOrUpdate(PersonInfo personInfo);

	PersonInfo selectById(Long id,Long userId);
	
	PersonInfo selectById(Long id);
}
