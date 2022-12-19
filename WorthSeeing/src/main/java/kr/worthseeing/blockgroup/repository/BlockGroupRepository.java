package kr.worthseeing.blockgroup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockgroup.entity.BlockGroup;

public interface BlockGroupRepository extends CrudRepository<BlockGroup, Integer>  {
	
	@Query("select b from BlockGroup b")
	Page<BlockGroup> listBoard(Pageable pageable);
//	,
//	QuerydslPredicateExecutor<BlockGroup>
//	List<BlockGroup> getlistBlockGroup(BlockGroup blockGroup);
	
}
