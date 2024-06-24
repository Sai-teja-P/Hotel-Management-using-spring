package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.CustomerDao;
import com.dao.FoodOrderDao;
import com.entities.Customer;
import com.entities.FoodOrder;
import com.entities.Item;

@Controller
public class FoodOrderController {
	@Autowired
	FoodOrderDao foodOrderDao;
	
	@Autowired
	CustomerDao customerDao;
	@RequestMapping("/addfoodorder")
	public ModelAndView addFoodOrder(HttpSession session) { 
//		FoodOrder foodOrder = new FoodOrder();
		int customerId = (Integer) session.getAttribute("customerinfo");
		FoodOrder foodOrder = customerDao.getBillDetails(customerId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("foodOrderObj", foodOrder);
		mav.setViewName("foodorderform");
		return mav;
	}
	
	@RequestMapping("/saveFoodOrder")
	public ModelAndView saveFoodOrder(@ModelAttribute("foodOrderObj") FoodOrder foodOrder, HttpSession session) {
		List<Item> items = (List<Item>) session.getAttribute("itemslist");
		ModelAndView mav = new ModelAndView();
		if(items==null || items.isEmpty()) {
			mav.addObject("message","No items to Bill");
			mav.setViewName("errorpage");
			return mav;
		}
		foodOrder.setItems(items);
		
		double totalprice = items.stream().map(i->i.getCost()).mapToDouble(Double::new).sum();
		//map method, filter method and for each all execute based on the no of elements present.
		//mapToDataType can be used to convert stream type of data to given datatype.
		//(Double::new) this piece of code functions by calling the constructor of given data type and assign the given element.
		//mapToDouble(i-> new Double(i)) == mapToDouble(Double::new)
		foodOrder.setTotalprice(totalprice);
		
		Integer customerId = (Integer) session.getAttribute("customerinfo");
		if (customerId == null) {
	        mav.setViewName("errorpage");
	        mav.addObject("message", "Customer information not found.");
	        return mav;
	    }
		Customer customer = customerDao.findCustomerById(customerId);
		
		List<FoodOrder> foodOrders = customer.getFoodorders();
		if(foodOrders != null) {
			foodOrders.add(foodOrder);
//			customer.setFoodorder(foodOrders);
		}
		else {
			foodOrders = new ArrayList<>();
			foodOrders.add(foodOrder);
			customer.setFoodorder(foodOrders);
		}
		foodOrderDao.saveFoodOrder(foodOrder);
		customerDao.updateCustomer(customer);
		session.removeAttribute("itemslist");
		
		//session.invalidate() : is used for logout 
		//invalidate method of HttpSession is used, this destroys all the information from HtppSession.
		
		session.setAttribute("foodOrderInfo", foodOrder);
		mav.addObject("message","Bill Details");
		
		mav.setViewName("displaybilltocustomer");
		//Receiver  name:
		//	    	mobile number:
		//			address:
		//List of food orders(table).
		return mav;
	}
}
