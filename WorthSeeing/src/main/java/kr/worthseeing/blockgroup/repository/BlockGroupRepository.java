package kr.worthseeing.blockgroup.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;

public interface BlockGroupRepository  extends CrudRepository<BlockGroup, Integer>{
 
}
