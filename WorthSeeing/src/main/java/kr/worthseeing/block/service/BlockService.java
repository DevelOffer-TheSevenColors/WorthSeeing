package kr.worthseeing.block.service;

import java.util.List;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;

public interface BlockService {
	
	Block getBlock(Block block);
	
	List<Block> listBlock();

	public  List<Block> findAuctionBlock(BlockGroup blockGroup);
	
	List<Integer> getBlockXY(int firstNum, int lastNum);
	
}
