package kr.worthseeing.mypage.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.security.config.SecurityUser;

public interface MyPageService {

	void getMyPage();
	
	void getClick(BlockGroup blockGroup,@AuthenticationPrincipal SecurityUser principal);
	
	List<BlockGroup> getBlockGroupUserId(String userId);
	
	List<Coupon> getCouponUserId(String userId);
	
	List<BlockGroup> getListBlockGroup();
	
	
	
}
