package kr.worthseeing.blockgroup.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.message.dto.MessageDTO;
import kr.worthseeing.mypage.service.MyPageService;
import kr.worthseeing.security.config.SecurityUser;

@Controller
public class BlockGroupController {

	@Autowired
	private BlockGroupService blockGroupService;

	@Autowired
	private MyPageService myPageService;

	// 경매 낙찰 후 미리 등록
	@GetMapping("/writeURLThumb")
	public String writeURLThumb(BlockGroupWaiting blockGroupWaiting, Model model) {
		BlockGroupWaiting BlockGroupWaiting = myPageService.findBlockGroupWaiting(blockGroupWaiting);
		model.addAttribute("blockGroupWaiting", BlockGroupWaiting);
		return "/writeURLThumb";
	} 

	// 이용중인 블록 수정
	@GetMapping("/updateURLThumb")
	public String updateURLThumb(BlockGroup blockGroup, Model model) {
		BlockGroup myBlockGroup = blockGroupService.findBlockGroup(blockGroup);
		model.addAttribute("blockGroup", myBlockGroup);

		return "/updateURLThumb";
	}

	// 이용 전 작성 처리
	@PostMapping("/writeURLThumb")
	public String writeURLThumbProc(Model model, BlockGroupWaiting blockGroupWaiting, MultipartFile files,
			@AuthenticationPrincipal SecurityUser principal,@PageableDefault Pageable pageable) {
		blockGroupService.updateBlockGroupWaiting(blockGroupWaiting, files, principal.getUsers());
		Page<BlockGroup> blockGroupUserId = myPageService.getBlockGroupUserId(principal.getUsers().getUserId(),pageable);
		model.addAttribute("users", myPageService.getUsers(principal.getUsers()));
		model.addAttribute("BlockGroupUserId", blockGroupUserId);

		MessageDTO message = new MessageDTO("등록되었습니다.", "/mypageMain", RequestMethod.GET, null);

		return showMessageAndRedirect(message, model);
	}

	// 수정 처리
	@PostMapping("/updateURLThumb")
	public String updateURLThumbProc(Model model, BlockGroup blockGroup, MultipartFile files,
			@AuthenticationPrincipal SecurityUser principal,@PageableDefault Pageable pageable) {
		blockGroupService.updateBlockGroup(blockGroup, files, principal.getUsers());
		Page<BlockGroup> blockGroupUserId = myPageService.getBlockGroupUserId(principal.getUsers().getUserId(),pageable);
		model.addAttribute("users", myPageService.getUsers(principal.getUsers()));
		model.addAttribute("BlockGroupUserId", blockGroupUserId);

		MessageDTO message = new MessageDTO("수정되었습니다.", "/mypageMain", RequestMethod.GET, null);

		return showMessageAndRedirect(message, model);
	}

	private String showMessageAndRedirect(final MessageDTO params, Model model) {
		model.addAttribute("params", params);
		return "/common/messageRedirect";
	}

	// blockGroup 클릭수 많은 순으로 정렬한 리스트
	@RequestMapping("/list/blockGroupRankList")
	public String listBlockGroupRank(Model model, @PageableDefault Pageable pageable) {
		Page<BlockGroup> blockGroupList = blockGroupService.listBlockGroupOrderByClickCnt(pageable);
		List<String> urlGroupRankList = new ArrayList<String>();
		urlGroupRankList.add(0, null);

		for (BlockGroup blockgroup : blockGroupList) {
			urlGroupRankList.add(blockgroup.getCImg());
		}
		model.addAttribute("urlGroupRankList", urlGroupRankList);
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
