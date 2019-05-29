/**
 * 
 */
package com.ydy.service.faq.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
import com.ydy.mapper.FaqMapper;
import com.ydy.model.Faq;
import com.ydy.service.faq.FaqService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.ienum.EnumFaq;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午8:05:21
 */
@Service
@Transactional
public class FaqServiceImpl implements FaqService {

	@Autowired
	private FaqMapper faqMapper;

	@Override
	public PageVo<Faq> select(Faq faq, Integer page, Integer size) {
		PageVo<Faq> vo = new PageVo<Faq>(page, size);
		Page<Faq> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<Faq> list = faqMapper.select(faq);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PageVo<Faq> list(Faq faq, Integer page, Integer size) {
		PageVo<Faq> vo = new PageVo<Faq>(page, size);
		Page<Faq> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "inx asc");
		List<Faq> list = faqMapper.select(faq);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public Faq saveOrUpdate(Faq faq) {
		if (faq == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(faq);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		// 新增信息
		if (faq.getId() == null) {
			faqMapper.insertSelective(faq);
		} else {// 根据id更新信息
			Faq temp = faqMapper.selectByPrimaryKey(faq.getId());
			if (temp == null) {
				throw new MyException(EnumFaq.DATA_NOT_FOUND);
			}
			faqMapper.updateByPrimaryKeySelective(faq);
		}
		return faq;
	}

	@Override
	public BaseVo delete(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Faq temp = faqMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumFaq.DATA_NOT_FOUND);
		}
		faqMapper.deleteByPrimaryKey(id);
		return new ResultVo();
	}

	@Override
	public Faq selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Faq temp = faqMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new MyException(EnumFaq.DATA_NOT_FOUND);
		}
		return temp;
	}

}
