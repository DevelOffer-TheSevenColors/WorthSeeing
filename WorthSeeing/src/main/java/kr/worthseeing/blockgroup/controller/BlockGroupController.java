package kr.worthseeing.blockgroup.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;

@Controller
public class BlockGroupController {

	@Autowired
	private BlockGroupService blockGroupService;
	
	@GetMapping("/writeURLThumb")
	public String writeURLThumb() {
		
		return "/writeURLThumb";
	}
	
	@PostMapping("/writeURLThumb")
	public String writeURLThumbProc(BlockGroup blockGroup, MultipartFile files) {
		blockGroupService.insertBlockGroup(blockGroup, files);
		
		return "redirect:/main";
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
		System.out.println("blockGrou==>" + blockGroupList.getContent().toString());
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
	
}
