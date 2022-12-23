package kr.worthseeing.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.service.CouponService;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.message.dto.MessageDTO;
import kr.worthseeing.mypage.service.MyPageService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.status.entity.Status;
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

	@Autowired
	private MyPageService myPageService;

	@Autowired
	private UsersService usersService;
	
	

	@GetMapping("/mypageMain")
	public String getmypage(Model model, @AuthenticationPrincipal SecurityUser principal) {
		
		List<BlockGroup> blockGroupUserId = myPageService.getBlockGroupUserId(principal.getUsers().getUserId());

		model.addAttribute("users", myPageService.getUsers(principal.getUsers()));
		
		model.addAttribute("BlockGroupUserId", blockGroupUserId);
		System.out.println("controller Users" + myPageService.getUsers(principal.getUsers()));
		return "/mypageMain";
	}
	
	@GetMapping("/buyCouponProc")
	public String buyCouponProc(Model model,@AuthenticationPrincipal SecurityUser principal, String price,Coupon coupon) {
		if (price != null) {
			myPageService.getUserPoint(principal.getUsers(), price, coupon);
		}
		return "redirect:/mypageMain";
	}
	

	@GetMapping("/mypageCouponMall")
	public String getmypageCouponMall(Model model, @AuthenticationPrincipal SecurityUser principal) {
		List<Coupon> couponList = myPageService.getCouponUserId(principal.getUsers().getUserId());
		model.addAttribute("couponList", couponList);
		model.addAttribute("users", myPageService.getUsers(principal.getUsers()));
		return "/mypageCouponMall";
	}

	@GetMapping("/mypagePointHistory")
	public String getlistcoupon(Model model, @AuthenticationPrincipal SecurityUser principal,@PageableDefault Pageable pageable) {
		Page<Coupon> couponUserId = myPageService.getCouponUserPage(principal.getUsers().getUserId(),pageable);
		model.addAttribute("couponUserId", couponUserId);
		model.addAttribute("users", principal.getUsers());

		return "/mypagePointHistory";
	}
	
	@GetMapping("/leftOverCoupon")
	public String getcouponcount(Model model,Coupon coupon,@PageableDefault Pageable pageable) {
		Page<Coupon> leftOverCouponList = myPageService.leftOverCouponPage(coupon,pageable);
		List<Integer> leftOverCouponCount = myPageService.getCouponCount();
		model.addAttribute("leftOverCouponList",leftOverCouponList);
		model.addAttribute("leftOverCouponCount",leftOverCouponCount);
		return "/adminCoupon";
	}
	
	
	@GetMapping("/system/mypageUpdate")
		public String mypageUpdate(@AuthenticationPrincipal SecurityUser principal, Model model) {
			model.addAttribute("users", myPageService.getUsers(principal.getUsers()));
			return "/system/mypageUpdate";
		}
	
	
	@PostMapping("/system/mypageUpdateProc")
	public String mypageUpdateProc(Users users, Model model) {
		myPageService.userUpdateProc(users);
		return "redirect:/mypageMain";
	}
	
	
	

//	@GetMapping("/mypageAuctionHistory")
//	public String getmypageAuctionHistory(Model model,@AuthenticationPrincipal SecurityUser principal) {
//		
//		Map<Integer, List<AuctionLog>> auctionLogUserIdMap = myPageService
//				.getAuctionLogUserId(principal.getUsers().getUserId());
//		model.addAttribute("successedAuctionList", auctionLogUserIdMap.get(1)); // 낙찰
//		model.addAttribute("failedAuctionList", auctionLogUserIdMap.get(2)); // 입찰
//		
//		return "/mypageAuctionHistory";
//	}

	@GetMapping("/mypageAuctionHistory")
	public String getmypageAuctionHistory(Model model, @AuthenticationPrincipal SecurityUser principal, Status status,@PageableDefault Pageable pageable) {

		myPageService.selectBlockGroupWaiting(principal.getUsers().getUserId(), status.getStatus_seq(),pageable);

		model.addAttribute("waiting",
				myPageService.selectBlockGroupWaiting(principal.getUsers().getUserId(), status.getStatus_seq(),pageable));

		return "/mypageAuctionHistory";
	}

	@GetMapping("/mypagePurchaseHistory")
	public String getmyagePurchaseHistory(Model model, @AuthenticationPrincipal SecurityUser principal,@PageableDefault Pageable pageable) {
		Page<BlockGroup> blockGroupUserId = myPageService.getBlockGroupPage(principal.getUsers().getUserId(),pageable);
		model.addAttribute("userId", principal.getUsers().getUserId());
		model.addAttribute("blockGroupUserId", blockGroupUserId);

		return "/mypagePurchaseHistory";
	}

	// 클릭 시 db에 저장된 url로 이동 추가
	@GetMapping("/updatePoint")
	public String getClick(BlockGroup blockGroup, @AuthenticationPrincipal SecurityUser principal, Model model) {
		myPageService.add500Point(principal.getUsers());

		MessageDTO message = new MessageDTO("500Pt 지급 완료!!!", "/click?blockGroup_seq=" + blockGroup.getBlockGroup_seq(),
				RequestMethod.GET, null);

		return showMessageAndRedirect(message, model);
	}

	@GetMapping("/click")
	public String getClick(BlockGroup blockGroup) {
		System.out.println("controller--->" + blockGroup.getBlockGroup_seq());
		return "redirect:" + myPageService.getClick(blockGroup);
	}

	private String showMessageAndRedirect(final MessageDTO params, Model model) {
		model.addAttribute("params", params);
		return "/common/messageRedirect";
	}

}