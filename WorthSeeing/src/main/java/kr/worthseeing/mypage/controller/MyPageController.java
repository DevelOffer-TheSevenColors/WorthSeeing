package kr.worthseeing.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.service.CouponService;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.mypage.service.MyPageService;
import kr.worthseeing.security.config.SecurityUser;

@Controller
public class MyPageController {
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private BlockGroupService blockGroupService;
	
	@Autowired
	private MyPageService myPageService;
	
	@GetMapping("/mypageMain")
	public String getmypage(Model model,@AuthenticationPrincipal SecurityUser principal) {
		List<BlockGroup> BGL = blockGroupService.getListBlockGroup(principal.getUsers().getUserId());
		model.addAttribute("users",principal.getUsers().getUserId());
		model.addAttribute("BGL",BGL);
		System.out.println("=======================>"+principal.getUsers().getUserId());
		System.out.println("=============BGLBGLBGLBGLBGL==========>"+BGL);
		
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
		List<BlockGroup> blockGroupList = blockGroupService.getListBlockGroup(principal.getUsers().getUserId());
		model.addAttribute("auctionList",auctionList);
		model.addAttribute("users",principal.getUsers());
		model.addAttribute("blockGroupList",blockGroupList);
		
		return "/mypagePurchaseHistory";
	}
	
	@GetMapping("/click")
	public String getClick(BlockGroup blockGroup, @AuthenticationPrincipal SecurityUser principal) {
		myPageService.getClick(blockGroup, principal);
		
		return "/main";
		
	}

}
