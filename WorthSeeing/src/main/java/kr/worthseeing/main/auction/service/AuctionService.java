package kr.worthseeing.main.auction.service;

import kr.worthseeing.main.auction.entity.AuctionLog;

public interface AuctionService {

	void insertAuctionLog(AuctionLog auctionLog);
	
	void deleteAuctionLog(AuctionLog auctionLog);
}
