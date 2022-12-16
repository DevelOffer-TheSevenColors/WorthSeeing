package kr.worthseeing.blockgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;

@Controller
public class BlockGroupController {

	@Autowired
	private BlockGroupService blockGroupService;
	
	@GetMapping("/writeURLThumb")
	public String writeURLThumb() {
		
		return "writeURLThumb";
	}
	
	@PostMapping("/writeURLThumb")
	public String writeURLThumbProc(BlockGroup blockGroup, MultipartFile files) {
		System.out.println("blockGroup ===> " + blockGroup);
		blockGroupService.insertBlockGroup(blockGroup, files);
		
		return "redirect:/";
	}
	
}
