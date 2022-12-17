package kr.worthseeing.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.service.CouponService;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.service.UsersService;

@Controller
public class MyPageController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BlockGroupService blockGroupService;
	
	@Autowired
	private CouponService couponService;
	
	@GetMapping("/mypageMain")
	public String getmypage(Model model) {
		Users principalUsers = new Users();
		principalUsers.setUserId("user1"); // 로그인 구현되면 수정
		Users users = usersService.getUsers(principalUsers); // 현재 로그인한 사용자의 정보 가져오기(1명꺼)
		model.addAttribute("users", users);
		
//		BlockGroup group = new BlockGroup();
//		BlockGroup userGroup = blockGroupService.getBlockGroup(principalUsers);
		
		return "/mypageMain";
	}
	
	@GetMapping("/mypagePointHistory")
	public String getlistcoupon(Model model) {
		List<Coupon> couponList = couponService.listCoupon();
		model.addAttribute("couponList", couponList);
		
		return "/mypagePointHistory";
	}
	
	@GetMapping("/mypageCouponMall")
	public String getmypageCouponMall() {
		return "/mypageCouponMall";
	}
	

//	@GetMapping("/mypageAuctionHistory")
//	public String getmymypageAuctionHistory() {
//		return "/mypageAuctionHistory";
//	}
//
//	@GetMapping("/mypagePointHistory")
//	public String getmypagePointHistory() {
//		return "/mypagePointHistory";
//	}
//
//	@GetMapping("/mypagePurchaseHistory")
//	public String getmyagePurchaseHistory() {
//		return "/mypagePurchaseHistory";
//	}


}
