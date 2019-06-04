/**
 * 
 */
package com.ydy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.annotation.AdminToken;
import com.ydy.annotation.CheckFormRepeat;
import com.ydy.annotation.CtrlParam;
import com.ydy.controller.base.BaseController;
import com.ydy.model.Admin;
import com.ydy.model.News;
import com.ydy.service.news.NewsService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "news")
public class NewsController extends BaseController {

	@Autowired
	private NewsService newsService;

	@GetMapping("list")
	@ResponseBody
	public ResponseEntity<PageVo<News>> list(News news, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(news);
		PageVo<News> vo = newsService.list(news, page, size);
		return ResponseEntity.ok(vo);
	}

	@GetMapping("detail")
	@ResponseBody
	public ResponseEntity<News> detail(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(newsService.selectById(id));
	}

	@AdminToken
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<News>> select(News news, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(news);
		PageVo<News> vo = newsService.select(news, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@CheckFormRepeat
	@PostMapping("save")
	@ResponseBody
	public ResponseEntity<News> save(News news) {
		Admin admin = getAdmin();
		news.setAdminId(admin.getId());
		news.setEditor(admin.getUsername());
		return ResponseEntity.ok(newsService.saveOrUpdate(news));
	}

	@AdminToken
	@CheckFormRepeat
	@PostMapping("status")
	@ResponseBody
	public ResponseEntity<BaseVo> status(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(newsService.status(id));
	}

	@AdminToken
	@CheckFormRepeat
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<BaseVo> delete(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(newsService.delete(id));
	}

	@AdminToken
	@GetMapping("selectById")
	@ResponseBody
	public ResponseEntity<News> selectById(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(newsService.selectById(id));
	}
}