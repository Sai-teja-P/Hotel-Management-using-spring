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

import com.dao.HotelDao;
import com.dao.ProductDao;
import com.entities.Hotel;
import com.entities.Product;

@Controller
public class ProductController {
	@Autowired
	ProductDao productDao;
	@Autowired
	HotelDao hotelDao;
	//hotel based endpoints:-
	@RequestMapping("/addproduct")
	public ModelAndView addProduct() {
		Product p = new Product();

		ModelAndView mv = new ModelAndView();
		mv.addObject("productobj",p);
		mv.setViewName("Productform");
		return mv;
	}
	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("productobj") Product p, HttpSession session) {
		Integer hotel_id = (Integer) session.getAttribute("hotelinfo");

		Hotel hotel = hotelDao.findHotelById(hotel_id);
		List<Product> products = hotel.getProducts();
		if(products != null) {
			products.add(p);
			hotel.setProducts(products);
		}
		else {
			List<Product> productList = new ArrayList<>();
			productList.add(p);
			hotel.setProducts(productList);
		}
		productDao.saveproduct(p);
		hotelDao.updateHotel(hotel);

		ModelAndView mv = new ModelAndView();
		mv.addObject("message","added successfully");
		mv.setViewName("Hoteloptions");
		return mv;
	}
	
	@RequestMapping("/updateproduct")
	public ModelAndView updateProduct(@RequestParam("id") int id) {
		Product product = productDao.findbyId(id);

		ModelAndView mv = new ModelAndView();
		mv.addObject("existingProductInfo",product);
		mv.setViewName("updateproductform");
		return mv;
	}
	@RequestMapping("/updateproductinfo")
	public ModelAndView updateProductInformation(@ModelAttribute("existingProductInfo") Product p) {
		productDao.updateProduct(p);

		ModelAndView mv = new ModelAndView();
		mv.addObject("message","updated successfully");
		mv.setViewName("redirect://fetchallhotelproducts");
		return mv;
	}
	@RequestMapping("/deleteproduct")
	public ModelAndView deleteProduct(@RequestParam("id") int id, HttpSession session) {
		Integer hotel_id = (Integer) session.getAttribute("hotelinfo");
		Hotel hotel = hotelDao.findHotelById(hotel_id);
		List<Product> products = hotel.getProducts();

		products.stream().filter(product-> product.getId()!=id).collect(Collectors.toList());
		hotel.setProducts(products);

		hotelDao.updateHotel(hotel);
		productDao.deleteById(id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("message","Deleted successfully");
		mav.setViewName("redirect://fetchallhotelproducts");
		return mav;
	}
	@RequestMapping("/fetchallhotelproducts")
	public ModelAndView fetchAllHotelProducts(HttpSession session) {
		Integer hotel_id = (Integer) session.getAttribute("hotelinfo");
		Hotel hotel = hotelDao.findHotelById(hotel_id);
		List<Product> products = hotel.getProducts();

		ModelAndView mv = new ModelAndView();
		mv.addObject("products", products);
		mv.setViewName("displayproductstohotel");
		return mv;
	}
//	@RequestMapping("/fetchproductsbyhotelbyhotel")//endpoint for hotel users or customer users 
//	public ModelAndView fetchProductsByHotel(HttpSession session) {
//		System.out.println(session.getAttribute("hotelinfo"));
//		Integer hotelid = (Integer) session.getAttribute("hotelinfo");
//		List<Product> products = productDao.fetchProductsByHotel(hotelid);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("productsList", products);
//		mav.setViewName("displayproductstohotel");
//		return mav;
//	}
	
	//customer based endpoints:-
	@RequestMapping("/fetchproductsbyhotelname")
	public ModelAndView fetchProductsByHotelname(@RequestParam("hotelname") String hotelname) {
		List<Product> products = productDao.fetchProductsByHotelname(hotelname);
		ModelAndView mav = new ModelAndView();
		mav.addObject("productsList", products);
		mav.setViewName("displayproductstocustomer");
		return mav;
	}
	@RequestMapping("/fetchallproducts")
	public ModelAndView fetchAllProducts() {
		List<Product> products = productDao.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("productsList",products);
		mav.setViewName("displayproductstocustomer");
		return mav;
	}
	@RequestMapping("/fetchproductsbetweenpricerange")
	public ModelAndView fetchProductsBetweenPriceRange(@RequestParam("max") Double max, @RequestParam("min") Double min) {
//		String min = request.getParameter("min");
//		String max = request.getParameter("max");
		List<Product> products = productDao.fetchProductsBetweenPriceRange(min, max);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("productsList",products);
		mav.setViewName("displayproductstocustomer");
		return mav;
	}
}
