package kr.worthseeing.blockGroupWaiting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.main.auction.entity.AuctionLog;

public interface BlockGroupWaitingRepository  extends CrudRepository<BlockGroupWaiting, Integer>,QuerydslPredicateExecutor<AuctionLog>{
 
	
	@Query("SELECT a FROM BlockGroupWaiting a where user_id =?1 ")
	List<BlockGroupWaiting> selectBlockGroupWaiting(String userId);
}
