package com.ydy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("system")
public class SystemController {

	@GetMapping("version")
	@ResponseBody
	public ResponseEntity<JSONObject> version() {
		JSONObject json = new JSONObject();
		json.put("time", System.currentTimeMillis());
		json.put("version", "20190528");
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}
}
