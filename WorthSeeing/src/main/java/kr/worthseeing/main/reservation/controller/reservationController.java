package kr.worthseeing.main.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUserId;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.users.entity.Users;

@Controller
@RequestMapping("/reservation")
public class reservationController {

	@Autowired
	private ReservationService reservationservice;

	@Autowired
	private BlockGroupService blockGroupService;

	// 예약가능 목록 띄우기
	@GetMapping("/auctionList")
	private String selectauctonList(Model model, Reservation reservation) {

		List<Reservation> reservationList = reservationservice.selectReservation(reservation);
		model.addAttribute("reservationList", reservationList);

		return "/reservation/auctionList";
	}

	// 나의 예약가능 목록 띄우기
	@GetMapping("/myAuctionList")
	private String selectMyAuctionList(Model model, Reservation reservation,
			@AuthenticationPrincipal SecurityUser principal) {

		List<Reservation> reservationList = reservationservice.selectReservation(reservation);
		model.addAttribute("principal", principal);
		model.addAttribute("reservationList", reservationList);

		return "/reservation/auctionList";
	}

	// 예약하기 눌리면 10프로 만결제하는 창으로 이동
	@GetMapping("/reservationCredit")
	private String reservationCredit(Model model, Reservation reservation,
			@AuthenticationPrincipal SecurityUser principal) {

		model.addAttribute("reservationCreditInfo", reservationservice.selectReservationCreditInfo(reservation));
		model.addAttribute("principal", principal);
		return "/reservation/reservationCredit";
	}

	@PostMapping("/insertReservation")
	private String insertReservation(Reservation reservation, @AuthenticationPrincipal SecurityUser principal) {

		reservationservice.insertReservation(reservation, principal.getUsers());

		return "redirect:/reservation/auctionList";
	}
}
