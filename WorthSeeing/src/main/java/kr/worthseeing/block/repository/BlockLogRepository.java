package kr.worthseeing.block.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.entity.BlockLog;

public interface BlockLogRepository  extends CrudRepository<BlockLog, Integer>{
 
}
