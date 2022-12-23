package kr.worthseeing.refund.service;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.status.entity.Status;

public interface RefundService {

	void insertRefund(Refund refund, BlockGroup blockGroup);
}
