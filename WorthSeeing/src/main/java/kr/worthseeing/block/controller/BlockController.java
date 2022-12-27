package kr.worthseeing.block.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.message.dto.MessageDTO;

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
		
//		그룹핑 테스트
//		blockService.getBlockXY(1, 37);
		
		model.addAttribute("blockGroupSeqList", blockGroupService.listBoardGroupSeq().get("listBlockGroupSeq"));
		
		model.addAttribute("XLocationList", blockGroupService.listBoardGroupSeq().get("XLocationList"));
		model.addAttribute("YLocationList", blockGroupService.listBoardGroupSeq().get("YLocationList"));
		
		model.addAttribute("betweenDaysList", blockGroupService.getBlockGroupDate().get("betweenDaysList"));
		model.addAttribute("usingBlockGroupList", blockGroupService.getBlockGroupDate().get("usingBlockGroupList"));
		model.addAttribute("reservationBlockGroupSeqList", reservationService.listReservationBlockGroupSeq());

		List<String> urlList = new ArrayList<String>();
		urlList.add(0, null);

		for (String url : blockGroupService.listcImg()) {
				urlList.add(url);
		}

		List<String> urlTopList = new ArrayList<String>();
		urlTopList.add(0, null);

		List<String> blockGroupLink = new ArrayList<String>();

		if (!blockGroupService.topBlock(0).getContent().isEmpty()) {
			for (BlockGroup blockgroup : blockGroupService.topBlock(0).getContent()) {
				blockGroupLink.add(blockgroup.getLinkUrl());
				urlTopList.add(blockgroup.getCImg());
			}
		} else {
			for (int i = 0; i < 5; i++) {
				blockGroupLink.add("#");
				urlTopList.add("https://kwangan2-worthseeing-burket.s3.eu-west-2.amazonaws.com/defaultIMG.png");
			}
		}
		System.out.println(blockGroupLink);

		model.addAttribute("listcImg", urlList);
		model.addAttribute("listTopcImg", urlTopList);
		model.addAttribute("blockGroupLink", blockGroupLink);

		return "/main";
	}
	
	@GetMapping("/reservation/chooseBlockGroup")
	public String chooseBlockGroup() {
		return "/reservation/chooseBlockGroup";
	}
	
	@GetMapping("/block/grouping")
	public String blockGrouping(String firstNum, String lastNum, Model model) {
		System.out.println("firstNum---->" + firstNum);
		if(blockService.getBlockXY(Integer.parseInt(firstNum), Integer.parseInt(lastNum)) == null) {
			MessageDTO message = new MessageDTO("그룹핑 불가능합니다. (10개 초과 or 불가능한 블록 선택)", "/reservation/chooseBlockGroup", RequestMethod.GET, null);
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
