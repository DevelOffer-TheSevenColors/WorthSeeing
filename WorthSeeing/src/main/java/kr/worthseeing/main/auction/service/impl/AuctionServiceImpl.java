package kr.worthseeing.main.auction.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.repository.BlockRepository;
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

	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired
	private BlockGroupRepository  blockGroupRepo;
	
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

	@Override
	public Page<BlockGroup> selectAlwaysBuyList(BlockGroup blockGorup, Pageable pageable) {

		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "blockGroup_seq");
		
		
		blockGroupRepo.alwaysBuyListNoPage( 11);
		
		return blockGroupRepo.alwaysBuyList(pageable ,11);
		
	}
	@Override
	public List<BlockGroup> selectAlwaysBuyListNoPage() {
		
		
		List<BlockGroup> findBlockGroup=  blockGroupRepo.alwaysBuyListNoPage(11);
		
		for(BlockGroup findBlockGroup2 : findBlockGroup) {
			
			LocalDate now= LocalDate.now();  //현재시간
		      
		      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현
		      
		      LocalDate date = LocalDate.parse(String.valueOf(now));  //현재 시간  스트링으로 변환
		      
		        LocalDate startDate = LocalDate.now();  //지금시간
		        
		        LocalDate endDate = date.withDayOfMonth(date.lengthOfMonth());  //현재 월의 일 수
		        
		        LocalDateTime date1 = startDate.atStartOfDay(); //현재시간 계산할수있도록 변환 -시작
		        LocalDateTime date2 = endDate.atStartOfDay();   //현재월일수를 계산할수있도록 변환 -끝
		        int betweenDays = (int) Duration.between(date1, date2).toDays();    //시작과 끝을 빼서 계산한 값
		        
		        endDate.getDayOfMonth();// 이번달 마지막날을 int로 가져오는것
		        
		        
		        findBlockGroup2.setPrice( findBlockGroup2.getPrice() / endDate.getDayOfMonth() * betweenDays);         
		        
		       System.out.println( "제발"+findBlockGroup2);
		}
		
		
		return blockGroupRepo.alwaysBuyListNoPage(11);
		
	}

	@Override
	public BlockGroup alwaysBuyCreditView(BlockGroup blockGroup ) {
		

        
		return 	  blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
	}
	
	@Override
	public void updateAlwaysCreditInfo(BlockGroup blockGroup,Status status, Users user) { // users : 낙찰받은사용자, auction : 낙찰된 블럭 + 가격 정보

		
		Users findUser=usersRepo.findById(user.getUserId()).get();
		findUser.setPoint(user.getPoint());
		
		BlockGroup findBlockGroup= blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
		
		LocalDate now= LocalDate.now();
		
		
			
			
		findBlockGroup.setPrice(blockGroup.getPrice());
		findBlockGroup.setPurchaseDay(new Date());
		findBlockGroup.setStatus(status);
		
		
		 
		blockGroupRepo.save(findBlockGroup);
		usersRepo.save(findUser);
	}
	
	
}










