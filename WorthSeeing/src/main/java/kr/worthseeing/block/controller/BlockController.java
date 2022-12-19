package kr.worthseeing.block.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		Map<Integer, List<BlockGroup>> blockGroupMap = blockGroupService.listBlockGroup();
		model.addAttribute("blockGroupMap", blockGroupMap);
		return "/main";
	}
	
}
