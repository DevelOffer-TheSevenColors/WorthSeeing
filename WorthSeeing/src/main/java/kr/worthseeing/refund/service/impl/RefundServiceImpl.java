package kr.worthseeing.refund.service.impl;

import java.util.Date;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.refund.repository.RefundRepository;
import kr.worthseeing.refund.service.RefundService;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

@Service
public class RefundServiceImpl implements RefundService {

	@Autowired
	private RefundRepository refundRepo;

	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;

	@Override
	public void insertRefund(Refund refund, BlockGroupWaiting blockGroupWatiting) {
		refundRepo.findById(refund.getRefund_seq());
		refund.setBlockGroupWaiting(blockGroupWatiting);
		refundRepo.save(refund);

		BlockGroupWaiting findBlockGroupWaiting = blockGroupWaitingRepo
				.findById(blockGroupWatiting.getBlockGroupWaiting_seq()).get();
		findBlockGroupWaiting.setCImg("https://kwangan2-worthseeing-burket.s3.eu-west-2.amazonaws.com/buypagetest.jpg");
		findBlockGroupWaiting.setEndDate(null);
		findBlockGroupWaiting.setLinkUrl("/buyBlock");
		findBlockGroupWaiting
				.setSImg("C:/Users/User/git/WorthSeeing/WorthSeeing/src/main/resources/static/img/buypageimg.jpg");
		findBlockGroupWaiting.setStartDate(null);

		Status status = new Status();
		status.setStatus_seq(2);
		findBlockGroupWaiting.setStatus(status);

		Users users = new Users();
		users.setUserId("user1");
		findBlockGroupWaiting.setUsers(users);

		blockGroupWaitingRepo.save(findBlockGroupWaiting);
	}
}
