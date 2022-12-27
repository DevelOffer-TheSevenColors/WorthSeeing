package kr.worthseeing.block.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;

public interface BlockRepository  extends CrudRepository<Block, Integer>,QuerydslPredicateExecutor<Block>{
 
	@Query("SELECT b FROM Block b Where block_Group_Waiting_seq = ?1")
	List<Block> findAuctionBlock(String keyword);
	
	@Query("select b from Block b where status_seq = 11")
	Page<Block> alwaysBuyList(Pageable pageable);
	
	@Query("select b.blockPrice from Block b where status_seq = 11")
	List<Integer> alwaysBuyListGetPrice();
	
	@Query("select b from Block b where status_seq = ?1")
	List<Block> alwaysBuyListNoPage(int keywoard);
	
	
}
