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

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.status.entity.Status;
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
	public void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files) {
		String imagePath = amazonS3Client.getUrl(S3Bucket, files.getOriginalFilename()).toString(); // 접근가능한 URL 가져오기

		BlockGroup blockGroup = new BlockGroup(blockGroupParam.getLinkUrl(), files.getOriginalFilename(),
				imagePath, 500);

		Status status = new Status();
		status.setStatus_seq(7);
		
		blockGroup.setStatus(status);
		blockGroup.setUsers(usersRepo.findById("user1").get());

		try {
			saveFile(files);
		} catch (IOException e) {
			e.printStackTrace();
		}
		blockGroupRepo.save(blockGroup);

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
		amazonS3Client.putObject(
				new PutObjectRequest(S3Bucket, fileName, files.getInputStream(), objectMetaData)
				.withCannedAcl(CannedAccessControlList.PublicRead)
				);
				
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