package kr.worthseeing.event.coupon.service;

import kr.worthseeing.event.coupon.entity.Coupon;

public interface CouponService {
	
	void insertCoupon(Coupon coupon);
	
	Coupon getCoupon(Coupon coupon);
	
	Coupon listCoupon();
	

}
