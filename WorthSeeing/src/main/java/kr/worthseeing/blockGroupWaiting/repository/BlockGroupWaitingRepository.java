package kr.worthseeing.blockGroupWaiting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.entity.BlockGroupLog;
import kr.worthseeing.main.auction.entity.AuctionLog;

public interface BlockGroupWaitingRepository  extends CrudRepository<BlockGroupWaiting, Integer>,QuerydslPredicateExecutor<AuctionLog>{
 
//	@Query("select a from AuctionLog a where a.userId like ?1 and a.status_seq =?2")
//	List<AuctionLog> findByUserId(String userId, int status_seq);
	
	
//	@Query("select a from BlcokGroupWaiting a where a.userId like ?1 and a.reservation_seq=?2")
//	List<BlockGroupWaiting> selectBlockGroupWaiting(String userId, int reservation_seq);
}
