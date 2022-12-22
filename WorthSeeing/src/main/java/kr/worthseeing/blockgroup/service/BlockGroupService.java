package kr.worthseeing.blockgroup.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.users.entity.Users;

public interface BlockGroupService {

	void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files, Users users);
 
	Map<Integer, List<BlockGroup>> listBlockGroup();
	
	Page<BlockGroup> listBlockGroupOrderByClickCnt(Pageable pageable);
	
	public BlockGroup findBlockGroup(BlockGroup blockGroup);
	
	List<Integer> listBoardGroupSeq();
	
	List<String> listcImg();
	
	public Page<BlockGroup> topBlock(int page);
	
	void updateBlockGroup(BlockGroup blockGroup, MultipartFile files);
}
