/**
 * 
 */
package com.ydy.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.exception.BusinessException;
import com.ydy.exception.DataNotFoundException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumSystem;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.ErrorVo;
import com.ydy.vo.other.ResultVo;

/**
 * 控制器全局处理器
 * 
 * @author xuzhaojie
 *
 *         2019年5月12日 上午8:58:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = ValidateException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, ValidateException e) {
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.DATA_NOT_MATCH);
		vo.setErrors(e.getErrors());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, BusinessException e) {
		ResultVo vo = new ResultVo();
		vo.setErrorEnum(e.getErrorEnum());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = DataNotFoundException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, DataNotFoundException e) {
		ResultVo vo = new ResultVo();
		vo.setErrorEnum(e.getErrorEnum());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(vo);
	}

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionHandler(HttpServletRequest req, Exception e) {
		String requestURI = req.getRequestURI();
		String msg = requestURI + "出现异常:" + e.getMessage();
		log.error(msg);
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.SYSTEM_ERROR);
		vo.putError("error", msg);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(vo);
	}
}
