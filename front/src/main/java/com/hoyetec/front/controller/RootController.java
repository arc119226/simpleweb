
package com.hoyetec.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author arcliu
 *
 */
@Controller
public class RootController {
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "index";
	}
}
