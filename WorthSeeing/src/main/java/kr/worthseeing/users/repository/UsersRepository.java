package kr.worthseeing.users.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.users.entity.Users;


public interface UsersRepository  extends CrudRepository<Users, String>,QuerydslPredicateExecutor<Users>{
 
	@Query("select u From Users u")
	Page<Users> findAll(Pageable pageable);
	
	@Query(value=" update Users u set daily_Click = 0 ", nativeQuery = true)
	void updateUsersPoint();
	
	@Query("select u from Users u where email = ?1")
	List<Users> findUser(String email);
	
	@Query("select u From Users u")
	List<Users> findUserPoint(String userId);
}