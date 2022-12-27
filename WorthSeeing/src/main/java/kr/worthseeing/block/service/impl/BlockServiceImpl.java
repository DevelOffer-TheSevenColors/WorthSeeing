package kr.worthseeing.block.service.impl;

import java.util.ArrayList;
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

	@Override
	public List<Integer> getBlockXY(int firstNum, int lastNum) {

		int temp = 0;

		if (firstNum > lastNum) {
			temp = firstNum;
			firstNum = lastNum;
			lastNum = temp;
		}

		int x1 = blockRepo.findById(firstNum).get().getXLocation();
		int y1 = blockRepo.findById(firstNum).get().getYLocation();

		int x2 = blockRepo.findById(lastNum).get().getXLocation();
		int y2 = blockRepo.findById(lastNum).get().getYLocation();

		int width = Math.abs(x2 - x1) + 1;
		int height = Math.abs(y2 - y1) + 1;
		
		List<Integer> intList = new ArrayList<Integer>();
		List<Block> blockList = (List<Block>) blockRepo.findAll();

		if (width * height <= 10) { // 총 개수가 10개 이하일 때

			for (Block block : blockList) {
				if (x1 <= block.getXLocation() && block.getXLocation() <= x2 && y1 <= block.getYLocation()
						&& block.getYLocation() <= y2) { // x, y 좌표가 firstNum과 lastNum 사이일 때

					Block findBlock = blockRepo.findById(block.getBlock_seq()).get();

					Status status = new Status();
					
					// 범위 내의 블록이 전부 그룹핑 가능할 때
					if (block.getStatus().getStatus_seq() == 8) { // 사용중/그룹핑가능
						
						// 사용중/그룹핑불가능
						status.setStatus_seq(9);
						findBlock.setStatus(status);
						
						// 블록 리스트 추가
						intList.add(block.getBlock_seq());
						
						blockRepo.save(findBlock);
					}

					else if (block.getStatus().getStatus_seq() == 10) { // 미사용중/그룹핑가능
						
						// 미사용중/그룹핑불가능
						status.setStatus_seq(11);
						findBlock.setStatus(status);
						
						// 블록 리스트 추가
						intList.add(block.getBlock_seq());
						
						blockRepo.save(findBlock);
						
//						BlockGroup blockGroup = new BlockGroup();
//						Status status = new Status();
//						status.setStatus_seq(11);
//						blockGroup.setStatus(status);

//						findBlock.setBlockGroup(null);

						// 리스트에 있는 애들을 블록그룹으로 새로 추가 => block 테이블의 blockGroup_seq를 새로 만든 blockGroup_seq로
						// 변경
						// status를 11(그룹핑된 상태)로 변경

					} else {
						System.out.println("그룹핑 불가능한 블록을 선택함");
						return null;
					}
					
				} // x, y 좌표가 firstNum과 lastNum 사이일 때
				
			} // for 반복문


		} else {
			System.out.println("10개 초과함");
			return null;
		}
		
		// ------- 그룹핑 가능인 상태 -------------------
		
		BlockGroup blockGroup = new BlockGroup();
		blockGroup.setWidth(width * 100);
		blockGroup.setHeight(height * 100);
		
		Users users = new Users();
		users.setUserId("testid");
		blockGroup.setUsers(users);
		
		blockGroupRepo.save(blockGroup);
		
		BlockGroupWaiting blockGroupWaiting = new BlockGroupWaiting();
		
		blockGroupWaiting.setWidth(width * 100);
		blockGroupWaiting.setHeight(height * 100);
		
		Status status = new Status();
		status.setStatus_seq(14);
		blockGroupWaiting.setStatus(status);
		blockGroupWaitingRepo.save(blockGroupWaiting);
		
		System.out.println("SUCCESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("blockLIst---->" + intList.toString());
		
		return intList;
		
	}

}
