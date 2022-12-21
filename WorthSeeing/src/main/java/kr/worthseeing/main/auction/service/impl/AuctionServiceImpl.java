package kr.worthseeing.main.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.status.repository.StatusRepository;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionRepository auctionRepo;

	@Autowired
	private AuctionLogRepository auctLogRepo;
	
	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;
	
	@Autowired
	private StatusRepository statusRepo;

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

	@Override
	public void endAuction(Reservation reservation,BlockGroup blockGroup) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		BlockGroupWaiting bgwr = new BlockGroupWaiting();
		bgwr.setPrice(findAuction.getSuggestPrice());
		bgwr.setUserId(findAuction.getUsers().getUserId());
		bgwr.setStatus(statusRepo.findById(12).get());
		bgwr.setBlockGroup(blockGroup);
		bgwr.setAuctionDate(findAuction.getSuggestDate());
		blockGroupWaitingRepo.save(bgwr);
	}

	/*
	 * // 입찰 리스트
	 * 
	 * @Override public Auction listAuction(Auction auction) { Auction listAuction =
	 * auctionRepo.findById(auction.getAuction_seq()).get(); return listAuction; }
	 */
	// 경매 시작
	@Override
	public void insertAuction(Auction auction) {
		auctionRepo.save(auction);
	}

	// 경매 종료
	@Override
	public void deleteAuction(Auction auction) {
		auctionRepo.deleteById(auction.getAuction_seq());
	}

	// 입찰 시 업데이트
	@Override
	public void updateAuction(Auction auction, Reservation reservation) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		findAuction.setUsers(auction.getUsers());
		findAuction.setSuggestPrice(auction.getSuggestPrice());
		findAuction.setSuggestDate(auction.getSuggestDate());
		auctionRepo.save(findAuction);
	}
	
	@Override
	public void updateMaxPrice(Reservation reservation, String maxPrice, Users user) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		findAuction.setUserAutoId(user.getUserId());
		findAuction.setMaxPrice(Integer.parseInt(maxPrice));
		auctionRepo.save(findAuction);
	}
	
	@Override
	public void autoAuction(Reservation reservation) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		if(findAuction.getSuggestPrice()*1.1<=findAuction.getMaxPrice()&&!findAuction.getUsers().getUserId().equals(findAuction.getUserAutoId())) {
			findAuction.getUsers().setUserId(findAuction.getUserAutoId());
			findAuction.setUsers(findAuction.getUsers());
			findAuction.setSuggestPrice((int)(findAuction.getSuggestPrice()*1.1));
			findAuction.setSuggestDate(new Date());
		}else if( (findAuction.getSuggestPrice()*1.1>=findAuction.getMaxPrice())
				&& (findAuction.getSuggestPrice()<findAuction.getMaxPrice()) ){
			findAuction.getUsers().setUserId(findAuction.getUserAutoId());
			findAuction.setUsers(findAuction.getUsers());
			findAuction.setSuggestPrice((int)(findAuction.getMaxPrice()));
			findAuction.setSuggestDate(new Date());
		}else if( findAuction.getSuggestPrice()>=findAuction.getMaxPrice() ){
			findAuction.setMaxPrice(0);
			findAuction.setUserAutoId(null);
		}
		auctionRepo.save(findAuction);
	}

	@Override
	public Auction findAuction(Reservation reservation) {
		return auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
	}

	@Autowired
	private BlockGroupRepository blockGroupRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private AuctionRepository auctionRepository;

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

	@Override
	public void auctionCreditView(Auction auction, Users users) {

		Users findUser = usersRepo.findById(users.getUserId()).get();
		Auction findAuction = auctionRepository.findById(auction.getAuctionPrice()).get();
		findAuction.setSuggestPrice(auction.getSuggestPrice());

		findUser.setBlockGroupList((List<BlockGroup>) auction.getReservation().getBlockGroup());

	}

}
