package kr.worthseeing.mypage.service.impl;

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

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
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
	private AuctionRepository auctionRepo;

	@Autowired
	private AuctionLogRepository auctionLogRepo;
	
	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;
	

	@Override
	public Users getUsers(Users users) {
		return usersRepo.findById(users.getUserId()).get();
	}

	@Override
	public String getClick(BlockGroup blockGroup) {
		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
		findBlockGroup.setClickCnt(findBlockGroup.getClickCnt()+1);
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
	public List<BlockGroup> getBlockGroupUserId(String userId) {
		return (List<BlockGroup>) blockGroupRepo.findByUserId(userId);
	}
	
	public Page<BlockGroupWaiting> getBlockGroupPage(String userId, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.ASC, "blockGroupWaiting_seq");
		
		return blockGroupWaitingRepo.findByUserList(userId,pageable);
	}
	

	@Override
	public List<Coupon> getCouponUserId(String userId) {
		return (List<Coupon>) couponRepo.findByUserId(userId);
	}
	
	@Override
	public Page<Coupon> getCouponUserPage(String userId,Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "couponUsedDate");
		
		return couponRepo.findByUserPage(userId,pageable);
	}
	
	@Transactional
	@Override
	public void getUserPoint(Users users, String price, Coupon coupon) {
		Users findUsers = usersRepo.findById(users.getUserId()).get();
		findUsers.setPoint(findUsers.getPoint() - Integer.parseInt(price));
		usersRepo.save(findUsers);

		Coupon findCoupon = couponRepo.findByCoupon(5, Integer.parseInt(price)).get(0); // status가 2인 쿠폰 리스트의 index 0번째 데이터 가져오기
		System.out.println("finidCoupon impol====>" + findCoupon);
		
		Status status = new Status();
		status.setStatus_seq(6);
		
		findCoupon.setStatus(status);
		findCoupon.setUsers(findUsers);
		
		findCoupon.setCouponUsedDate(new Date());
		
		couponRepo.save(findCoupon);
		System.out.println("-==-=--==-=-=-=-=---[][][][][][][]" + findUsers);
	}
//	@Override
//	public List<Coupon> getleftOverCoupon() {
//	List<Coupon> leftOverCoupon = couponRepo.findLeftOverCoupon();
//		return leftOverCoupon;
//	}
	@Override
	public Page<Coupon> leftOverCouponPage(Coupon coupon,Pageable pageable) {
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
	public Page<BlockGroupWaiting> selectBlockGroupWaiting(String userId,int status_seq,Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "endDate");
		
		return blockGroupWaitingRepo.selectBlockGroupWaiting(userId,pageable);
		
	}

	@Override
	public BlockGroupWaiting findBlockGroupWaiting(BlockGroupWaiting blockGroupWaiting) {
		return blockGroupWaitingRepo.findById(blockGroupWaiting.getBlockGroupWaiting_seq()).get();
	}
	
	
	@Override
	public List<BlockGroup> getListBlockGroup() {
		
		return (List<BlockGroup>) blockGroupRepo.findAll();
	}
	
	//잔여쿠폰 페이징
	
	
	//회원정보 수정
	@Override
	public void userUpdateProc(Users users) {
		Users findUser = usersRepo.findById(users.getUserId()).get();
		
		findUser.setDetailAddress(users.getDetailAddress());
		findUser.setAddress(users.getAddress());
		findUser.setTel(users.getTel());
		findUser.setEmail(users.getEmail());
		findUser.setNickName(users.getNickName());
		
		usersRepo.save(findUser);
	}
	//쿠폰 등록
		@Override
		public void getCouponAdd(Coupon coupon) {
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
				
				coupon.setStatus(status);
				coupon.setCouponSerialNum(couponnum);
				coupon.setCouponPrice(3000000);
				couponRepo.save(coupon);
			}
		}
	
	
}
