package kr.worthseeing.main.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class AuctionServiceImpl implements AuctionService{

	@Autowired
	private AuctionRepository auctionRepo;
	
	@Autowired
	private AuctionLogRepository auctLogRepo;
	
	// 경매 기록 저장
	@Override
	public void insertAuctionLog(AuctionLog auctionLog) {
		auctLogRepo.save(auctionLog);
	}
	
	// 경매 기록 삭제
	@Override
	public void deleteAuctionLog(AuctionLog auctionLog) {
		auctLogRepo.deleteById(auctionLog.getAuctionLog_seq());
	}
	
	/*
	// 입찰 리스트
	@Override
	public Auction listAuction(Auction auction) {
		Auction listAuction = auctionRepo.findById(auction.getAuction_seq()).get();
		return listAuction;
	}
	*/
	// 경매 시작
	@Override
	public void insertAuction(Auction auction) {
		auctionRepo.save(auction);
	}
	
	// 경매 종료
	@Override
	public void deleteAuction(Auction auction) {
		auctionRepo.deleteById(auction.getAuction_seq());;
	}
	
	// 입찰 시 업데이트 
	@Override
	public void updateAuction(Auction auction) {
		Auction findAuction = auctionRepo.findById(auction.getAuction_seq()).get();
		System.out.println("@@@au"+findAuction);
		findAuction.setUsers(auction.getUsers());
		findAuction.setSuggestPrice(auction.getSuggestPrice());
		findAuction.setSuggestDate(auction.getSuggestDate());
		auctionRepo.save(findAuction);
	}
	
	public int findAuctionPrice(Auction auction) {
		return auctionRepo.findById(auction.getAuction_seq()).get().getFinishPrice();
	}
	
	// 경매 리스트 불러오기
	@Override
	public List<Auction> getlistAuction() {
		return (List<Auction>) auctionRepo.findAll();		
	}

	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	@Autowired
	private UsersRepository usersRepo;
	
	@Override
	public void updateCreditInfo(Users users, Auction auction) { // users : 낙찰받은사용자, auction : 낙찰된 블럭 + 가격 정보
		
		int blockGroup_Seq = auction.getReservation().getBlockGroup().getBlockGroup_seq();
		
		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup_Seq).get();
		
		
		findBlockGroup.setUsers(users);
//		findBlockGroup.setPrice(auction.getFinishPrice()); // 마지막에 이걸로 변경하기
		findBlockGroup.setPrice(30000); 
		
		
		blockGroupRepo.save(findBlockGroup);
		usersRepo.save(users);
	}

	@Override
	public Auction selectCredit(Auction auction) {
		
		return (Auction) auctionRepo.findAll();
	}

	
}
