package kr.worthseeing.block.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.main.reservation.service.ReservationService;

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
		
		blockService.getBlockXY(1, 37);
		
		model.addAttribute("blockGroupSeqList", blockGroupService.listBoardGroupSeq());
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
	
}
