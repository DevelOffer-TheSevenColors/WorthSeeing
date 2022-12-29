package kr.worthseeing.block.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.message.dto.MessageDTO;
import kr.worthseeing.security.config.SecurityUser;

@Controller
public class BlockController {

	@Autowired
	private BlockGroupService blockGroupService;

	@Autowired
	private BlockService blockService;

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/main")
	public String mainPage(Model model) {
		
		Map<String, Object> maplistBoardGroupSeq = blockGroupService.listBoardGroupSeq();
		Map<String, List<Integer>> mapgetBlockGroupDate = blockGroupService.getBlockGroupDate();
		Map<String, Object> mapgetTopBlock = blockGroupService.topBlock();
		
		model.addAttribute("blockGroupSeqList", maplistBoardGroupSeq.get("listBlockGroupSeq"));

		model.addAttribute("listXLocation", maplistBoardGroupSeq.get("listXLocation"));
		model.addAttribute("listYLocation", maplistBoardGroupSeq.get("listYLocation"));

		model.addAttribute("listWidth", maplistBoardGroupSeq.get("listWidth"));
		model.addAttribute("listHeight", maplistBoardGroupSeq.get("listHeight"));
		
		model.addAttribute("listcImg", blockGroupService.listcImg());

		model.addAttribute("betweenDaysList", mapgetBlockGroupDate.get("betweenDaysList"));
		model.addAttribute("usingBlockGroupList", mapgetBlockGroupDate.get("usingBlockGroupList"));
		model.addAttribute("reservationBlockGroupSeqList", reservationService.listReservationBlockGroupSeq());

		model.addAttribute("listTopcImg", mapgetTopBlock.get("imgTopList"));
		model.addAttribute("blockGroupLink", mapgetTopBlock.get("blockGroupLink"));
		
		return "/main";
		
	}

	@GetMapping("/reservation/chooseBlockGroup")
	public String chooseBlockGroup(Model model) {
		model.addAttribute("availableGroupingblock", blockService.availableGroupingblock());
		return "/reservation/chooseBlockGroup";
	}

	@GetMapping("/block/grouping")
	public String blockGrouping(String firstNum, String lastNum, Model model, @AuthenticationPrincipal SecurityUser principal) {
		System.out.println("firstNum--->" + firstNum);
		
		if (blockService.getBlockXY(Integer.parseInt(firstNum), Integer.parseInt(lastNum), principal.getUsers().getUserId()) == null) {
			MessageDTO message = new MessageDTO("예약 불가능합니다. (10개 초과 or 불가능한 블록 선택)", "/reservation/chooseBlockGroup",
					RequestMethod.GET, null);
			return showMessageAndRedirect(message, model);
		} else {
			MessageDTO message = new MessageDTO("예약되었습니다.", "/reservation/reservationList", RequestMethod.GET, null);
			return showMessageAndRedirect(message, model);
		}
	}

	private String showMessageAndRedirect(final MessageDTO params, Model model) {
		model.addAttribute("params", params);
		return "/common/messageRedirect";
	}

}
