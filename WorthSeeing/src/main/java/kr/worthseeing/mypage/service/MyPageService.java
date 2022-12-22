package kr.worthseeing.mypage.service;

import java.util.List;
import java.util.Map;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.users.entity.Users;

public interface MyPageService {

	Users getUsers(Users users);

	void add500Point(Users users);

	String getClick(BlockGroup blockGroup);

	List<BlockGroup> getBlockGroupUserId(String userId);

	List<Coupon> getCouponUserId(String userId);

	Map<Integer, List<AuctionLog>> getAuctionLogUserId(String userId);

	List<BlockGroupWaiting> selectBlockGroupWaiting(String userId, int status_seq);

	List<BlockGroup> getListBlockGroup();

	void getUserPoint(Users users, String price);

	void updateCoupon(Users users,String price, Coupon coupon);

}
