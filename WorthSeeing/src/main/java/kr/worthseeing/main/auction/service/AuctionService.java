package kr.worthseeing.main.auction.service;

import java.util.List;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.users.entity.Users;

public interface AuctionService {

	void insertAuctionLog(AuctionLog auctionLog);
	
	void deleteAuctionLog(AuctionLog auctionLog);
	
	void updateAuction(Auction auction);
	
	// Auction listAuction(Auction auction);
	
	void insertAuction(Auction auction);
	
	void deleteAuction(Auction auction);
	
	List<Auction> getlistAuction();

	//결제페이지에서 블록정보 가져올떄
	Auction selectCredit(Auction auction);
	
	
	//페이지 작성후 등록할 때
	void updateCreditInfo(Users users, Auction auction);
}
