package kr.worthseeing.block.service;

import java.util.List;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;

public interface BlockService {
	
	Block getBlock(Block block);
	
	List<Block> listBlock();

	public  List<Block> findAuctionBlock(BlockGroupWaiting blockGroupWaiting);
	
	List<Integer> getBlockXY(int firstNum, int lastNum);
	
	List<Integer> availableGroupingblock();
	
}
