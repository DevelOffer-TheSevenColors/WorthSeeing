package kr.worthseeing.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.users.entity.Users;

public interface MyPageService {

	Users getUsers(Users users);

	void add500Point(Users users);

	String getClick(BlockGroup blockGroup);

	Page<BlockGroup> getBlockGroupPage(String userId,Pageable pageable);

	List<Coupon> getCouponUserId(String userId);
	
	Page<Coupon> getCouponUserPage(String userId,Pageable pageable);

	Map<Integer, List<AuctionLog>> getAuctionLogUserId(String userId);

	Page<BlockGroupWaiting> selectBlockGroupWaiting(String userId, int status_seq,Pageable pageable);

	List<BlockGroup> getListBlockGroup();
	
	void getUserPoint(Users users,String price, Coupon coupon);
	
	List<Integer> getCouponCount();
	
	Page<Coupon> leftOverCouponPage(Coupon coupon,Pageable pageable);

	List<BlockGroup> getBlockGroupUserId(String userId);
	
	void userUpdateProc(Users users);
	

}
