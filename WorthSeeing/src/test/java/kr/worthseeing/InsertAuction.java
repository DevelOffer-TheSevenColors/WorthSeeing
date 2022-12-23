package kr.worthseeing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.main.reservation.entity.Reservation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertAuction {

	@Autowired
	private BlockRepository blockRepo;
	
	/*
	@Test
	public void insertReservation() {
		Reservation reservation = new Reservation();
		reservation.setStartPrice(1500);
		reservation.setBlockGroup(21);
	}
	*/
	
	@Test
	public void blockChart() {
		
		String strDate = "20200806";
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
		// String 타입을 Date 타입으로 변환
		Date formatDate = null;
		try {
			formatDate = dtFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환
		String strNewDtFormat = newDtFormat.format(formatDate);
		
		for(Block block : blockRepo.findAll()) {
			System.out.println("@@as"+block.getBlockPrice());
			System.out.println("@@as"+block.getEndDate());
			
			block.getEndDate();
		}

	}

}
