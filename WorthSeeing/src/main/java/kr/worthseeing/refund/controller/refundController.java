package kr.worthseeing.refund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.message.dto.MessageDTO;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.refund.service.RefundService;

@Controller
public class refundController {
	
	@Autowired
	private BlockGroupService blockGroupService;

	@Autowired
	private RefundService refundService;
	
	@GetMapping("/refund")
	public String refund(BlockGroup blockGroup, Model model) {
		BlockGroup myBlockGroup = blockGroupService.findBlockGroup(blockGroup);
		model.addAttribute("blockGroup", myBlockGroup);
		return "/refund";
	}
	
	@PostMapping("/refundProc")
	public String successRefund(Refund refund, BlockGroup blockGroup, Block block, Model model) {
		refundService.insertRefund(refund, blockGroup, block);
		MessageDTO message = new MessageDTO("환불이 완료되었습니다", "/mypageMain",
	            RequestMethod.GET, null);

		return showMessageAndRedirect(message, model);
		
	}

	private String showMessageAndRedirect(final MessageDTO params, Model model) {
	      model.addAttribute("params", params);
	      return "/common/messageRedirect";
	   }
}
