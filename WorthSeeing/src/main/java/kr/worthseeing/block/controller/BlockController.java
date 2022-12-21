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

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.users.dto.SessionUserDTO;

@Controller
public class BlockController {

	@Autowired
	private BlockGroupService blockGroupService;

	@GetMapping("/main")
	public String mainPage(Model model, @AuthenticationPrincipal SecurityUser principal) {

		SessionUserDTO sessionUserDTO = new SessionUserDTO();
		sessionUserDTO.setUsers(principal.getUsers());
		System.out.println("sessionUserDTO========>" + sessionUserDTO.toString());

//		Map<Integer, List<BlockGroup>> blockGroupMap = blockGroupService.listBlockGroup();
//		model.addAttribute("blockGroupMap", blockGroupMap);
		
		List<Integer> blockGroupSeqList = blockGroupService.listBoardGroupSeq();
		model.addAttribute("blockGroupSeqList", blockGroupSeqList);
		
//		JSONObject jsonObject = new JSONObject(blockGroupMap);
//		System.out.println("jsonObject--->" + jsonObject.get(3).toString());
		
//		List<BlockGroup> blockGroupList = blockGroupMap.get(3);
		
		List<String> urlList = new ArrayList<String>();
		urlList.add(0, null);
		
		for(String url : blockGroupService.listcImg()) {
			try {
				String encodeResult = URLEncoder.encode(url, "utf-8");
				urlList.add(url);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		
		
		List<String> urlTopList = new ArrayList<String>();
		urlTopList.add(0, null);
		
		for(BlockGroup blockgroup : blockGroupService.topBlock(0).getContent()) {
			try {
				String encodeResult = URLEncoder.encode(blockgroup.getCImg(), "utf-8");
				urlTopList.add(blockgroup.getCImg());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		
//		blockGroupList.get(0).getLinkUrl();
		model.addAttribute("listcImg", urlList);
		model.addAttribute("listTopcImg", urlTopList);
		
//		model.addAttribute("blockGroupMap", jsonObject.get(3)); // html ${blockGroupMap.get(2).get(0).getBlockGroup_seq()}

		return "/main";
	}

}
