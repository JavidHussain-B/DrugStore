package com.xplorethis.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xplorethis.service.BaseService;

/**
 * The Class BaseController.
 */
@Controller
public class BaseController {

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(BaseController.class);
	
	/** The base service. */
	@Autowired
	BaseService baseService = null;
	
	/** The data. */
	private StringBuffer data = null;

}