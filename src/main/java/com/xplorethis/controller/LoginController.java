package com.xplorethis.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xplorethis.service.LoginService;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.vo.LoginVO;
import com.xplorethis.vo.UserMenuAccessVO;
import com.xplorethis.vo.UserVO;


@Controller
@RequestMapping(value = "/Test")
public class LoginController {

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService = null;
	
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
	public String authenticateUser(@RequestBody LoginVO loginVO, ModelMap model, HttpServletRequest request) {
		try {
			if(loginVO.getUserName() != null && loginVO.getPassword() != null) {
				UserVO vo = loginService.authenticateUser(loginVO.getUserName(), loginVO.getPassword());
				if(vo != null) {
					request.getSession().setAttribute("LOGGEDIN_USER", vo);
					return "home";
				} else {
					model.addAttribute("message", "Enter credentials are wrong");
					return "login";
				}
			}
		} catch(Exception e) {
			logger.error("Error occured while authenticating User ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		model.addAttribute("message","You have been sucessfully logged out.");
		request.getSession().removeAttribute("LOGGEDIN_USER");
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping(value = "/getMenus", method = RequestMethod.POST)
	public UserMenuAccessVO getMenus(@RequestBody LoginVO loginVO, HttpServletRequest request) {
		UserMenuAccessVO vo = null;
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			vo = loginService.getMenus(loginVO.getUserName(), uVO.getGroupId());
		} catch(Exception e) {
			logger.error("Error occured while getting Menus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return vo;
	}

}