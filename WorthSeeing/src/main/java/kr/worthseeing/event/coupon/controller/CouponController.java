package kr.worthseeing.event.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.event.coupon.service.CouponService;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.users.service.UsersService;

@Controller
public class CouponController {
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private UsersService usersService;
	
	

}
