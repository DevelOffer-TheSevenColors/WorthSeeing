package kr.worthseeing.blockgroup.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;

public interface BlockGroupService {

	void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files);
 
	Map<Integer, List<BlockGroup>> listBlockGroup();
	
	Page<BlockGroup> listBlockGroupOrderByClickCnt(Pageable pageable);
	
	List<BlockGroup> getListBlockGroup();
	
}
