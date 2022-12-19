package kr.worthseeing.status.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class StatusController {

	@Scheduled(cron = "0 00 00 1 * ?")
	public void auctionNotify() {
		// 매월 1일 00시 00분 블록목록 초기화
	}

	@Scheduled(cron = "0 00 12 20 * ?")
	public void auctionReservation() {
		// 매월 20일 12시 00분 예약 공지
	}

	@Scheduled(cron = "0 00 12 23 * ?")
	public void auctionStart() {
		// 매월 23일 경매시작
	}

}
