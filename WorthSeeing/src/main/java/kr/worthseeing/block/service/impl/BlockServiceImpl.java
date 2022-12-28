package kr.worthseeing.block.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
	private BlockRepository blockRepo;

	@Autowired
	private BlockGroupRepository blockGroupRepo;

	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;

	@Override
	public Block getBlock(Block block) {
		return blockRepo.findById(block.getBlock_seq()).get();
	}

	@Override
	public List<Block> listBlock() {
		return (List<Block>) blockRepo.findAll();
	}

	@Override
	public List<Block> findAuctionBlock(BlockGroupWaiting blockGroupWaiting) {
		return blockRepo.findAuctionBlock(String.valueOf(blockGroupWaiting.getBlockGroupWaiting_seq()));
	}
	
	// 그룹핑 가능한 블록 리스트
	@Override
	public List<Integer> availableGroupingblock() {
		System.out.println("testsetset---->" + blockRepo.availableGroupingblock().toString());
		return blockRepo.availableGroupingblock();
	}

	@Override
	public List<Integer> getBlockXY(int firstNum, int lastNum) {

		int temp = 0;

		if (firstNum > lastNum) {
			temp = firstNum;
			firstNum = lastNum;
			lastNum = temp;
		}

		Block blockFirstNum = blockRepo.findById(firstNum).get();
		Block blockLastNum = blockRepo.findById(lastNum).get();

		int x1 = blockFirstNum.getXLocation();
		int y1 = blockFirstNum.getYLocation();

		int x2 = blockLastNum.getXLocation();
		int y2 = blockLastNum.getYLocation();

		int width = Math.abs(x2 - x1) + 1;
		int height = Math.abs(y2 - y1) + 1;

		List<Integer> intList = new ArrayList<Integer>();
		List<Block> blockList = (List<Block>) blockRepo.findAll();

		if (width * height <= 10) { // 총 개수가 10개 이하일 때

			for (Block block : blockList) {
				if (x1 <= block.getXLocation() && block.getXLocation() <= x2 && y1 <= block.getYLocation()
						&& block.getYLocation() <= y2) { // x, y 좌표가 firstNum과 lastNum 사이일 때

					Block findBlock = blockRepo.findById(block.getBlock_seq()).get();

					int blockStatus = block.getStatus().getStatus_seq();

					Status status = new Status();

					// 범위 내의 블록이 전부 그룹핑 가능할 때
					if (blockStatus != 8 && blockStatus != 10) {
						System.out.println("그룹핑 불가능한 블록을 선택함");
						return null;
					}

					else if (blockStatus == 8) { // 사용중/그룹핑가능

						// 사용중/그룹핑불가능
						status.setStatus_seq(9);
						findBlock.setStatus(status);

						// 블록 리스트 추가
						intList.add(block.getBlock_seq());

						blockRepo.save(findBlock);
					}

					else if (blockStatus == 10) { // 미사용중/그룹핑가능

						// 미사용중/그룹핑불가능
						status.setStatus_seq(11);
						findBlock.setStatus(status);

						// 블록 리스트 추가
						intList.add(block.getBlock_seq());

						blockRepo.save(findBlock);

					}

					else {
						System.out.println("그룹핑 불가능");
						return null;
					}

				} // x, y 좌표가 firstNum과 lastNum 사이일 때

			} // for 반복문

		} else {
			System.out.println("10개 초과함");
			return null;
		}

		// ------- 그룹핑 가능인 상태 -------------------
		// 1. blockGroupWaiting에 insert
		// 2. reservation에 insert
		// 3. reservationUserId에 insert
		
		Users users = new Users();
		users.setUserId("testid");

		BlockGroupWaiting blockGroupWaiting = new BlockGroupWaiting();

		blockGroupWaiting.setWidth(width * 100);
		blockGroupWaiting.setHeight(height * 100);

		Status status = new Status();
		status.setStatus_seq(14);
		blockGroupWaiting.setStatus(status);
		blockGroupWaiting.setMinBlockSeq(Collections.min(intList));
		blockGroupWaiting.setUsers(users);

		blockGroupWaitingRepo.save(blockGroupWaiting);

		for (int item : intList) {
			Block findBlockWaitingSeq = blockRepo.findById(item).get();
			findBlockWaitingSeq.setBlockGroupWaiting(blockGroupWaiting);
			blockRepo.save(findBlockWaitingSeq); // block에 blockGroupWaiting_seq Update
		}

		return intList;
	}

}