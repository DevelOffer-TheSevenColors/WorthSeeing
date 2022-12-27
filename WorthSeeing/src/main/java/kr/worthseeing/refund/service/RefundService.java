package kr.worthseeing.refund.service;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.refund.entity.Refund;

public interface RefundService {

	public void insertRefund(Refund refund, BlockGroupWaiting blockGroupWatiting);
}
