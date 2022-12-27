package kr.worthseeing.event.coupon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.event.coupon.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponRepository couponRepo;
	
	@Override
	public void insertCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	}
	
	@Override
	public List<Coupon> listCoupon() {
		return (List<Coupon>) couponRepo.findAll();
	}
}
