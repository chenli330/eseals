package cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.globalConfig.TheGlobalConfig;
import cn.pojo.Resource;
import cn.pojo.User;
import cn.service.UserService;
import cn.util.GetWeather;
import cn.util.SecurityCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-14
 * <p>
 * Version: 1.0
 */
@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
@Controller
public class IndexController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model, @RequestParam(value="securityCode",required=false) String securityCode) {
		User user = (User) request.getAttribute(TheGlobalConfig.USER_LOGIN);
		List<Resource> resourcesList = userService.findResourceByUsername(user.getUsername());
		model.addAttribute("resourcesList", resourcesList);
		String weather = GetWeather.queryWeather("芜湖");
		model.addAttribute("weather", weather);
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/getSecurityCode")
	public void getSecurityCode(HttpServletResponse response, HttpSession session) throws Exception {
		Map map = SecurityCode.generateCodeAndPic();
		ServletOutputStream out = response.getOutputStream();
		BufferedImage img = (BufferedImage) map.get("codePic");
		String securityCode = (String) map.get("code");
		session.setAttribute("securityCode", securityCode);
		ImageIO.write(img, "jpg", response.getOutputStream());
	}

}
