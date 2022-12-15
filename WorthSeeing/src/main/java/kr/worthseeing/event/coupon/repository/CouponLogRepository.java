package kr.worthseeing.event.coupon.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.entity.CouponLog;

public interface CouponLogRepository  extends CrudRepository<CouponLog, Integer>{
 
}
