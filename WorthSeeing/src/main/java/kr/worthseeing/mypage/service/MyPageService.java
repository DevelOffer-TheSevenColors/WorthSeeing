package kr.worthseeing.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.users.entity.Users;

public interface MyPageService {

	void getMyPage();
	
	void getClick(BlockGroup blockGroup, Users users);
	
	List<BlockGroup> getBlockGroupUserId(String userId);
	
	List<Coupon> getCouponUserId(String userId);
	
	Map<Integer, List<AuctionLog>> getAuctionLogUserId(String userId);
	
	List<BlockGroup> getListBlockGroup();
	
	
	
}
