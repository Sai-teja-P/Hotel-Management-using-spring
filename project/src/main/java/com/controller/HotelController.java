package com.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.HotelDao;
import com.entities.Hotel;

@Controller
public class HotelController {
	@Autowired
	HotelDao dao;
	@RequestMapping("/addhotel")
	//handler to create Hotel class object and pass it to HotelForm.jsp
	public ModelAndView addHotel(){
		Hotel hotel = new Hotel();
		ModelAndView mv = new ModelAndView();
		mv.addObject("hotelobj",hotel);
		mv.setViewName("Hotelform");
		return mv;
	}
	@RequestMapping("/savehotel")
	public ModelAndView saveHotel(@ModelAttribute("hotelobj") Hotel hotel) {
		hotel.setStatus("Not approved");
		dao.saveHotel(hotel);

		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "your account is in review, please wait fot the evaluation.");
		mv.setViewName("Hotellogin");
		return mv;
	}
	@RequestMapping("/hotelloginvalidate")
	//hotel login validation
	public ModelAndView hotelLoginValidation(ServletRequest request,HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Hotel hotel = dao.login(email,password);
		if(hotel!=null) {
			if(hotel.getStatus().equals("Not approved")) {
				ModelAndView mv = new ModelAndView();
				mv.addObject("message","your account is in review, please wait for sometime.");
				mv.setViewName("displaymessage");
				return mv;
			}
			else if(hotel.getStatus().equals("Blocked")){
				ModelAndView mv = new ModelAndView();
				mv.addObject("message","your account is blocked");
				mv.setViewName("displaymessage");
				return mv;
			}
			else {
				//storing hotel entity object into session.
				//storing the logged in hotel into session.
				//stored at..
				//cookies->browser
				//session->server
				session.setAttribute("hotelinfo", hotel.getId());
				ModelAndView mv = new ModelAndView();
				mv.addObject("message","logged in successfully");
				mv.setViewName("Hoteloptions");
				return mv;
			}
		}
		else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("message","invalid credentials");
			mv.setViewName("Hotellogin");
			return mv;
		}
	}
	@RequestMapping("/fetchunapprovedhotels")
	//used to list all un approved hotels
	public ModelAndView fetchUnapprovedHotel() {
		List<Hotel> hotels = dao.findUnapprovedHotel();
		ModelAndView mv = new ModelAndView();
		mv.addObject("unapprovedhotels", hotels);
		mv.setViewName("displayunapprovedhotels");
		return mv;
	}
	@RequestMapping("/approvehotel")
	//this handler is used to modify the status of the hotel to approved
	public ModelAndView approveHotel(@RequestParam("id") int id) {
		Hotel hotel = dao.findHotelById(id);
		hotel.setStatus("approved");
		dao.updateHotel(hotel);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect://fetchunapprovedhotels");
		return mv;
	}
	@RequestMapping("/fetchapprovedhotels")
	//used to list all approved hotels
	public ModelAndView fetchApprovedHotel() {
		List<Hotel> hotels = dao.findApprovedHotel();
		ModelAndView mv = new ModelAndView();
		mv.addObject("approvedhotels", hotels);
		mv.setViewName("displayapprovedhotels");
		return mv;
	}
	@RequestMapping("/blockhotel")
	//this handler is used to modify the status of the hotel to Blocked
	public ModelAndView unApproveHotel(@RequestParam("id") int id) {
		Hotel hotel = dao.findHotelById(id);
		hotel.setStatus("Blocked");
		dao.updateHotel(hotel);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect://fetchapprovedhotels");
		return mv;
	}
	@RequestMapping("/fetchblockedhotels")
	//used to list all blocked hotels
	public ModelAndView fetchBlockedHotel() {
		List<Hotel> hotels = dao.findBlockedHotel();
		ModelAndView mv = new ModelAndView();
		mv.addObject("blockedhotels", hotels);
		mv.setViewName("displayblockedhotels");
		return mv;
	}
	@RequestMapping("/unblockhotel")
	//this handler is used to modify the status of the hotel to approved
	public ModelAndView unBlockHotel(@RequestParam("id") int id) {
		Hotel hotel = dao.findHotelById(id);
		hotel.setStatus("approved");
		dao.updateHotel(hotel);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect://fetchblockedhotels");
		return mv;
	}
	@RequestMapping("/hotellogout")
	//handler is used to delete the admin data from current Http session.
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Logged out succesfully");
		mv.setViewName("Hotelhomepage");
		return mv;
	}
	
	//endpoints for customer
	@RequestMapping("/fetchapprovedhotelsforcustomer")
	public ModelAndView fetchApprovedHotelsForCustomer() {
		List<Hotel> hotels = dao.findApprovedHotel();
		ModelAndView mv = new ModelAndView();
		mv.addObject("approvedhotels", hotels);
		mv.setViewName("readhotelname");
		return mv;
	}
}
