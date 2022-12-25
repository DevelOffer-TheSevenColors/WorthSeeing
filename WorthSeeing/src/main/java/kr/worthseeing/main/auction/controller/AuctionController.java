package kr.worthseeing.main.auction.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

@Controller
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private BlockService blockService;
	
	

	@ResponseBody // ajax를 불르기 위한 어노테이션
	@RequestMapping(value = "/auction/selectAuction", method = RequestMethod.POST)
	public Auction selectAuction(Reservation reservation) throws Throwable {
		return auctionService.findAuction(reservation);
	}

	@ResponseBody // ajax를 불르기 위한 어노테이션
	@RequestMapping(value = "/auction/selectBlock", method = RequestMethod.POST)
	public List<Block> selectBlock(Reservation reservation) throws Throwable {
		return blockService
				.findAuctionBlock(reservationService.selectReservationCreditInfo(reservation).getBlockGroup());
	}

	@ResponseBody // ajax를 불르기 위한 어노테이션
	@RequestMapping(value = "/auction/autoBidding", method = RequestMethod.POST)
	public String autoBidding(Reservation reservation) throws Throwable {
		auctionService.autoAuction(reservation);
		return "success";
	}

	@ResponseBody // ajax를 불르기 위한 어노테이션
	@RequestMapping(value = "/auction/endAuction", method = RequestMethod.POST)
	public String endAuction(Reservation reservation) throws Throwable {
		auctionService.endAuction(reservation, 
				reservationService.selectReservationCreditInfo(reservation).getBlockGroup());
		return "end";
	}

	// 경매 페이지로 이동
	@GetMapping("/auction")
	public String Auction(Model model, Auction auction,Reservation reservation, @AuthenticationPrincipal SecurityUser principal) {
		model.addAttribute("user", principal.getUsers());
		model.addAttribute("reservation_seq",reservation.getReservation_seq());
		return "/auction";
	}

	// 낙찰해서 결제하러 갈때
	@PostMapping("/credit")
	public String AuctionCredit(Model model, BlockGroupWaiting blockGroupWaiting,@AuthenticationPrincipal SecurityUser principal) {
		
		System.out.println("====>1"+blockGroupWaiting);
		auctionService.auctionCreditView(blockGroupWaiting);
		
		model.addAttribute("blockGroupWaiting",auctionService.auctionCreditView(blockGroupWaiting));
		
		model.addAttribute("users",principal.getUsers());
		
		return "/credit";
	}

	@GetMapping("/autoBiddingStop")
	public String autoBiddingStop(Reservation reservation) {
		auctionService.autoBiddingStop(reservation);
		return "redirect:/auction?reservation_seq="+reservation.getReservation_seq();
	}
	
	// 입찰 버튼 클릭 시 경매 업데이트
	@PostMapping("/bidding")
	public String bidding(Model model,Reservation reservation, String suggestPrice, String cPrice,
			@AuthenticationPrincipal SecurityUser principal, String autoPrice) {
		System.out.println("@@aa@@");
		Auction auction = null;
		System.out.println("@@Reser@@==>"+reservation.getReservation_seq());
		if (autoPrice.equals("")) {
			auction = new Auction();
			auction.setSuggestPrice(Integer.parseInt(suggestPrice));
			if (auctionService.findAuction(reservation).getSuggestPrice() < auction.getSuggestPrice()) {
				auction.setUserId(principal.getUsers().getUserId());
				auctionService.updateAuction(auction, reservation);
			}
		}
		else {
			auctionService.updateMaxPrice(reservation, autoPrice,principal.getUsers());
		}
		model.addAttribute("reservation_seq",reservation.getReservation_seq());
		return "/auction";
	}

	// 경매 종료
	@GetMapping("/deleteAuction")
	public String deleteAuction(Auction auction) {
		auctionService.deleteAuction(auction);
		return "redirect:/main";
	}

	// 예약 결제할때 페이지 정보 불러오기
	@GetMapping("/seletCredit")
	public String selectCredit(Model model, Auction auction) {
		auctionService.selectCredit(auction);
		model.addAttribute("reservationList", auctionService.selectCredit(auction));
		return "/mypageMain";
	}

//  최종결제 할 때 값 넣기
	@PostMapping("/updateCredit")
	public String updateCredit(BlockGroupWaiting blockGroupWaiting ,Status status,Users users, int month) {

		System.out.println("===>>"+month);
		
		auctionService.updateCreditInfo(blockGroupWaiting, status, users,month);
		return "/main";
	}
	
	@GetMapping("/alwaysBuyList")
	public String selectAlwaysBuyList(Model model, BlockGroup blockGroup,@PageableDefault Pageable pageable) {
		
		Page<BlockGroup> alwaysList = auctionService.selectAlwaysBuyList(blockGroup, pageable);
		
//		List<BlockGroup>  awlaysListNoPage  = auctionService.selectAlwaysBuyListNoPage();
		
		
		int nowPage = alwaysList.getPageable().getPageNumber()+1;
		model.addAttribute("alwaysList",alwaysList);
			model.addAttribute("nowPage",nowPage);
//			model.addAttribute("alwaysListNoPage",awlaysListNoPage);
			
		 
		
		return "/auction/alwaysBuyList";
	}
	@PostMapping("/alwaysBuyCreditView")
	public String alwaysBuyCreditView(Model model, BlockGroup blockGroup,@PageableDefault Pageable pageable,@AuthenticationPrincipal SecurityUser principal) {
		
		
		model.addAttribute("blockGroup",auctionService.alwaysBuyCreditView(blockGroup));
		model.addAttribute("users",principal.getUsers());
		
		return "/auction/alwaysCredit";
	}
	@PostMapping("/updateAlwaysCredit")
	public String updateAlwaysCredit(BlockGroup blockGroup, Status status,Users users) {
		
		
		auctionService.updateAlwaysCreditInfo(blockGroup, status, users);
		return "redirect:/alwaysBuyList";
	}
}






