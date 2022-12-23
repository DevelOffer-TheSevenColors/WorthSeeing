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
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.main.reservation.entity.Reservation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertAuction {

	@Autowired
	private BlockRepository blockRepo;

	/*
	 * @Test public void insertReservation() { Reservation reservation = new
	 * Reservation(); reservation.setStartPrice(1500);
	 * reservation.setBlockGroup(21); }
	 */

	@Test
	public void blockChart() {
		int [] monthPrice = new int[12];
		List<Integer> chartPrice = new ArrayList<Integer>();
		String startYear = "2022";
		for (Block block : blockRepo.findAll()) {
			for (int i = 1; i <= 12; i++) {
				String i_str = String.valueOf(i);
				if (block.getEndDate() != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
					if (format.format(block.getEndDate()) != null) {
						if(i<=9)  i_str = "0"+i;
						if (format.format(block.getEndDate()).equals(startYear + i_str)) {
							monthPrice[i - 1] += block.getBlockPrice();
							System.out.println("@@==> enddate = " + block.getEndDate());
							System.out.println("@@==> index = " + (i - 1));
							System.out.println("@@==> price = " + block.getBlockPrice());
						}
					}
				}
			}
		}
		for(int i=0;i<=3;i++) {
			chartPrice.add(monthPrice[0+3*i]+monthPrice[1+3*i]+monthPrice[2+3*i]);
		}
	}
}
