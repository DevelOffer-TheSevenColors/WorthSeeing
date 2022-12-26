package kr.worthseeing.block.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import groovyjarjarantlr4.v4.parse.ANTLRParser.block_return;
import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.security.config.SecurityUser;

@Controller
public class BlockController {

	@Autowired
	private BlockGroupService blockGroupService;

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/main")
	public String mainPage(Model model) {

//		Map<Integer, List<BlockGroup>> blockGroupMap = blockGroupService.listBlockGroup();
//		model.addAttribute("blockGroupMap", blockGroupMap);

		model.addAttribute("blockGroupSeqList", blockGroupService.listBoardGroupSeq());
		model.addAttribute("betweenDaysList", blockGroupService.getBlockGroupDate().get("betweenDaysList"));
		model.addAttribute("usingBlockGroupList", blockGroupService.getBlockGroupDate().get("usingBlockGroupList"));
		model.addAttribute("reservationBlockGroupSeqList", reservationService.listReservationBlockGroupSeq());

//		JSONObject jsonObject = new JSONObject(blockGroupMap);
//		System.out.println("jsonObject--->" + jsonObject.get(3).toString());

//		List<BlockGroup> blockGroupList = blockGroupMap.get(3);

		List<String> urlList = new ArrayList<String>();
		urlList.add(0, null);

		for (String url : blockGroupService.listcImg()) {
			try {
				String encodeResult = URLEncoder.encode(url, "utf-8");
				urlList.add(url);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

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

//		blockGroupList.get(0).getLinkUrl();
		model.addAttribute("listcImg", urlList);
		model.addAttribute("listTopcImg", urlTopList);
		model.addAttribute("blockGroupLink", blockGroupLink);

//		model.addAttribute("blockGroupMap", jsonObject.get(3)); // html ${blockGroupMap.get(2).get(0).getBlockGroup_seq()}

		return "/main";
	}

}
