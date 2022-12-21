package kr.worthseeing.main.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.main.auction.entity.AuctionLog;

public interface AuctionLogRepository  extends CrudRepository<AuctionLog, Integer>,
QuerydslPredicateExecutor<AuctionLog>  {
	
	@Query("select a from AuctionLog a where a.userId like ?1 and a.status_seq =?2")
	List<AuctionLog> findByUserId(String userId, int status_seq);
	
	
}
