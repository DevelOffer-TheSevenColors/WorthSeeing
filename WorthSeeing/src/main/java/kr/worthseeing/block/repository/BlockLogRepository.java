package kr.worthseeing.block.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.BlockLog;

public interface BlockLogRepository  extends CrudRepository<BlockLog, Integer>{
 
	@Query("SELECT b FROM BlockLog b Where block_seq = ?1")
	List<BlockLog> findBlock(int keyword);
	
}
