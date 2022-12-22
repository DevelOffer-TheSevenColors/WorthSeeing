package kr.worthseeing.event.pointlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.pointlog.entity.PointLog;
import kr.worthseeing.main.auction.entity.Auction;

public interface PointLogRepository  extends CrudRepository<PointLog, Integer>,
QuerydslPredicateExecutor<BlockGroup>{
	
	@Query("select c from Coupon c where status_seq = ?1")
	List<Coupon> findByCoupon();
 
}
