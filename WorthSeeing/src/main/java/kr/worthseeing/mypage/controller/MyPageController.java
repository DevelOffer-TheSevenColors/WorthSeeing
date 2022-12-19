package kr.worthseeing.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.service.CouponService;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.service.UsersService;

@Controller
public class MyPageController {
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private BlockGroupService blockGroupService;
	
	@GetMapping("/mypageMain")
	public String getmypage(Model model,@AuthenticationPrincipal SecurityUser principal) {
//		Users principalUsers = new Users();
//		principalUsers.setUserId("user1"); // 로그인 구현되면 수정
		model.addAttribute("users", principal.getUsers());
		System.out.println("=======================>"+principal.getUsers());
		
		return "/mypageMain";
	}
	
	@GetMapping("/mypagePointHistory")
	public String getlistcoupon(Model model,@AuthenticationPrincipal SecurityUser principal) {
		List<Coupon> couponList = couponService.listCoupon();
		model.addAttribute("couponList", couponList);
		model.addAttribute("users", principal.getUsers());
		
		return "/mypagePointHistory";
	}
	
	@GetMapping("/mypageCouponMall")
	public String getmypageCouponMall() {
		return "/mypageCouponMall";
	}
	

	@GetMapping("/mypageAuctionHistory")
	public String getmypageAuctionHistory(Model model,@AuthenticationPrincipal SecurityUser principal) {
		List<Auction> auctionList = auctionService.getlistAuction();
		model.addAttribute("auctionList",auctionList);
		model.addAttribute("users",principal.getUsers());
		
		return "/mypageAuctionHistory";
	}

	@GetMapping("/mypagePurchaseHistory")
	public String getmyagePurchaseHistory(Model model,@AuthenticationPrincipal SecurityUser principal) {
		List<Auction> auctionList = auctionService.getlistAuction();
		List<BlockGroup> blockGroupList = blockGroupService.getListBlockGroup();
		model.addAttribute("auctionList",auctionList);
		model.addAttribute("users",principal.getUsers());
		model.addAttribute("blockGroupList",blockGroupList);
		
		return "/mypagePurchaseHistory";
	}


}
