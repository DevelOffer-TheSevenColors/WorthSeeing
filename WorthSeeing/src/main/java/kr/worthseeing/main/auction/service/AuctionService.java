package kr.worthseeing.main.auction.service;

import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.users.entity.Users;

public interface AuctionService {

	void insertAuctionLog(AuctionLog auctionLog);
	
	void deleteAuctionLog(AuctionLog auctionLog);
	
	void updateAuction(Auction auction);
	
	// Auction listAuction(Auction auction);
	
	void insertAuction(Auction auction);
	
	void deleteAuction(Auction auction);

	//결제페이지에서 블록정보 가져올떄
	Auction selectCredit(Auction auction);
	
	public Auction findAuction(Auction auction);
	
	//페이지 작성후 등록할 때
	void updateCreditInfo(Users users, Auction auction);
	
	
	//결제하기 회원 블록 가격 정보 띄우기 
	void auctionCreditView(Auction auction,Users users);
}
