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
				System.out.println("111");
				BlockGroup blockGroup_ = new BlockGroup();
				blockGroup_.setBlockGroup_seq(1);
				BlockGroupWaiting blockGroupWaiting = new BlockGroupWaiting();
				blockGroupWaiting.setBlockGroupWaiting_seq(1);
				blockItem.setBlockGroupWaiting(blockGroupWaiting);
				blockItem.setBlockGroup(blockGroup_);
				blockRepo.save(blockItem);
				System.out.println("222");
				refund.setBlockGroup_seq(findBlockGroup);
				refund.setRefundPrice(blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get().getPrice());
				refund.setRefundDate(new Date());
				refund.setBlockGroupWaiting_seq(1);
				refundRepo.save(refund);
				System.out.println("333");
				blockGroupRepo.deleteById(findBlockGroup);				
			}
		}
		
	}
}
