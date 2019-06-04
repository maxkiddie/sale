/**
 * 
 */
package com.ydy.service.news.impl;

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
import com.ydy.constant.SystemConstant;
import com.ydy.exception.BusinessException;
import com.ydy.exception.DataNotFoundException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumNews;
import com.ydy.mapper.NewsMapper;
import com.ydy.model.News;
import com.ydy.service.news.NewsService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 上午8:35:26
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	private final static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
	@Autowired
	private NewsMapper newsMapper;

	@Override
	public PageVo<News> select(News news, Integer page, Integer size) {
		PageVo<News> vo = new PageVo<News>(page, size);
		Page<News> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<News> list = newsMapper.select(news);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PageVo<News> list(News news, Integer page, Integer size) {
		PageVo<News> vo = new PageVo<News>(page, size);
		Page<News> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "top_status desc,update_time desc");
		List<News> list = newsMapper.select(news);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public News saveOrUpdate(News news) {
		if (news == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(news);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		Date now = new Date();
		// 新增信息
		if (news.getId() == null) {
			news.setTopStatus(SystemConstant.NEWS_TOP_OFF);
			news.setCreateTime(now);
			news.setUpdateTime(now);
			newsMapper.insertSelective(news);
			log.info("新增news成功：" + news.getId());
		} else {// 根据id更新信息
			News temp = newsMapper.selectByPrimaryKey(news.getId());
			if (temp == null) {
				log.info("找不到news信息：" + news.getId());
				throw new DataNotFoundException(EnumNews.DATA_NOT_FOUND);
			}
			newsMapper.updateByPrimaryKeySelective(news);
			log.info("更新news成功：" + news.getId());
		}
		return news;
	}

	@Override
	public BaseVo delete(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		News temp = newsMapper.selectByPrimaryKey(id);
		if (temp == null) {
			log.info("找不到news信息：" + id);
			throw new DataNotFoundException(EnumNews.DATA_NOT_FOUND);
		}
		newsMapper.deleteByPrimaryKey(id);
		log.info("删除news成功：" + id);
		return new ResultVo();
	}

	@Override
	public BaseVo status(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		News temp = newsMapper.selectByPrimaryKey(id);
		if (temp == null) {
			log.info("找不到news信息：" + id);
			throw new DataNotFoundException(EnumNews.DATA_NOT_FOUND);
		}
		News news = new News();
		news.setId(id);
		StringBuilder builder = new StringBuilder();
		if (Objects.equals(SystemConstant.NEWS_TOP_ON, temp.getTopStatus())) {
			news.setTopStatus(SystemConstant.NEWS_TOP_OFF);
			builder.append("News下顶成功:").append(id);
		} else {
			news.setTopStatus(SystemConstant.NEWS_TOP_ON);
			builder.append("News置顶成功:").append(id);
		}
		newsMapper.updateByPrimaryKeySelective(news);
		log.info(builder.toString());
		return new ResultVo();
	}

	@Override
	public News selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		News temp = newsMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new BusinessException(EnumNews.DATA_NOT_FOUND);
		}
		return temp;
	}

}
