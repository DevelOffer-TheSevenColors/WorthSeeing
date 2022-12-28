package kr.worthseeing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.entity.BlockLog;
import kr.worthseeing.block.repository.BlockLogRepository;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.log.service.LogService;
import kr.worthseeing.log.service.impl.BlockLogServiceImpl;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.main.reservation.entity.Reservation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertAuction {

	@Autowired
	private LogService logService;
	
	@Autowired
	private BlockLogRepository blockLogRepo;
	
//	@Autowired
//	private BlockGroupLogRepository blockGroupLogRepo;

	@Autowired
	private AuctionRepository auctionRepo;
	
	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitRepo;
	
	/*
	 * @Test public void insertReservation() { Reservation reservation = new
	 * Reservation(); reservation.setStartPrice(1500);
	 * reservation.setBlockGroup(21); }
	 */

//	@Test
//	public void blockChart() {
//		int [] monthPrice = new int[12];
//		List<Integer> chartPrice = new ArrayList<Integer>();
//		String startYear = "2022";
//		for (Block block : blockRepo.findAll()) {
//			for (int i = 1; i <= 12; i++) {
//				String i_str = String.valueOf(i);
//				if (block.getEndDate() != null) {
//					SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
//					if (format.format(block.getEndDate()) != null) {
//						if(i<=9)  i_str = "0"+i;
//						if (format.format(block.getEndDate()).equals(startYear + i_str)) {
//							monthPrice[i - 1] += block.getBlockPrice();
//							System.out.println("@@==> enddate = " + block.getEndDate());
//							System.out.println("@@==> index = " + (i - 1));
//							System.out.println("@@==> price = " + block.getBlockPrice());
//						}
//					}
//				}
//			}
//		}
//		for(int i=0;i<=3;i++) {
//			chartPrice.add(monthPrice[0+3*i]+monthPrice[1+3*i]+monthPrice[2+3*i]);
//		}
//	}

//	@Test
//	public void addlog() {
//		BlockLog blockLog = null;
//		BlockGroupLog blockGroupLog = null;
//		for(int i = 0; i<289; i++) {
//			blockLog = new BlockLog();
//			blockGroupLog = new BlockGroupLog();
//			blockLog.setBlock_seq(i);
//			blockLog.setBlockGroupLog(blockGroupLogRepo.findById(i).get());
//			blockLog.setBlockPrice(1000*i);
//			blockLog.setSoldOutCnt(i);
//			blockLogRepo.save(blockLog);
//			blockGroupLog.setAvgPrice(i*100);
//			blockGroupLog.setBlockGroup_seq(i);
//			blockGroupLog.setClickCnt(i);
//			blockLog.setBlock_seq(1);
//			blockLog.setSoldOutCnt(i);
//			blockLog.setBlockGroupLog(blockGroupLogRepo.findById(630+i).get());
//			blockGroupLogRepo.save(blockGroupLog);
//			blockLogRepo.save(blockLog);
//		}
//	}
	
//	@Test
	public void blockChart() {
		String block_seq = "1";
		String startYear = "2022";
		int[] monthPrice = new int[12];
		if (!logService.blockChartList(block_seq).isEmpty()) {
			for (BlockLog blockLog : logService.blockChartList(block_seq)) {
				for (int i = 1; i <= 12; i++) {
					String i_str = String.valueOf(i);
					if (blockLog.getEndDate() != null) {
						SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
						if (format.format(blockLog.getEndDate()) != null) {
							if (i <= 9)
								i_str = "0" + i;
							if (format.format(blockLog.getEndDate()).equals(startYear + i_str)) {
								monthPrice[i - 1] += blockLog.getBlockPrice();
								System.out.println("@@==> enddate = " + blockLog.getEndDate());
								System.out.println("@@==> index = " + (i - 1));
								System.out.println("@@==> price = " + blockLog.getBlockPrice());
							}
						}
					}
				}
			}
		}
	}
//	@Test
	public void blockChart1() {
		String block_seq = "1";
		String startYear = "2022";
		int[] monthPrice = new int[12];
		if (!logService.blockChartList(block_seq).isEmpty()) {
			for (BlockLog blockLog : logService.blockChartList(block_seq)) {
				for (int i = 1; i <= 12; i++) {
					String i_str = String.valueOf(i);
					if (blockLog.getEndDate() != null) {
						SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
						if (format.format(blockLog.getEndDate()) != null) {
							if (i <= 9)
								i_str = "0" + i;
							if (format.format(blockLog.getEndDate()).equals(startYear + i_str)) {
								monthPrice[i - 1] += blockLog.getBlockPrice();
								System.out.println("@@==> enddate = " + blockLog.getEndDate());
								System.out.println("@@==> index = " + (i - 1));
								System.out.println("@@==> price = " + blockLog.getBlockPrice());
							}
						}
					}
				}
			}
		}
		List<Integer> monthPriceList = new ArrayList<Integer>();
		for(int price : monthPrice) {
			monthPriceList.add(price);
		}
		
//		return monthPriceList;
	}
	
//	@Test
	public void asd() {
		System.out.println(auctionRepo.findAll().iterator().hasNext());
	}
	
	@Test
	public void weg(){
		blockGroupWaitRepo.deleteBlockGroupWaiting();
	}
	
	
}
