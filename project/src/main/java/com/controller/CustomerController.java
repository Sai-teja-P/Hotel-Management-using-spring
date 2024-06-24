package com.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.CustomerDao;
import com.entities.Customer;
//i can add an additional entity called rider that gets the order info(foodorderid) and gets the customer's address 
//i can add another attribute to find closest hotel by calculating the distance between customer and hotela nd providing the list based on that order.
@Controller
public class CustomerController {
	@Autowired
	CustomerDao customerDao;
	
	@RequestMapping("/addcustomer")
	public ModelAndView addCustomer() {
		Customer customer = new Customer();
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerobj",customer);
		mav.setViewName("Customerform");
		return mav;
	}
	@RequestMapping("/savecustomer")
	//handler used to save customer details into database.
	public ModelAndView saveCustomer(@ModelAttribute("customerobj") Customer customer ) {
		customerDao.saveCustomer(customer);

		ModelAndView mav=new ModelAndView();
		mav.addObject("message","account created successfully");
		mav.setViewName("Customerlogin");
		return mav;
	}
	@RequestMapping("/customerloginvalidate")
	//handler used to perform login validation for customer
	public ModelAndView customerLoginValidation(ServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Customer customer = customerDao.login(email, password);
		ModelAndView mav=new ModelAndView();
		if(customer!=null) {
			session.setAttribute("customerinfo",customer.getId());
			//
			mav.addObject("message","logged in successfully");
			mav.setViewName("Customeroptions");
		}

		else {
			mav.addObject("message","Invalid Credentials");
			mav.setViewName("Customerlogin");
		}
		return mav;
	}
	@RequestMapping("/customerlogout")
	//handler is used to delete the customer data from current Http session.
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Logged out succesfully");
		mv.setViewName("Customerhome");
		return mv;
	}
}
