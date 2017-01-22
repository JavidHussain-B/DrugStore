package com.xplorethis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xplorethis.vo.LoginVO;

@Component
public class ApplicationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getLogger(ApplicationInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!request.getRequestURI().equals("/DrugStore/") && !request.getRequestURI().equals("/DrugStore/login")
				&& !request.getRequestURI().startsWith("/DrugStore/Test")) {
			LoginVO userData = (LoginVO) request.getSession().getAttribute("LOGGEDIN_USER");
			if (userData == null) {
				response.sendRedirect("/DrugStore");
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// logger.debug("DrugStoreInterceptor: REQUEST Intercepted for URI:
		// postHandle :" + request.getRequestURI());
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// logger.debug("DrugStoreInterceptor: REQUEST Intercepted for URI:
		// afterCompletion :" + request.getRequestURI());
	}

}