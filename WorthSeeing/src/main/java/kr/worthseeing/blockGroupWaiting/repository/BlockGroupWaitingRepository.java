package kr.worthseeing.blockGroupWaiting.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.main.auction.entity.AuctionLog;

public interface BlockGroupWaitingRepository  extends CrudRepository<BlockGroupWaiting, Integer>,QuerydslPredicateExecutor<AuctionLog>{
	
	@Query("SELECT a FROM BlockGroupWaiting a where user_Id=?1")
	Page<BlockGroupWaiting> selectBlockGroupWaiting(String userId,Pageable pageable);
	
	@Query("select b from BlockGroupWaiting b ")
	Page<BlockGroupWaiting> findByUserList(String userId,Pageable pageable);

	@Query("select b from BlockGroupWaiting b where block_Group_Waiting_seq = ?1")
	BlockGroupWaiting endAuctionConfirm(int blockgroupWaiting_seq);
	
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

	@Query("DELETE FROM BlockGroupWaiting b Where block_group_waiting_seq != 1")
	@Modifying
	@Transactional
	void deleteBlockGroupWaiting();
	
}
