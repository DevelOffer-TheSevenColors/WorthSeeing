package kr.worthseeing.mypage.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.entity.CouponLog;
import kr.worthseeing.event.coupon.repository.CouponLogRepository;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.event.pointlog.entity.PointLog;
import kr.worthseeing.event.pointlog.repository.PointLogRepository;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.repository.ReservationRepository;
import kr.worthseeing.main.reservation.repository.ReservationUsersRepository;
import kr.worthseeing.mypage.service.MyPageService;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
@Component
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private BlockGroupRepository blockGroupRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private CouponRepository couponRepo;

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private AuctionRepository auctionRepo;

	@Autowired
	private AuctionLogRepository auctionLogRepo;

	@Autowired
	private ReservationUsersRepository reservationUsersRepo;

	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;

	@Autowired
	private CouponLogRepository couponLogRepo;

	@Autowired
	private PointLogRepository pointLogRepo;

	@Autowired
	private BlockRepository blockRepo;

	@Override
	public Users getUsers(Users users) {
		return usersRepo.findById(users.getUserId()).get();
	}

	@Override
	public String getClick(BlockGroup blockGroup) {
		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
		findBlockGroup.setClickCnt(findBlockGroup.getClickCnt() + 1);
		blockGroupRepo.save(findBlockGroup);
		return findBlockGroup.getLinkUrl();
	}

	@Override
	public void add500Point(Users users) {
		Users findUsers = usersRepo.findById(users.getUserId()).get();

		findUsers.setPoint(findUsers.getPoint() + 500);
		findUsers.setDailyClickCheck("완료");

		usersRepo.save(findUsers);
	}

	@Override
	public Page<BlockGroup> getBlockGroupUserId(String userId, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 4, Sort.Direction.ASC, "blockGroup_seq");
		return blockGroupRepo.findByUserId(userId, pageable);
	}

	public Page<BlockGroupWaiting> getBlockGroupPage(String userId, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.ASC, "blockGroupWaiting_seq");

		return blockGroupWaitingRepo.findByUserList(userId, pageable);
	}

	@Override
	public List<Coupon> getCouponUserId(String userId) {
		return (List<Coupon>) couponRepo.findByUserId(userId);
	}

	@Override
	public Page<Coupon> getCouponUserPage(String userId, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "couponUsedDate");

		return couponRepo.findByUserPage(userId, pageable);
	}

	@Transactional
	@Override
	public void getUserPoint(Users users, String price, Coupon coupon) {
		Users findUsers = usersRepo.findById(users.getUserId()).get();
		findUsers.setPoint(findUsers.getPoint() - Integer.parseInt(price));
		usersRepo.save(findUsers);

		Coupon findCoupon = couponRepo.findByCoupon(5, Integer.parseInt(price)).get(0); // status가 2인 쿠폰 리스트의 index 0번째
																						// 데이터 가져오기

		Status status = new Status();
		status.setStatus_seq(6);

		findCoupon.setStatus(status);
		findCoupon.setUsers(findUsers);

		findCoupon.setCouponUsedDate(new Date());

		couponRepo.save(findCoupon);

		CouponLog couponLog = new CouponLog();

		couponLog.setCouponPrice(findCoupon.getCouponPrice());
		couponLog.setCouponSerialNum(findCoupon.getCouponSerialNum());
		couponLog.setCouponUsedDate(findCoupon.getCouponUsedDate());
		couponLog.setUserid(users.getUserId());

		couponLogRepo.save(couponLog);

		PointLog pointLog = new PointLog();

		pointLog.setPoint(findUsers.getPoint());
		pointLog.setPointDate(new Date());
		pointLog.setUserid(users.getUserId());

	}

