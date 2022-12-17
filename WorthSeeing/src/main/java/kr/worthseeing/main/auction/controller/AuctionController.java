package kr.worthseeing.main.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.service.AuctionService;

@Controller
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	// 경매 페이지로 이동
	@GetMapping("/auction")
	public String Auction(Auction auction, Model model) {
		List<Auction> auctionList = auctionService.getlistAuction();
		System.out.println("====>" + auctionList.toString());
		model.addAttribute("auctionList", auctionList);
		return "auction";
	}

	// 입찰 버튼 클릭 시 경매 업데이트
	@PostMapping("/bidding")
	public String bidding(Auction auction) {
		auctionService.updateAuction(auction);
		return "auction";
	}

	// 경매 종료
	@GetMapping("/deleteAuction")
	public String deleteAuction(Auction auction) {
		auctionService.deleteAuction(auction);
		return "redirect:/main";
	}

	// 경매중 리스트 페이지
	@GetMapping("/myAuctionList")
	public String getAuctionList(Model model) {
		List<Auction> auctionList = auctionService.getlistAuction();
		model.addAttribute("auctionList", auctionList);
		return "myAuctionList";
	}

	// 결제할때 페이지 정보 불러오기
	@GetMapping("seletCredit")
	public String selectCredit(Model model, Auction auction) {
		auctionService.selectCredit(auction);

		model.addAttribute("reservationList", auctionService.selectCredit(auction));

		return "/mypageMain";
	}

}
