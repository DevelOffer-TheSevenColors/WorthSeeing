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

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.users.entity.Users;

@Controller
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	@ResponseBody // ajax를 불르기 위한 어노테이션
	@RequestMapping(value = "/auction/selectPrice", method = RequestMethod.POST)
	public int selectPrice(Auction auction) throws Throwable {
		return auctionService.findAuctionPrice(auction);
	}

	// 경매 페이지로 이동
	@GetMapping("/auction")
	public String Auction(Model model,Auction auction) {
		model.addAttribute("auction",auctionService.selectAuction(auction));
		String block = "";
		for(Block block_ : auctionService.selectAuction(auction).getReservation().getBlockGroup().getBlockList()) {
			block += String.valueOf(block_.getBlock_seq()) + ", ";
		}
		block = block.substring(0, block.length()-2);
		model.addAttribute("block", block);
		return "/auction";
	}
	// 낙착되어서 결제하러 갈떄
	@GetMapping("/credit")
	public String AuctionCredit(Auction auction) {
		return "/credit";
	}
	
	

	// 입찰 버튼 클릭 시 경매 업데이트
	@PostMapping("/bidding")
	public String bidding(Auction auction, @AuthenticationPrincipal SecurityUser principal) {
		auction.setUsers(principal.getUsers());
		if(auctionService.findAuctionPrice(auction)<auction.getSuggestPrice())
		auctionService.updateAuction(auction);
		return "/auction";
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
		return "/myAuctionList";
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