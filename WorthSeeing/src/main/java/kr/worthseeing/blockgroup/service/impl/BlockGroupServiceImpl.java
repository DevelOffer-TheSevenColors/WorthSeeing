package kr.worthseeing.blockgroup.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

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
	private UsersRepository usersRepo;

	private String S3Bucket = "kwangan2-worthseeing-burket"; // Bucket 이름

	@Override
	public void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files, Users users) {
		String imagePath = amazonS3Client.getUrl(S3Bucket, files.getOriginalFilename()).toString(); // 접근가능한 URL 가져오기

		BlockGroup blockGroup = new BlockGroup(blockGroupRepo.getMaxBlockGroupSeq() + 1, blockGroupParam.getLinkUrl(),
				files.getOriginalFilename(), imagePath, 500);

		Status status = new Status();
		status.setStatus_seq(3);

		blockGroup.setStatus(status);
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
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "clickCnt");

		return blockGroupRepo.listBoard(pageable);
	}

	@Override
	public Page<BlockGroup> topBlock(int page) {
		Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "clickCnt");
		return blockGroupRepo.listBoard(pageable);
	}

	@Override
	public List getBlockGroupDate() {

		List<BlockGroup> listBlockGroup = (List<BlockGroup>) blockGroupRepo.findAll();

		List<String> endDateList = new ArrayList<String>();

		for (BlockGroup blockGroup : listBlockGroup) {

			Date testDate = blockGroup.getEndDate();
			
//			LocalDate now = LocalDate.now(); // 현재시간

//			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format 되게 구현

			LocalDate date = LocalDate.parse(String.valueOf(testDate)); // 현재 시간 스
//
//			LocalDate startDate = LocalDate.now(); // 지금 시간
//			LocalDate endDate = date.withDayOfMonth(date.lengthOfMonth()); // 현재 월의 일 수 31일까지 찍힘
//
//			LocalDateTime calcStartDate = startDate.atStartOfDay(); // 현재 시간 계산 할 수있도록 변환 - 시작
//			LocalDateTime calcEndDate = endDate.atStartOfDay(); // 현재 월일수를 계산 할 수있도록 변환 - 끝
//
//			int betweenDays = (int) Duration.between(calcStartDate, calcEndDate).toDays(); // 시작과 끝을 빼서 계산한 값
//
//			System.out.println("betweenDays : " + betweenDays);
//			
//			
//			endDate.getDayOfMonth();// 이번달 마지막날을 int로 가져오는것

//			BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
//
//			Date endDate = blockGroup.getEndDate();
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//			System.out.println("date--->" + simpleDateFormat.format(endDate));

//			String endDateSubString = endDate.toString();
//			System.out.println("endDateList Impl----->" + endDateSubString.substring(0, 10));
//			System.out.println("endDateList Impl----->" + endDate);
//			endDateList.add(simpleDateFormat.format(endDate));
//			endDateList.add(simpleDateFormat.format(endDate));

		}

		return endDateList;
	}

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

	@Override
	public List<Integer> listBoardGroupSeq() {

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