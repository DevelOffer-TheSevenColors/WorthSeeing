package kr.worthseeing.event.coupon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.event.coupon.entity.Coupon;

public interface CouponRepository  extends CrudRepository<Coupon, Integer>, 
	QuerydslPredicateExecutor<Coupon> {
	
	
	@Query("select c from Coupon c where c.users.userId like %?1%")
	Page<Coupon> findByUserPage(String userId,Pageable pageable);
	
	@Query("select c from Coupon c where c.users.userId like %?1%")
	List<Coupon> findByUserId(String userId);
	
	@Query(value="select c from Coupon c where c.status.status_seq = ?1 and couponPrice = ?2")
	List<Coupon> findByCoupon(int status_seq, int couponPrice);
	
	@Query(value="select c from Coupon c where c.status.status_seq = 5")
	List<Coupon> findLeftOverCoupon();
	
	@Query("select c from Coupon c where c.status.status_seq = 5")
	Page<Coupon> findCouponList(Pageable pageable);
	
	
}
