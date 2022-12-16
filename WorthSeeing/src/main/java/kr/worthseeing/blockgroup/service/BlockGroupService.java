package kr.worthseeing.blockgroup.service;

import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;

public interface BlockGroupService {

	void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files);

}
