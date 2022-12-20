package kr.worthseeing.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<BlockGroup> BlockGroupUserId = myPageService.getBlockGroupUserId(principal.getUsers().getUserId());
//		List<BlockGroup> BlockGroupList = myPageService.getListBlockGroup();
		model.addAttribute("users",principal.getUsers());
		model.addAttribute("userId",principal.getUsers().getUserId());
		model.addAttribute("BlockGroupUserId",BlockGroupUserId);
//		System.out.println("=======================>"+principal.getUsers().getUserId());
		return "/mypageMain";
	}
	
	
	@GetMapping("/mypageCouponMall")
	public String getmypageCouponMall() {
		return "/mypageCouponMall";
	}
	
	@GetMapping("/mypagePointHistory")
	public String getlistcoupon(Model model,@AuthenticationPrincipal SecurityUser principal) {
		List<Coupon> couponUserId = myPageService.getCouponUserId(principal.getUsers().getUserId());
		model.addAttribute("couponUserId", couponUserId);
		model.addAttribute("users", principal.getUsers());
		
		return "/mypagePointHistory";
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
		List<BlockGroup> blockGroupUserId = myPageService.getBlockGroupUserId(principal.getUsers().getUserId());
		model.addAttribute("userId",principal.getUsers().getUserId());
		model.addAttribute("blockGroupUserId",blockGroupUserId);
		
		return "/mypagePurchaseHistory";
	}
	
	// 클릭 시 db에 저장된 url로 이동 추가
	@GetMapping("/click")
	public String getClick(BlockGroup blockGroup, 
			@AuthenticationPrincipal SecurityUser principal,
			@RequestParam("blockGroup_seq") int paramBlockGroupSeq) {
		myPageService.getClick(blockGroup, principal);

		BlockGroup blockGroupSeq = blockGroupService.findBlockGroup(blockGroup);
	    if (blockGroupSeq != null && blockGroupSeq.getBlockGroup_seq() == paramBlockGroupSeq) {
	        return "redirect:" + blockGroupSeq.getLinkUrl();
	    } else {
	        return null;
	    }
		
	}

}
