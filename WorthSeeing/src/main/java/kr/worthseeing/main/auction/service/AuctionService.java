package kr.worthseeing.main.auction.service;

import java.util.List;

import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;

public interface AuctionService {

	void insertAuctionLog(AuctionLog auctionLog);
	
	void deleteAuctionLog(AuctionLog auctionLog);
	
	void updateAuction(Auction auction);
	
	// Auction listAuction(Auction auction);
	
	void insertAuction(Auction auction);
	
	void deleteAuction(Auction auction);
	
	List<Auction> getlistAuction();
}
