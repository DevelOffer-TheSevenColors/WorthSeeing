package kr.worthseeing.main.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;
import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.security.config.SecurityUser;
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
	public String Auction(Model model, Auction auction, @AuthenticationPrincipal SecurityUser principal) {
		model.addAttribute("user", principal.getUsers());
		return "/auction";
	}

	// 낙착되어서 결제하러 갈떄
	@GetMapping("/credit")
	public String AuctionCredit(Auction auction) {
		return "/credit";
	}

	// 입찰 버튼 클릭 시 경매 업데이트
	@PostMapping("/bidding")
	public String bidding(Reservation reservation, String suggestPrice, String cPrice,
			@AuthenticationPrincipal SecurityUser principal, String autoPrice) {
		System.out.println("@@aa@@");
		Auction auction = null;
		if (autoPrice.equals("")) {
			auction = new Auction();
			auction.setSuggestPrice(Integer.parseInt(suggestPrice));
			if (Integer.parseInt(cPrice) < auction.getSuggestPrice()) {
				auction.setUsers(principal.getUsers());
				auctionService.updateAuction(auction, reservation);
			}
		}
		else {
			auctionService.updateMaxPrice(reservation, autoPrice,principal.getUsers());
		}
		return "/auction";
	}

	// 경매 종료
	@GetMapping("/deleteAuction")
	public String deleteAuction(Auction auction) {
		auctionService.deleteAuction(auction);
		return "redirect:/main";
	}

	// 결제할때 페이지 정보 불러오기
	@GetMapping("/seletCredit")
	public String selectCredit(Model model, Auction auction) {
		auctionService.selectCredit(auction);

		model.addAttribute("reservationList", auctionService.selectCredit(auction));

		return "/mypageMain";
	}

	// 결제 할 때 페이지 넘기는 값들 ㅎㅎ
	@PostMapping("/updateCredit")
	public String updateCredit(Users users, Auction auction) {

		auctionService.updateCreditInfo(users, auction);
		return "/";
	}
}