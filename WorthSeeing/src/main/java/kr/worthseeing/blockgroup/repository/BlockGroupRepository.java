package kr.worthseeing.blockgroup.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.reservation.entity.Reservation;

public interface BlockGroupRepository extends CrudRepository<BlockGroup, Integer>,
	QuerydslPredicateExecutor<BlockGroup>  {
	
	@Query("select b from BlockGroup b")
	Page<BlockGroup> listBlockGroup(Pageable pageable);
	
	@Query("select b from BlockGroup b where b.users.userId like %?1%")
	Page<BlockGroup> findByUserId(String userId, Pageable pageable);
	
	@Query("select max(b.blockGroup_seq) from BlockGroup b ")
	int getMaxBlockGroupSeq();
	
	@Query("select b.blockGroup_seq from BlockGroup b order by b.blockGroup_seq")
	List<Integer> listBlockGroupSeq();
	
	@Query("select b.cImg from BlockGroup b order by b.blockGroup_seq")
	List<String> listcImg();
	
	@Query("select b from BlockGroup b order by b.blockGroup_seq")
	List<BlockGroup> orderByBlockGroupSeq();
	
	@Query("select b from BlockGroup b where status_seq = 11")
	Page<BlockGroup> alwaysBuyList(Pageable pageable);
	
	@Query("select b.price from BlockGroup b where status_seq = 11")
	List<Integer> alwaysBuyListGetPrice();
	
	
	@Query("select b from BlockGroup b where b.users.userId like %?1%")
	Page<BlockGroup> findByUserList(String userId,Pageable pageable);

//	@Query("select b from BlockGroup b where status_seq = 8 order by b.blockGroup_seq")
	@Query("select b from BlockGroup b order by b.blockGroup_seq")
	List<BlockGroup> listBlockGroupEndDate();
	
	@Query("select b.xLocation from Block b inner join b.blockGroup bg where b.block_seq = bg.minBlockSeq order by b.block_seq")
	List<Integer> listXLocationBlockJoinBlockGroupMinBlockSeq();
	
	@Query("select b.yLocation from Block b inner join b.blockGroup bg where b.block_seq = bg.minBlockSeq order by b.block_seq")
	List<Integer> listYLocationBlockJoinBlockGroupMinBlockSeq();
	
	@Query("select bg.width from Block b inner join b.blockGroup bg where b.block_seq = bg.minBlockSeq order by b.block_seq")
	List<Integer> listBlockGroupWidthJoinBlockMinBlockSeq();
	
	@Query("select bg.height from Block b inner join b.blockGroup bg where b.block_seq = bg.minBlockSeq order by b.block_seq")
	List<Integer> listBlockGroupHeightJoinBlockMinBlockSeq();
	
}
