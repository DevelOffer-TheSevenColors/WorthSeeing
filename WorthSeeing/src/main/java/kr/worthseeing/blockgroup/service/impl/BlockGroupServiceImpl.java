package kr.worthseeing.blockgroup.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class BlockGroupServiceImpl implements BlockGroupService {

	@Autowired
	private BlockGroupRepository blockGroupRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Override
	public void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files) {
		BlockGroup blockGroup = new BlockGroup(blockGroupParam.getLinkUrl(), files.getOriginalFilename(),
				files.getOriginalFilename(), 500);
		Status status = new Status();
		status.setStatus_seq(2);
		blockGroup.setStatus(status);
		blockGroup.setUsers(usersRepo.findById("user1").get());

		try {
			saveFile(files);
		} catch (IOException e) {
			e.printStackTrace();
		}
		blockGroupRepo.save(blockGroup);

	}

	public void saveFile(MultipartFile files) throws IOException {

		String fileDir1 = "C:\\Users\\User\\git\\WorthSeeing\\WorthSeeing\\src\\main\\resources\\static\\img\\cimg/";
		String fileDir2 = "C:/uploadfiles/";

		if (files.isEmpty()) {
			System.out.println("Empty!!!!!!!!!!!!!!");
		}

		String fileName = files.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDate = format.format(new Date());

		String extension = fileName.substring(fileName.lastIndexOf("."));

		String fileSname = uuid + extension;
		String filePath = fileDir1 + nowDate + "/" + fileSname;

		File directory = new File(fileDir1 + nowDate);

		if (!directory.exists()) {
			try {
				directory.mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}

		System.out.println("file===>" + fileName + fileSname + filePath);

		files.transferTo(new File(filePath));

	}

	/*
	 * // 블록 그룹 status별 리스트 만들기
	 * 
	 * @Override public Map<String, List> listBlockGroup() { List<BlockGroup>
	 * status1blockGroupList = new ArrayList<BlockGroup>(); List<BlockGroup>
	 * status2blockGroupList = new ArrayList<BlockGroup>(); List<BlockGroup>
	 * status3blockGroupList = new ArrayList<BlockGroup>(); List<BlockGroup>
	 * status4blockGroupList = new ArrayList<BlockGroup>(); List<BlockGroup>
	 * status5blockGroupList = new ArrayList<BlockGroup>(); List<BlockGroup>
	 * status6blockGroupList = new ArrayList<BlockGroup>(); List<BlockGroup>
	 * status7blockGroupList = new ArrayList<BlockGroup>();
	 * 
	 * Map<String, List> resultMap = new HashMap<String, List>(); // List resultList
	 * = resultMap.get("status1");
	 * 
	 * List<BlockGroup> blockGroupList = (List<BlockGroup>)
	 * blockGroupRepo.findAll(); for(BlockGroup blockGroup : blockGroupList) {
	 * 
	 * if(blockGroup.getStatus().getStatus_seq() == 1) {
	 * status1blockGroupList.add(blockGroup); resultMap.put("status1",
	 * status1blockGroupList);
	 * 
	 * } else if(blockGroup.getStatus().getStatus_seq() == 2) {
	 * status2blockGroupList.add(blockGroup); resultMap.put("status2",
	 * status2blockGroupList);
	 * 
	 * } else if(blockGroup.getStatus().getStatus_seq() == 3) {
	 * status3blockGroupList.add(blockGroup); resultMap.put("status3",
	 * status3blockGroupList);
	 * 
	 * } else if(blockGroup.getStatus().getStatus_seq() == 4) {
	 * status4blockGroupList.add(blockGroup); resultMap.put("status4",
	 * status4blockGroupList);
	 * 
	 * } else if(blockGroup.getStatus().getStatus_seq() == 5) {
	 * status5blockGroupList.add(blockGroup); resultMap.put("status5",
	 * status5blockGroupList);
	 * 
	 * } else if(blockGroup.getStatus().getStatus_seq() == 6) {
	 * status6blockGroupList.add(blockGroup); resultMap.put("status6",
	 * status6blockGroupList);
	 * 
	 * } else if(blockGroup.getStatus().getStatus_seq() == 7) {
	 * status7blockGroupList.add(blockGroup); resultMap.put("status7",
	 * status7blockGroupList); } }
	 * 
	 * return resultMap;
	 * 
	 * }
	 */

	@Override
	public Map<Integer, List<BlockGroup>> listBlockGroup() {

		// resultMap : key의 타입 : String, value의 타입 : List<BlockGroup>
		Map<Integer, List<BlockGroup>> resultMap = new HashMap<>();

		List<BlockGroup> blockGroupList = (List<BlockGroup>) blockGroupRepo.findAll();

		for (BlockGroup blockGroup : blockGroupList) {
			int status = blockGroup.getStatus().getStatus_seq();

			if (!resultMap.containsKey(status)) { // 
				resultMap.put(status, new ArrayList<>());
			}

			resultMap.get(status).add(blockGroup);
		}

		return resultMap;
	}

}
