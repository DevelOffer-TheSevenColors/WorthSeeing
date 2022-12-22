package kr.worthseeing.main.auction.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
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

	@Autowired
	private UsersRepository usersRepo;
	
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
	public void endAuction(Reservation reservation, BlockGroup blockGroup) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		BlockGroupWaiting bgwr = new BlockGroupWaiting();
		bgwr.setPrice(findAuction.getSuggestPrice());
		bgwr.setUserId(findAuction.getUserId());
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
	@Override
	public List<Auction> listAuction() {

		return (List<Auction>) auctionRepo.findAll();
	}

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
		findAuction.setUserId(auction.getUserId());
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
		System.out.println("@@1@@");
		System.out.println("@@re"+reservation.getReservation_seq());
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		if (findAuction.getSuggestPrice() * 1.1 <= findAuction.getMaxPrice()
				&& !findAuction.getUserId().equals(findAuction.getUserAutoId())) {
			findAuction.setUserId(findAuction.getUserAutoId());
			findAuction.setSuggestPrice((int) (findAuction.getSuggestPrice() * 1.1));
			findAuction.setSuggestDate(new Date());
		} else if ((findAuction.getSuggestPrice() * 1.1 >= findAuction.getMaxPrice())
				&& (findAuction.getSuggestPrice() < findAuction.getMaxPrice())) {
			findAuction.setUserId(findAuction.getUserAutoId());
			findAuction.setSuggestPrice((int) (findAuction.getMaxPrice()));
			findAuction.setSuggestDate(new Date());
		} else if (findAuction.getSuggestPrice() >= findAuction.getMaxPrice()) {
			findAuction.setMaxPrice(0);
			findAuction.setUserAutoId(null);
		}
		auctionRepo.save(findAuction);
	}

	@Override
	public Auction findAuction(Reservation reservation) {
		return auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
	}

	@Override
	public Auction selectCredit(Auction auction) {

		return (Auction) auctionRepo.findAll();
	}


	//낙찰받은 블록 결제하기 
			@Override
			public void updateCreditInfo(BlockGroupWaiting blockGroupWaiting ,Status status, Users user, int month) { // users : 낙찰받은사용자, auction : 낙찰된 블럭 + 가격 정보

				
				Users findUser=usersRepo.findById(user.getUserId()).get();
				findUser.setPoint(user.getPoint());
				
				BlockGroupWaiting findBlockGroupWaiting = blockGroupWaitingRepo.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();
				
				LocalDate now= LocalDate.now();
				
				
					if(month == 1) {
						LocalDate result1= now.plusMonths(1);
						Date enddate1 = java.sql.Date.valueOf(result1);
						findBlockGroupWaiting.setEndDate(enddate1);
				
					}else if(month == 2){
						LocalDate result1= now.plusMonths(2);
						Date enddate1 = java.sql.Date.valueOf(result1);
						findBlockGroupWaiting.setEndDate(enddate1);
					
					}else if(month ==3) {
						LocalDate result1= now.plusMonths(3);
						Date enddate1 = java.sql.Date.valueOf(result1);
						findBlockGroupWaiting.setEndDate(enddate1);
					}
				
					
					
				findBlockGroupWaiting.setPrice(blockGroupWaiting.getPrice());
				findBlockGroupWaiting.setPurchaseDay(new Date());
				findBlockGroupWaiting.setStatus(status);
				
				
				
				blockGroupWaitingRepo.save(findBlockGroupWaiting);
				usersRepo.save(findUser);
			}

	  @Override
      public BlockGroupWaiting auctionCreditView(BlockGroupWaiting blockGroupWaiting) {

         
         BlockGroupWaiting findblockGroupWaiting=   
                  blockGroupWaitingRepo.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();
         
            return findblockGroupWaiting;
      }

}