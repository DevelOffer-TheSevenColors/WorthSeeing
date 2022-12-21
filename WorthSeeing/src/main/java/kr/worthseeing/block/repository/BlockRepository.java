package kr.worthseeing.block.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;

public interface BlockRepository  extends CrudRepository<Block, Integer>,QuerydslPredicateExecutor<Block>{
 
	@Query("SELECT b FROM Block b Where block_Group_seq = ?1")
	List<Block> findAuctionBlock(String keyword);
	
}
