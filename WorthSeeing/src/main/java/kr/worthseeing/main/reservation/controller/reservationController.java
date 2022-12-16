package kr.worthseeing.main.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.service.ReservationService;

@Controller
@RequestMapping("/reservation")
public class reservationController {
	
	
	
	@Autowired
	private ReservationService reservationservice;
	
	
	//예약 가능 목록 띄우기
	@GetMapping("/auctionList")
	private String selectBoard(Model model, Reservation reservation) {
		
//		if(reservation.getReservation_seq() == 0) {
//	
//			return "redirect:/";
//		}
		
		List<Reservation> reservationList = reservationservice.selectReservation(reservation);
		model.addAttribute("reservationList",reservationList);
		
		System.out.println(reservationList);
		
		return "/reservation/auctionList";
	}

	@GetMapping("/insertReservation")
	private String insertReservation( Reservation reservation) {
		
		
		 reservationservice.insertReservation(reservation);
		
		return "/reservation/auctionList";
	}
	
}
