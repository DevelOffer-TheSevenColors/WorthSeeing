package kr.worthseeing.event.coupon.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;

public interface CouponRepository  extends CrudRepository<Coupon, Integer>{
 
}
