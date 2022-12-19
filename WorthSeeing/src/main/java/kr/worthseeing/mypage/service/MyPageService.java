package kr.worthseeing.mypage.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.security.config.SecurityUser;

public interface MyPageService {

	void getMyPage();
	
	void getClick(BlockGroup blockGroup,@AuthenticationPrincipal SecurityUser principal);
	
	
}
