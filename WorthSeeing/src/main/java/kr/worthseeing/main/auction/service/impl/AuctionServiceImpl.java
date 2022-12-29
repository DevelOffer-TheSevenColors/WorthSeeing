package kr.worthseeing.main.auction.service.impl;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.pointlog.entity.PointLog;
import kr.worthseeing.event.pointlog.repository.PointLogRepository;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.repository.ReservationLogRepository;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.status.repository.StatusRepository;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionRepository auctionRepo;
	
	@Autowired
	private BlockRepository blockRepo;

	@Autowired
	private AuctionLogRepository auctLogRepo;

	@Autowired
	private StatusRepository statusRepo;
	
	@Autowired
	private BlockGroupRepository blockGroupRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;
	
	@Autowired
	private AuctionLogRepository auctionLogRepo;

	@Autowired
	private PointLogRepository pointLogRepo;
	
	@Autowired
	private ReservationLogRepository reservationLogRepo;

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
	public void endAuction(Reservation reservation, BlockGroupWaiting blockGroupWaiting) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		findAuction.setUserAutoId("");
		findAuction.setMaxPrice(0);
		System.out.println("이야호호ㅗ1111");
		if(blockGroupWaitingRepo.endAuctionConfirm(blockGroupWaiting.getBlockGroupWaiting_seq())!=null) {
			BlockGroupWaiting bgwr = blockGroupWaitingRepo.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();
			System.out.println("이야호호ㅗ22222");
			Status status = new Status();
			status.setStatus_seq(16);
			bgwr.setPrice(findAuction.getSuggestPrice());
			System.out.println("이야호호ㅗ3333");
			System.out.println("@@@aaadwD"+findAuction.getUserId());
			bgwr.setUsers(usersRepo.findById(findAuction.getUserId()).get());
			System.out.println("이야호호ㅗ444");
			bgwr.setStatus(status);
			System.out.println("이야호호ㅗ5555");
			bgwr.setAuctionDate(findAuction.getSuggestDate());
			auctionRepo.save(findAuction);
			System.out.println("이야호호ㅗ33333");
			blockGroupWaitingRepo.save(bgwr);
		}
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
		
		
		//auction로그 저장
		AuctionLog auctionLog=new AuctionLog();
		auctionLog.setAuctionPrice(findAuction.getAuctionPrice());
		auctionLog.setAuction_seq(findAuction.getAuction_seq());
		auctionLog.setSuggestPrice(findAuction.getSuggestPrice());
		auctionLog.setSuggestDate(findAuction.getSuggestDate());
		auctionLog.setUserId(findAuction.getUserId());

		auctionLogRepo.save(auctionLog);
		
		
		
	}

	@Override
	public void updateMaxPrice(Reservation reservation, String maxPrice, Users user) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		findAuction.setUserAutoId(user.getUserId());
		findAuction.setMaxPrice(Integer.parseInt(maxPrice));
		auctionRepo.save(findAuction);
	}

	@Override
	public void autoBiddingStop(Reservation reservation) {
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		findAuction.setUserAutoId("");
		findAuction.setMaxPrice(0);
		auctionRepo.save(findAuction);
	}

	@Override
	public void autoAuction(Reservation reservation) {
		System.out.println("@@1@@");
		System.out.println("@@re" + reservation.getReservation_seq());
		Auction findAuction = auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
		if (findAuction.getSuggestPrice() * 1.1 <= findAuction.getMaxPrice()
				&& !findAuction.getUserId().equals(findAuction.getUserAutoId())) {
			findAuction.setUserId(findAuction.getUserAutoId());
			findAuction.setSuggestPrice((int) (findAuction.getSuggestPrice() * 1.1));
			findAuction.setSuggestDate(new Date());
		} else if ((findAuction.getSuggestPrice() * 1.1 >= findAuction.getMaxPrice())
				&& (findAuction.getSuggestPrice() < findAuction.getMaxPrice())
				&&!findAuction.getUserId().equals(findAuction.getUserAutoId())) {
			findAuction.setUserId(findAuction.getUserAutoId());
			findAuction.setSuggestPrice((int) (findAuction.getMaxPrice()));
			findAuction.setSuggestDate(new Date());
		} else if (findAuction.getSuggestPrice() >= findAuction.getMaxPrice()) {
			findAuction.setMaxPrice(0);
			findAuction.setUserAutoId(null);
		}
		auctionRepo.save(findAuction);
		
		
		//auction로그 저장
		AuctionLog auctionLog=new AuctionLog();
		auctionLog.setAuctionPrice(findAuction.getAuctionPrice());
		auctionLog.setAuction_seq(findAuction.getAuction_seq());
		auctionLog.setSuggestPrice(findAuction.getSuggestPrice());
		auctionLog.setSuggestDate(findAuction.getSuggestDate());
		auctionLog.setUserId(findAuction.getUserId());
		auctionLog.setReservationLog(reservationLogRepo.findById(reservation.getReservation_seq()).get());

		auctionLogRepo.save(auctionLog);
		
		
		
	}

	@Override
	public Auction findAuction(Reservation reservation) {
		return auctionRepo.findByAuction(reservation.getReservation_seq()).get(0);
	}

	@Override
	public Auction selectCredit(Auction auction) {

		return (Auction) auctionRepo.findAll();
	}

	// 낙찰받은 블록 결제하기
	@Override
	public void updateCreditInfo(BlockGroupWaiting blockGroupWaiting, Status status, Users user, int month) { // users :
																												// 낙찰받은사용자,
																												// auction
																												// : 낙찰된
																												// 블럭 +
																												// 가격 정보
		Users findUser = usersRepo.findById(user.getUserId()).get();
		findUser.setPoint(user.getPoint());

		BlockGroupWaiting findBlockGroupWaiting = blockGroupWaitingRepo
				.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();

		LocalDate now = LocalDate.now();
		
		now.getDayOfMonth();
		
		if (month == 1) {
			  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현

			  LocalDate Ldate =now.plusMonths(1);
			int lastdat=Ldate.withDayOfMonth(Ldate.lengthOfMonth()).getDayOfMonth();
			int month1 = now.plusMonths(1).getMonthValue();
			int yeser =now.getYear();
			String date=""+yeser+"-" +  month1 + "-" + lastdat;
			 Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			findBlockGroupWaiting.setEndDate(date1);

		} else if (month == 2) {
			  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현

			  LocalDate Ldate =now.plusMonths(2);
			  
				int lastdat=Ldate.withDayOfMonth(Ldate.lengthOfMonth()).getDayOfMonth();
				
				
				int month1 = now.plusMonths(2).getMonthValue();
				int yeser =now.getYear();
				String date=""+yeser+"-" +  month1 + "-" + lastdat;
				 Date date1 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				findBlockGroupWaiting.setEndDate(date1);
		} else if (month == 3) {
			  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현
			  LocalDate Ldate =now.plusMonths(3);
				int lastdat=Ldate.withDayOfMonth(Ldate.lengthOfMonth()).getDayOfMonth();
				int month1 = now.plusMonths(3).getMonthValue();
				int yeser =now.getYear();
				String date=""+yeser+"-" +  month1 + "-" + lastdat;
				 Date date1 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				findBlockGroupWaiting.setEndDate(date1);
		}
		findBlockGroupWaiting.setPrice(blockGroupWaiting.getPrice());
		findBlockGroupWaiting.setPurchaseDay(new Date());
		findBlockGroupWaiting.setStatus(status);

		blockGroupWaitingRepo.save(findBlockGroupWaiting);
		usersRepo.save(findUser);
		
		
		PointLog pointLog = new PointLog();
		
		pointLog.setPoint(findUser.getPoint());
		pointLog.setPointDate(new Date());
		pointLog.setUserid(user.getUserId());
		
		pointLogRepo.save(pointLog);
	}

	@Override
	public BlockGroupWaiting auctionCreditView(BlockGroupWaiting blockGroupWaiting) {

		BlockGroupWaiting findblockGroupWaiting = blockGroupWaitingRepo
				.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();

		return findblockGroupWaiting;
	}


	   @Override
	   public Page<Block> selectAlwaysBuyList(Pageable pageable) {

		   
	      int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
	      pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "block_seq");
	      
	      
	      return blockRepo.alwaysBuyList(pageable);
	   }
	   
	   @Override
	public List<Integer> selectAlwaysBuyListPrice() {
		return blockRepo.alwaysBuyListGetPrice();
	}

	@Override
	public Block alwaysBuyCreditView(Block block) {

		return blockRepo.findById(block.getBlock_seq()).get();
	}

	@Override
	public void updateAlwaysCreditInfo(Block block, Status status, Users user) { // users : 낙찰받은사용자, auction :
																							// 낙찰된 블럭 + 가격 정보

		Users findUser = usersRepo.findById(user.getUserId()).get();
		findUser.setPoint(user.getPoint());

		Block findBlock = blockRepo.findById(block.getBlock_seq()).get();

//		LocalDate now = LocalDate.now();

		BlockGroup blockGroup = new BlockGroup();
		
		blockGroup.setPrice(findBlock.getBlockPrice());
		blockGroup.setPurchaseDay(new Date());
		blockGroup.setHeight(100);
		blockGroup.setWidth(100);
		blockGroup.setUsers(findUser);
		

		blockGroupRepo.save(blockGroup);
		
		PointLog pointLog = new PointLog();
		
		pointLog.setPoint(findUser.getPoint());
		pointLog.setPointDate(new Date());
		pointLog.setUserid(user.getUserId());
		
		pointLogRepo.save(pointLog);
	}

	@Override
	public String auctionAttendBtnYes() {
		String flag = "yes";
		if (auctionRepo.findAll().iterator().hasNext()) {
			flag = "no";
		}
		return flag;
	}
	
}
