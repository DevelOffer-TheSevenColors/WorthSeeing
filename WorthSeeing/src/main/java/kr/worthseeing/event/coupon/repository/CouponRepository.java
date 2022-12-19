package kr.worthseeing.event.coupon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;

public interface CouponRepository  extends CrudRepository<Coupon, Integer>, 
	QuerydslPredicateExecutor<Coupon> {
	
	
	@Query("select c from Coupon c where c.users.userId like %?1%")
	List<Coupon> findByUserId(String userId);
 
}
