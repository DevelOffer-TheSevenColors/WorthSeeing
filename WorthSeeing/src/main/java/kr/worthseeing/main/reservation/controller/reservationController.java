package kr.worthseeing.main.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.security.config.SecurityUser;

@Controller
@RequestMapping("/reservation")
public class reservationController {

	@Autowired
	private ReservationService reservationservice;

	@Autowired
	private BlockService blockService;

	// 예약가능 목록 띄우기
	@RequestMapping("/reservationList")
	private String selectauctonList(Model model, Reservation reservation, @PageableDefault Pageable pageable) {
		
		model.addAttribute("reservationList", reservationservice.selectReservation(pageable).get("reservationList"));
		
		model.addAttribute("auctionFlag", reservationservice.selectReservation(pageable).get("flag"));
		
		return "/reservation/reservationList";
	}

	// 나의 예약가능 목록 띄우기
	@GetMapping("/myAuctionList")
	private String selectMyAuctionList(Model model, Reservation reservation,
			@AuthenticationPrincipal SecurityUser principal) {

		List<ReservationUsers> reservationUsersList = reservationservice
				.selectMyReservation(principal.getUsers().getUserId());
		model.addAttribute("principal", principal);
		model.addAttribute("reservationUsersList", reservationUsersList);

		return "/reservation/myAuctionList";
	}

	// 예약하기 눌리면 10프로 만 결제하는 창으로 이동
	@GetMapping("/reservationCreditView")
	private String reservationCredit(Model model, Reservation reservation) {
		Reservation reservation_ = reservationservice.selectReservationCreditInfo(reservation);
		
		String blockStr = "";
		for (Block block : blockService.findAuctionBlock(reservation_.getBlockGroup())) {
			blockStr += block.getBlock_seq() + ",";
		}
		
		model.addAttribute("reservationCreditInfo", reservation_);
		model.addAttribute("block", blockStr.substring(0, blockStr.length() - 1));
		
		return "/reservation/reservationCreditView";
	}

	// 10프로 결제하기 버튼 클릭 시
	@PostMapping("/insertReservation")
	private String insertReservation(Reservation reservation, @AuthenticationPrincipal SecurityUser principal) {
		reservationservice.insertReservationUsers(reservation, principal.getUsers().getUserId());

		return "redirect:/reservation/reservationList";
	}

	// 10프로 결제하기 버튼 클릭 시
	@GetMapping("/delectReservation")
	private String deleteReservation(Reservation reservation, @AuthenticationPrincipal SecurityUser principal,
			ReservationUsers reservationUsers) {

		reservationservice.deleteReservationUsers(reservation, principal.getUsers().getUserId(), reservationUsers);

		return "redirect:/reservation/myAuctionList";
	}
}