//	@Override
//	public List<Coupon> getleftOverCoupon() {
//	List<Coupon> leftOverCoupon = couponRepo.findLeftOverCoupon();
//		return leftOverCoupon;
//	}
	@Override
	public Page<Coupon> leftOverCouponPage(Coupon coupon, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.ASC, "couponPrice");

		return couponRepo.findCouponList(pageable);

	}

	@Override
	public List<Integer> getCouponCount() {
		List<Integer> cntList = new ArrayList<Integer>();
		int tenthousandCnt = 0;
		int fiftythousandCnt = 0;
		int hundredthousandCnt = 0;

		for (Coupon coupon : couponRepo.findLeftOverCoupon()) {
			if (coupon.getCouponPrice() == 30000) {
				tenthousandCnt++;
			} else if (coupon.getCouponPrice() == 50000) {
				fiftythousandCnt++;
			} else {
				hundredthousandCnt++;
			}
		}
		cntList.add(tenthousandCnt);
		cntList.add(fiftythousandCnt);
		cntList.add(hundredthousandCnt);
		System.out.println("cnt===========================>" + cntList);
		return cntList;
	}

	@Override
	public Map<Integer, List<AuctionLog>> getAuctionLogUserId(String userId) {
		Map<Integer, List<AuctionLog>> auctionLogMap = new HashMap<Integer, List<AuctionLog>>();
		auctionLogMap.put(1, auctionLogRepo.findByUserId(userId, 1));
		auctionLogMap.put(2, auctionLogRepo.findByUserId(userId, 2));
		System.out.println("map--->" + auctionLogMap.get(1).toString());
		System.out.println("map--->" + auctionLogMap.get(2).toString());

		return auctionLogMap;
	}

	@Override
	public Page<BlockGroupWaiting> selectBlockGroupWaiting(String userId, int status_seq, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "endDate");

		return blockGroupWaitingRepo.selectBlockGroupWaiting(userId, pageable);

	}

	@Override
	public BlockGroupWaiting findBlockGroupWaiting(BlockGroupWaiting blockGroupWaiting) {
		return blockGroupWaitingRepo.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();
	}

	@Override
	public List<BlockGroup> getListBlockGroup() {

		return (List<BlockGroup>) blockGroupRepo.findAll();
	}

	// 잔여쿠폰 페이징

	// 회원정보 수정
	@Override
	public void userUpdateProc(Users users) {

		System.out.println("하222" + users);

		Users findUser = usersRepo.findById(users.getUserId()).get();

		findUser.setDetailAddress(users.getDetailAddress());
		findUser.setAddress(users.getAddress());
		findUser.setTel(users.getTel());
		findUser.setEmail(users.getEmail());
		findUser.setNickName(users.getNickName());

		usersRepo.save(findUser);
	}

	// 쿠폰 등록
	@Override
	public void getCouponAdd(Coupon coupon, int price) {
		Status status = new Status();
		status.setStatus_seq(5);
		for (int i = 0; i < 20; i++) {
			int couponSize = 3;
			final char[] possibleCharacters = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D',
					'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
					'Y', 'Z' };
			final int possibleCharacterCount = possibleCharacters.length;
			Random rnd = new Random();
			int currentIndex = 0;
			int j = 0;
			String couponnum = "";

			while (currentIndex < couponSize) {
				StringBuffer buf = new StringBuffer(16);
				for (j = 8; j > 0; j--) {
					buf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
				}
				couponnum += buf.toString() + "-";
				currentIndex++;
			}

			couponnum = couponnum.substring(0, couponnum.length() - 1);
			System.out.println("=====asdf==>" + couponnum);

			Coupon newCoupon = new Coupon();

			newCoupon.setStatus(status);
			newCoupon.setCouponSerialNum(couponnum);
			newCoupon.setCouponPrice(price);
			couponRepo.save(newCoupon);
		}
	}

	@Override
	@Transactional
	public void startService() {
		auctionRepo.deleteAll();
		reservationUsersRepo.deleteAll();
		for (BlockGroupWaiting blockWaiting : blockGroupWaitingRepo.findAll()) {
			if (blockWaiting.getBlockGroupWaiting_seq() != 1) {
				BlockGroup blockGroup = new BlockGroup();
				blockGroup.setCImg(blockWaiting.getCImg());

				System.out.println("safasfsaf@@11" + blockWaiting);

				if (blockWaiting.getEndDate() != null) {
					LocalDate localDate = new java.sql.Date(blockWaiting.getEndDate().getTime()).toLocalDate(); // java.sql.Date
					System.out.println("safasfsaf@@22" + blockWaiting);
					blockGroup.setEndDate(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}
				blockGroup.setLinkUrl(blockWaiting.getLinkUrl());
				blockGroup.setPrice(blockWaiting.getPrice());
				blockGroup.setPurchaseDay(blockWaiting.getPurchaseDay());
				blockGroup.setSImg(blockWaiting.getSImg());
				blockGroup.setStartDate(blockWaiting.getStartDate());
				blockGroup.setUsers(blockWaiting.getUsers());
				blockGroup.setMinBlockSeq(blockWaiting.getMinBlockSeq());
				blockGroup.setHeight(blockWaiting.getHeight());
				blockGroup.setWidth(blockWaiting.getWidth());
				System.out.println("safasfsaf@@33" + blockWaiting);
				blockGroupRepo.save(blockGroup);
				System.out.println("safasfsaf@@44" + blockWaiting);
				Pageable pageable = PageRequest.of(0, 1, Sort.Direction.DESC, "blockGroup_seq");
				System.out.println("fEWEFAWF3W@@@" + blockGroupRepo.listBlockGroup(pageable).getContent());
				BlockGroup blockGroup_ = blockGroupRepo.listBlockGroup(pageable).getContent().get(0);
				System.out.println("asdwqf@@@@asd@" + blockGroup_);
				for (Block block : blockRepo
						.findAuctionBlock(String.valueOf(blockWaiting.getBlockGroupWaiting_seq()))) {
					Status status_ = new Status();
					status_.setStatus_seq(8);
					BlockGroupWaiting blockGroupwait = new BlockGroupWaiting();
					blockGroupwait.setBlockGroupWaiting_seq(1);
					block.setBlockGroup(blockGroup_);
					block.setBlockGroupWaiting(blockGroupwait);
					block.setStatus(status_);
					blockRepo.save(block);
				}
			}
			Status status = new Status();
			status.setStatus_seq(13);
			blockWaiting.setStatus(status);
			blockGroupWaitingRepo.save(blockWaiting);
		}
	}

	@Override
	public void deleteWaiting() {
		reservationRepo.deleteAll();
	}
}