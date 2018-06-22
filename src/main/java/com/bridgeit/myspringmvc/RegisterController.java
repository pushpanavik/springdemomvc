package com.bridgeit.myspringmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.model.User1;
import com.bridgeit.service.IUserService;

@Controller
public class RegisterController {
	
	@Autowired
	private IUserService userServices;

	@RequestMapping("register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@RequestMapping("registerProcess")
	public ModelAndView registerProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User1 user ) {
		ModelAndView mav;
		int status = userServices.register(user);
		if(status==1) {
			mav = new ModelAndView("login");
			mav.addObject("result", "Successfully Registered! Login Here");
		}else {
			mav = new ModelAndView("register");
			mav.addObject("message", "Sorry! Registertion Failed! Re-try Again!");
		}
		return mav;
	}
}
