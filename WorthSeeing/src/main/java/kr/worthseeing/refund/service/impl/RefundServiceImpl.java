package kr.worthseeing.refund.service.impl;

import java.util.Date;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.refund.repository.RefundRepository;
import kr.worthseeing.refund.service.RefundService;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

@Service
public class RefundServiceImpl implements RefundService{

	@Autowired
	private RefundRepository refundRepo;

	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	@Override
	public void insertRefund(Refund refund, BlockGroup blockGroup) {
		refundRepo.findById(refund.getRefund_seq());
		refund.setBlockGroup(blockGroup);
		refundRepo.save(refund);
		
		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
		findBlockGroup.setCImg("https://kwangan2-worthseeing-burket.s3.eu-west-2.amazonaws.com/buypagetest.jpg");
		findBlockGroup.toString();
		findBlockGroup.setLinkUrl("/buyBlock");
		findBlockGroup.setSImg("C:/Users/User/git/WorthSeeing/WorthSeeing/src/main/resources/static/img/buypageimg.jpg");
		findBlockGroup.setStartDate(null);
		
		Status status = new Status();
			status.setStatus_seq(2);
		findBlockGroup.setStatus(status);
		
		Users users = new Users();
			users.setUserId("user1");
		findBlockGroup.setUsers(users);
		
		blockGroupRepo.save(findBlockGroup);
	}
}
