package kr.worthseeing.blockgroup.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.reservation.entity.Reservation;

public interface BlockGroupRepository extends CrudRepository<BlockGroup, Integer>,
	QuerydslPredicateExecutor<BlockGroup>  {
	
	@Query("select b from BlockGroup b")
	Page<BlockGroup> listBoard(Pageable pageable);
	
	@Query("select b from BlockGroup b where b.users.userId like %?1%")
	List<BlockGroup> findByUserId(String userId);
	
	@Query("select max(b.blockGroup_seq) from BlockGroup b ")
	int getMaxBlockGroupSeq();
	
	@Query("select b.blockGroup_seq from BlockGroup b order by b.blockGroup_seq")
	List<Integer> listBlockGroupSeq();
	
	@Query("select b.cImg from BlockGroup b order by b.blockGroup_seq")
	List<String> listcImg();
	
	@Query("select b from BlockGroup b order by b.blockGroup_seq")
	List<BlockGroup> orderByBlockGroupSeq();
	
	@Query("select b from BlockGroup b where status_seq = ?1")
	Page<BlockGroup> alwaysBuyList(Pageable pageable, int keywoard);
}
