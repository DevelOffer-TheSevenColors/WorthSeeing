package kr.worthseeing.block.service;

import java.util.List;

import kr.worthseeing.block.entity.Block;

public interface BlockService {
	
	Block getBlock(Block block);
	
	List<Block> listBlock();

}
