package kr.worthseeing.main.auction.service;

import java.util.List;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

public interface AuctionService {

	void insertAuctionLog(AuctionLog auctionLog);
	
	void deleteAuctionLog(AuctionLog auctionLog);
	
	void updateAuction(Auction auction, Reservation reservation);
	
	void updateMaxPrice(Reservation reservation, String maxPrice, Users user);
	
	public List<Auction> listAuction();
	
	
	// Auction listAuction(Auction auction);
	
	void insertAuction(Auction auction);
	
	void deleteAuction(Auction auction);

	//예약 결제 정보
	Auction selectCredit(Auction auction);;
	
	public Auction findAuction(Reservation reservation);
	
	//페이지 작성 후 최종구매할 때
		void updateCreditInfo(BlockGroupWaiting blockGroupWaiting ,Status status, Users user);
	
	void autoAuction(Reservation reservation);
	
	//결제하기 회원 블록 가격 정보 띄우기 
	BlockGroupWaiting auctionCreditView(BlockGroupWaiting blockGroupWaiting);
	
	
	public void endAuction(Reservation reservation, BlockGroup blockGroup);
}
