package cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.service.ResourceService;

@Transactional(rollbackFor={Exception.class, RuntimeException.class})
@Controller
@RequestMapping("resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "resourceZtree", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String resourceZtree() {
		return JSONArray.toJSONString(resourceService.findAllResource());
	}

}
