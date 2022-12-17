package kr.worthseeing.block.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;

@Controller
public class BlockController {
	
	@Autowired
	private BlockGroupService blockGroupService;
	
	@GetMapping("/main")
	public String mainPage(Model model) {
		Map<Integer, List<BlockGroup>> blockGroupMap = blockGroupService.listBlockGroup();
		model.addAttribute("blockGroupMap", blockGroupMap);
		return "/main";
	}
	
}
