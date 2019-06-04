/**
 * 
 */
package com.ydy.service.news;

import com.ydy.model.News;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午7:11:11
 */
public interface NewsService {

	PageVo<News> select(News news, Integer page, Integer size);

	PageVo<News> list(News news, Integer page, Integer size);

	News saveOrUpdate(News news);

	BaseVo status(Long id);

	BaseVo delete(Long id);

	News selectById(Long id);
}
