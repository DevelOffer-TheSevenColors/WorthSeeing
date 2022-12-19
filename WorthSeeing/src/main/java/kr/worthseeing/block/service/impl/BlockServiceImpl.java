package kr.worthseeing.block.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.blockgroup.entity.BlockGroup;

@Service
public class BlockServiceImpl implements BlockService{

	@Autowired
	private BlockRepository blockRepo;
	
	@Override
	public Block getBlock(Block block) {
		return blockRepo.findById(block.getBlock_seq()).get();
	}
	
	@Override
	public List<Block> listBlock() {
		return (List<Block>) blockRepo.findAll();
	}
	
	@Override
	public  List<Block> findAuctionBlock(BlockGroup blockGroup){
		System.out.println("aaaaa");
		
		System.out.println("bbbb"+blockRepo.findAuctionBlock(String.valueOf(blockGroup.getBlockGroup_seq())));
		
		System.out.println("cccccc");
		return blockRepo.findAuctionBlock(String.valueOf(blockGroup.getBlockGroup_seq()));
	}
	
	
	
	
}
