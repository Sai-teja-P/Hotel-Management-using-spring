package com.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AdminDao;
import com.entities.Admin;


@Controller
public class AdminController {

	@Autowired
	AdminDao dao;
	
	@RequestMapping("/addadmin")
	//handler used to addmin
	public ModelAndView addAdmin() {
		Admin admin=new Admin();
		ModelAndView mav=new ModelAndView();
		mav.addObject("adminobj",admin);
		mav.setViewName("Adminform");
		return mav;
	}

	@RequestMapping("/saveadmin")
	//handler used to save admin details into database.
	public ModelAndView saveAdmin(@ModelAttribute("adminobj") Admin admin ) {
		dao.saveAdmin(admin);

		ModelAndView mav=new ModelAndView();
		mav.addObject("message","account created successfully");
		mav.setViewName("Adminlogin");
		return mav;
	}

	@RequestMapping("/adminloginvalidate")
	//handler used to perform login validation for admin
	public ModelAndView loginValidation(ServletRequest request, HttpSession session) {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Admin admin=dao.login(email, password);
		
		ModelAndView mav=new ModelAndView();

		if(admin!=null) {
			session.setAttribute("admininfo",admin);
			mav.addObject("message","logged in successfully!");
			mav.setViewName("Adminoptions");
			return mav;
		}

		else {
			mav.addObject("message","Invalid Credentials");
			mav.setViewName("Adminlogin");
			return mav;
		}
	}
	@RequestMapping("/adminlogout")
	//handler is used to delete the admin data from current Http session.
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Logged out succesfully");
		mv.setViewName("Adminhome");
		return mv;
	}
}