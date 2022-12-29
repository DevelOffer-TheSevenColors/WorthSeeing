package kr.worthseeing.blockgroup.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.users.entity.Users;

public interface BlockGroupService {

	void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files, Users users);
 
	Map<Integer, List<BlockGroup>> listBlockGroup();
	
	Page<BlockGroup> listBlockGroupOrderByClickCnt(Pageable pageable);
	
	public BlockGroup findBlockGroup(BlockGroup blockGroup);
	
	Map<String, Object> listBoardGroupSeq();
	
	List<String> listcImg();
	 
	public Map<String, Object> topBlock();
	
	Map<String, List<Integer>> getBlockGroupDate();
	
	void updateBlockGroup(BlockGroup blockGroup, MultipartFile files, Users users);
	
	void updateBlockGroupWaiting(BlockGroupWaiting blockGroupWaiting, MultipartFile files, Users users);
	
	public void auctionStart();
}
