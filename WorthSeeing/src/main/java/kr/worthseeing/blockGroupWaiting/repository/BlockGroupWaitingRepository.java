package kr.worthseeing.blockGroupWaiting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.main.auction.entity.AuctionLog;

public interface BlockGroupWaitingRepository  extends CrudRepository<BlockGroupWaiting, Integer>,QuerydslPredicateExecutor<AuctionLog>{
 
	
//	@Query("SELECT a FROM BlockGroupWaiting a where user_id =?1 ")
//	List<BlockGroupWaiting> selectBlockGroupWaiting(String userId);
	
	@Query("SELECT a FROM BlockGroupWaiting a where user_id =?1 ")
	Page<BlockGroupWaiting> selectBlockGroupWaiting(String userId,Pageable pageable);
}
