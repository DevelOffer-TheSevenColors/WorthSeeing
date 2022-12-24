package kr.worthseeing.blockgroup.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.mypage.service.MyPageService;
import kr.worthseeing.security.config.SecurityUser;

@Controller
public class BlockGroupController {

	@Autowired
	private BlockGroupService blockGroupService;
	
	@Autowired
	private MyPageService myPageService;
	
	@GetMapping("/writeURLThumb")
	public String writeURLThumb(BlockGroup blockGroup, Model model) {
		BlockGroup myBlockGroup = blockGroupService.findBlockGroup(blockGroup);
		model.addAttribute("blockGroup", myBlockGroup);
		System.out.println("Controller blockGroup-->" + myBlockGroup);
		
		return "/writeURLThumb";
	}
	
//	@PostMapping("/writeURLThumb")
	public String writeURLThumbProc(BlockGroup blockGroup, MultipartFile files, @AuthenticationPrincipal SecurityUser principal) {
		blockGroupService.insertBlockGroup(blockGroup, files, principal.getUsers());
		
		return "redirect:/main";
	}
	
	@PostMapping("/updateURLThumb")
	public String updateURLThumbProc(Model model, BlockGroup blockGroup, MultipartFile files, @AuthenticationPrincipal SecurityUser principal) {
		blockGroupService.updateBlockGroup(blockGroup, files, principal.getUsers());
		List<BlockGroup> blockGroupUserId = myPageService.getBlockGroupUserId(principal.getUsers().getUserId());
		model.addAttribute("users", myPageService.getUsers(principal.getUsers()));
		model.addAttribute("BlockGroupUserId", blockGroupUserId);
		return "redirect:/mypageMain";
	}
	
	
	/*
	@GetMapping("/main")
	public String listBlockGroup(Model model) {
		List<BlockGroup> blockGroupList = blockGroupService.listBlockGroup();
		model.addAttribute("blockGroupList", blockGroupList);
		return "redirect:/";
	}
	*/
	
	// blockGroup 클릭수 많은 순으로 정렬한 리스트
	@RequestMapping("/list/blockGroupRankList")
	public String listBlockGroupRank(Model model, @PageableDefault Pageable pageable) {
		Page<BlockGroup> blockGroupList = blockGroupService.listBlockGroupOrderByClickCnt(pageable);
		List<String> urlGroupRankList = new ArrayList<String>();
		urlGroupRankList.add(0, null);
		
		for(BlockGroup blockgroup : blockGroupList) {
			try {
				String encodeResult = URLEncoder.encode(blockgroup.getCImg(), "utf-8");
				urlGroupRankList.add(blockgroup.getCImg());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		model.addAttribute("urlGroupRankList",urlGroupRankList);
		model.addAttribute("blockGroupList", blockGroupList);
		return "/list/blockGroupRankList";
	}
	
	@GetMapping("/test")
	public String listBlockGroup(Model model) {
		Map<Integer, List<BlockGroup>> blockGroupMap = blockGroupService.listBlockGroup();
		System.out.println("map===>" + blockGroupMap.get(2).get(0).getBlockGroup_seq());
		model.addAttribute("blockGroupMap", blockGroupMap); // html ${blockGroupMap.get(2).get(0).getBlockGroup_seq()}
		return "redirect:/";
	}
	
	@GetMapping("/buyBlock")
	public String buyBlock() {
		return "/buyBlock";
	}
	
}
