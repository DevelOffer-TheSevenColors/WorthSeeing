package kr.worthseeing.refund.service;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.refund.entity.Refund;

public interface RefundService {

	public void insertRefund(Refund refund, BlockGroup blockGroup, Block block);
}
