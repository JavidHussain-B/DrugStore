package com.xplorethis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.service.LoginService;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.vo.LoginVO;
import com.xplorethis.vo.MenuVO;
import com.xplorethis.vo.ResponseVO;
import com.xplorethis.vo.UserVO;


@Controller
//@RequestMapping(value = "/Test")
public class LoginController {

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService = null;
	
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
	public @ResponseBody ResponseVO authenticateUser(@RequestBody LoginVO loginVO, ModelMap model, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			if(loginVO.getUserName() != null && loginVO.getPassword() != null) {
				UserVO vo = loginService.authenticateUser(loginVO.getUserName(), loginVO.getPassword());
				if(vo != null) {
					request.getSession().setAttribute("LOGGEDIN_USER", vo);
					res.setResponseCode("success");
				} else {
					model.addAttribute("message", "Enter credentials are wrong");
					res.setResponseCode("fail");
					res.setResponseDesc("Enter credentials are wrong");
				}
			}
		} catch(Exception e) {
			res.setResponseCode("fail");
			res.setResponseDesc("Problem while authenticating User.");
			logger.error("Error occured while authenticating User ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/HOME", method = RequestMethod.GET)
	public String home(HttpServletRequest request) throws Exception {
		UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
		if (uVO == null)
			return "forward:/login";
		return "home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		model.addAttribute("message","You have been sucessfully logged out.");
		request.getSession().removeAttribute("LOGGEDIN_USER");
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping(value = "/getMenus", method = RequestMethod.GET)
	public @ResponseBody List<MenuVO> getMenus(HttpServletRequest request) {
		List<MenuVO> menuList = null;
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			menuList = loginService.getMenus(uVO.getGroupId());
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting Menus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return menuList;
	}
	
	@RequestMapping(value = "/getSubMenus", method = RequestMethod.GET)
	public @ResponseBody List<MenuVO> getSubMenus(HttpServletRequest request) {
		List<MenuVO> menuList = null;
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			menuList = loginService.getSubMenus(uVO.getGroupId(), Integer.parseInt(request.getParameter("parentId")));
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting sub Menus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return menuList;
	}
	
}