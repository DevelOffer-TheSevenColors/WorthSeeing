package kr.worthseeing.blockgroup.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.entity.BlockGroupLog;

public interface BlockGroupLogRepository  extends CrudRepository<BlockGroupLog, Integer>{
 
}
