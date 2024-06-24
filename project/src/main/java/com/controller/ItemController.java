package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.ItemDao;
import com.dao.ProductDao;
import com.entities.Hotel;
import com.entities.Item;
import com.entities.Product;

@Controller
public class ItemController {
	@Autowired
	ItemDao itemDao;
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping("/additem")
	public ModelAndView addItem(@RequestParam("id") int productId) {
		Product product = productDao.findbyId(productId);
		Item item = new Item();
		item.setName(product.getName());
		item.setType(product.getType());
		item.setCost(product.getCost());
		item.setQuantity(1);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemobj", item);
		mav.setViewName("Itemform");
		return mav;
	}
	@RequestMapping("/saveItemtoorder")
	public ModelAndView saceItemToOrder(@ModelAttribute("itemobj") Item currItem, HttpSession session, ServletRequest request) {
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		ModelAndView mav = new ModelAndView();
		if(quantity==0) {
			mav.setViewName("redirect://fetchallproducts");
			return mav;
		}
		currItem.setCost(quantity*currItem.getCost());
	
		if(session.getAttribute("itemslist")==null) {
			List<Item> items = new ArrayList<>();
			items.add(currItem);
			session.setAttribute("itemslist", items);
		} else {
			List<Item> items = (List<Item>) session.getAttribute("itemslist");
			
			int index = items.indexOf(currItem);
			if(index != -1) {
				Item existingItem = items.get(index);
				int newQuantity = existingItem.getQuantity() + currItem.getQuantity();
				double newCost = existingItem.getCost() + currItem.getCost();
				existingItem.setQuantity(newQuantity);
				existingItem.setCost(newCost);
			}else {
				items.add(currItem);
			}
		}
		
		mav.addObject("message","added successfully");
		mav.setViewName("redirect://fetchallproducts");
		return mav;
	}
	@RequestMapping("/removeitem")
	public ModelAndView deleteProduct(@RequestParam("id") int item_id, HttpSession session) {
		List<Item> items = (List<Item>) session.getAttribute("itemslist");

//		items.stream().filter(item -> item.getId()!=item_id).collect(Collectors.toList());
		items = itemDao.removeItemById(items, item_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("message","Deleted successfully");
		mav.setViewName("viewaddeditemstocustomer");
		return mav;
	}
//	@RequestMapping("viewaddeditemstocustomer")
	
}
