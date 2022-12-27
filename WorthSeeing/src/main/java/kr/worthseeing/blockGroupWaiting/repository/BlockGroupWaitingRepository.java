package kr.worthseeing.blockGroupWaiting.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.main.auction.entity.AuctionLog;

public interface BlockGroupWaitingRepository  extends CrudRepository<BlockGroupWaiting, Integer>,QuerydslPredicateExecutor<AuctionLog>{
	
	@Query("SELECT a FROM BlockGroupWaiting a ")
	Page<BlockGroupWaiting> selectBlockGroupWaiting(String userId,Pageable pageable);
	
	@Query("select b from BlockGroupWaiting b ")
	Page<BlockGroupWaiting> findByUserList(String userId,Pageable pageable);

	@Query("select b from BlockGroupWaiting b where blockGroupWaiting_seq = ?1")
	BlockGroupWaiting endAuctionConfirm(int blockgroup_seq);
	
	@Query("select b from BlockGroupWaiting b where status_seq = 8")
	Page<BlockGroupWaiting> listBlockGroupWaiting(Pageable pageable);
	
	@Query("select b from BlockGroupWaiting b")
	List<BlockGroupWaiting> findByUserId(String userId);
	
	@Query("select max(b.blockGroupWaiting_seq) from BlockGroupWaiting b ")
	int getMaxBlockGroupWaitingSeq();
	
	@Query("select b.blockGroupWaiting_seq from BlockGroupWaiting b order by b.blockGroupWaiting_seq")
	List<Integer> listBlockGroupWaitingSeq();
	
	@Query("select b.cImg from BlockGroupWaiting b order by b.blockGroupWaiting_seq")
	List<String> listcImg();
	
	@Query("select b from BlockGroupWaiting b order by b.blockGroupWaiting_seq")
	List<BlockGroupWaiting> orderByBlockGroupWaitingSeq();

}
