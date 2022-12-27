package kr.worthseeing.blockgroup.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class BlockGroupServiceImpl implements BlockGroupService {

	@Autowired
	private AmazonS3Client amazonS3Client;

	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	@Autowired
	private BlockRepository blockRepo;

	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;
	
	@Autowired
	private UsersRepository usersRepo;

	private String S3Bucket = "kwangan2-worthseeing-burket"; // Bucket 이름

	@Override 
	public void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files, Users users) {
		String imagePath = amazonS3Client.getUrl(S3Bucket, files.getOriginalFilename()).toString(); // 접근가능한 URL 가져오기

		BlockGroup blockGroup = new BlockGroup(blockGroupRepo.getMaxBlockGroupSeq() + 1, blockGroupParam.getLinkUrl(),
				files.getOriginalFilename(), imagePath, 500);

		Status status = new Status();
		status.setStatus_seq(3);

//		blockGroup.setStatus(status);
		blockGroup.setUsers(usersRepo.findById(users.getUserId()).get());

		try {
			saveFile(files);
		} catch (IOException e) {
			e.printStackTrace();
		}
		blockGroupRepo.save(blockGroup);

	}

	@Override
	public void updateBlockGroup(BlockGroup blockGroup, MultipartFile files, Users users) {
		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();

		if (!files.isEmpty()) {
			String imagePath = amazonS3Client.getUrl(S3Bucket, files.getOriginalFilename()).toString();
			findBlockGroup.setCImg(imagePath);
			findBlockGroup.setSImg(files.getOriginalFilename());
			try {
				saveFile(files);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		findBlockGroup.setLinkUrl(blockGroup.getLinkUrl());

		blockGroupRepo.save(findBlockGroup);
	}
	
	@Override
	public void updateBlockGroupWaiting(BlockGroupWaiting blockGroupWaiting, MultipartFile files, Users users) {
		BlockGroupWaiting findBlockGroupWaiting = blockGroupWaitingRepo.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();
		
		if (!files.isEmpty()) {
			String imagePath = amazonS3Client.getUrl(S3Bucket, files.getOriginalFilename()).toString();
			findBlockGroupWaiting.setCImg(imagePath);
//			findBlockGroupWaiting.setSImg(files.getOriginalFilename());
			try {
				saveFile(files);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		findBlockGroupWaiting.setLinkUrl(blockGroupWaiting.getLinkUrl());
		
		blockGroupWaitingRepo.save(findBlockGroupWaiting);
	}

	private void saveFile(MultipartFile files) throws IOException {

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
		long size = files.getSize(); // 파일 크기

		ObjectMetadata objectMetaData = new ObjectMetadata();
		objectMetaData.setContentType(files.getContentType());
		objectMetaData.setContentLength(size);

		// S3에 업로드
		amazonS3Client.putObject(new PutObjectRequest(S3Bucket, fileName, files.getInputStream(), objectMetaData)
				.withCannedAcl(CannedAccessControlList.PublicRead));

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

	// 인기 블록 리스트
	@Override
	public Page<BlockGroup> listBlockGroupOrderByClickCnt(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 9, Sort.Direction.DESC, "clickCnt");

		return blockGroupRepo.listBlockGroup(pageable);
	}

	@Override
	public Page<BlockGroup> topBlock(int page) {
		Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "clickCnt");
		return blockGroupRepo.listBlockGroup(pageable);
	}

	@Override
	public Map<String, List<Integer>> getBlockGroupDate() {
		
		if(blockGroupRepo.listBlockGroupEndDate()==null) {
			return null;
		}
		
		List<BlockGroup> listBlockGroupEndDate = blockGroupRepo.listBlockGroupEndDate();

		List<Integer> betweenDaysList = new ArrayList<Integer>();
		List<Integer> usingBlockGroupList = new ArrayList<Integer>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		for (BlockGroup blockGroupItem : listBlockGroupEndDate) {
			String endDateItem = blockGroupItem.getEndDate();
			
			if (endDateItem == null){
				betweenDaysList.add((int) Duration
						.between(LocalDate.now().atStartOfDay(), LocalDate.now().atStartOfDay())
						.toDays());
			} else {
				betweenDaysList.add((int) Duration
						.between(LocalDate.now().atStartOfDay(), LocalDate.parse(endDateItem, formatter).atStartOfDay())
						.toDays());
				usingBlockGroupList.add(blockGroupItem.getBlockGroup_seq());
			}
			
		}

		Map<String, List<Integer>> resultMap = new HashMap<String, List<Integer>>();
		resultMap.put("betweenDaysList", betweenDaysList);
		resultMap.put("usingBlockGroupList", usingBlockGroupList);
		
		return resultMap;
	}

	@Override
	public Map<Integer, List<BlockGroup>> listBlockGroup() {

		// resultMap : key의 타입 : String, value의 타입 : List<BlockGroup>
		Map<Integer, List<BlockGroup>> resultMap = new HashMap<>();

		List<BlockGroup> blockGroupList = (List<BlockGroup>) blockGroupRepo.findAll();

		for (BlockGroup blockGroup : blockGroupList) {
//			int status = blockGroup.getStatus().getStatus_seq();
//
//			if (!resultMap.containsKey(status)) { //
//				resultMap.put(status, new ArrayList<>());
//			}
//
//			resultMap.get(status).add(blockGroup);
		}

		return resultMap;
	}

	// 메인화면 기존 이미지 표시
	@Override
	public List<Integer> listBoardGroupSeq() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Integer> listBlockGroupSeq = blockGroupRepo.listBlockGroupSeq();
		
		List<Integer> listXLocation = new ArrayList<Integer>();
		List<Integer> listYLocation = new ArrayList<Integer>();
		
		for(int blockGroupSeq : listBlockGroupSeq) {
			
			Block findBlock = blockRepo.findBlockGroupSeqFromBlock(blockGroupSeq);
			listXLocation.add(findBlock.getXLocation());
			listYLocation.add(findBlock.getYLocation());
			
		}
//				 blockGroupRepo.listBlockGroupSeq()
		return blockGroupRepo.listBlockGroupSeq();
	}

	@Override
	public List<String> listcImg() {
		return blockGroupRepo.listcImg();
	}

	@Override
	public BlockGroup findBlockGroup(BlockGroup blockGroup) {
		return blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
	}

}