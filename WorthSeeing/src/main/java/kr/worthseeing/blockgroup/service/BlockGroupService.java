package kr.worthseeing.blockgroup.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;

public interface BlockGroupService {

	void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files);
 
//	Map<String, List> listBlockGroup();
	
	Map<Integer, List<BlockGroup>> listBlockGroup();
}
