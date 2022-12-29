package kr.worthseeing.block.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;

public interface BlockRepository extends CrudRepository<Block, Integer>, QuerydslPredicateExecutor<Block> {

	@Query("SELECT b FROM Block b Where block_Group_Waiting_seq = ?1")
	List<Block> findAuctionBlock(String keyword);

	@Query("select b from Block b where status_seq = 12")
	Page<Block> alwaysBuyList(Pageable pageable);

	@Query("select b.blockPrice from Block b where status_seq = 12")
	List<Integer> alwaysBuyListGetPrice();

	@Query("select b from Block b where status_seq = ?1")
	List<Block> alwaysBuyListNoPage(int keywoard);

	@Query("select b from Block b where b.blockGroup.blockGroup_seq = ?1")
	List<Block> findBlockGroupSeqFromBlock(int blockGroup_seq);

	@Query("select b from Block b order by block_seq")
	List<Block> listblock();

	@Modifying
	@Query("UPDATE Block b SET Status_seq = 12 WHERE block_group_Waiting_seq = null")
	void updateBlockStatus();

	@Modifying
	@Transactional
	@Query("UPDATE Block b SET block_group_Waiting_seq = 1 WHERE block_seq = ?1")
	void updateBlock_GroupWaitingSeq(int block_seq);
	
	@Modifying
	@Transactional
	@Query("UPDATE Block b SET status_seq = 8 WHERE block_seq = ?1")
	void updateBlock_StatusSeq(int block_seq);
	
	@Query("select b.block_seq from Block b where status_seq = 8 or status_seq = 10 order by block_seq")
	List<Integer> availableGroupingblock();

	
}
