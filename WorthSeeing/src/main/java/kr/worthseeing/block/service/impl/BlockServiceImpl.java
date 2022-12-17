package kr.worthseeing.block.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.block.service.BlockService;

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
	
}
