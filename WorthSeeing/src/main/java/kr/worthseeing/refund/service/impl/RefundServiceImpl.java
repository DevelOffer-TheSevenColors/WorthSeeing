package kr.worthseeing.refund.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.refund.repository.RefundRepository;
import kr.worthseeing.refund.service.RefundService;
import kr.worthseeing.status.entity.Status;

@Service
public class RefundServiceImpl implements RefundService {

	@Autowired
	private RefundRepository refundRepo;

	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	@Autowired
	private BlockRepository blockRepo;

	@Override
	public void insertRefund(Refund refund, BlockGroup blockGroup, Block block) {
		
		List<Block> findBlock = (List<Block>) blockRepo.findAll();
		int findBlockGroup = blockGroup.getBlockGroup_seq();
		
		for(Block blockItem : findBlock) {
			if(blockItem.getBlockGroup().getBlockGroup_seq() == findBlockGroup) {
				Status status = new Status();
				status.setStatus_seq(12);
				blockItem.setStatus(status);
				BlockGroup blockGroup_ = new BlockGroup();
				blockGroup_.setBlockGroup_seq(blockItem.getBlock_seq());
				BlockGroupWaiting blockGroupWaiting = new BlockGroupWaiting();
				blockGroupWaiting.setBlockGroupWaiting_seq(1);
				blockItem.setBlockGroupWaiting(blockGroupWaiting);
				blockItem.setBlockGroup(blockGroup_);
				blockRepo.save(blockItem);
			}
		}
		refund.setBlockGroup_seq(findBlockGroup);
		refund.setRefundPrice(blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get().getPrice());
		refund.setRefundDate(new Date());
		refund.setBlockGroupWaiting_seq(1);
		refundRepo.save(refund);
		blockGroupRepo.deleteById(findBlockGroup);		
		
	}
}
