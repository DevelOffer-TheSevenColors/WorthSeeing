package kr.worthseeing.event.coupon.service;

import java.util.List;

import kr.worthseeing.event.coupon.entity.Coupon;

public interface CouponService {
	
	void insertCoupon(Coupon coupon);
	
	List<Coupon> listCoupon();
	
}
