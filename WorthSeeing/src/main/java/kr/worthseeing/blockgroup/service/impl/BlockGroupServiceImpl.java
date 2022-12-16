package kr.worthseeing.blockgroup.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class BlockGroupServiceImpl implements BlockGroupService{

	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	@Autowired
	private UsersRepository usersRepo;
	
	@Override
	public void insertBlockGroup(BlockGroup blockGroupParam, MultipartFile files) {
		BlockGroup blockGroup = new BlockGroup(
					blockGroupParam.getLinkUrl(),
					files.getOriginalFilename(), 
					files.getOriginalFilename(),
					500
				);
		
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
	
}
