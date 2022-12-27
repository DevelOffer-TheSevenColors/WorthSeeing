//package kr.worthseeing;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import kr.worthseeing.main.reservation.entity.Reservation;
//import kr.worthseeing.main.reservation.repository.ReservationRepository;
//import kr.worthseeing.status.entity.Status;
//import kr.worthseeing.status.repository.StatusRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@EnableScheduling
//class WorthSeeingApplicationTests {
//	
//	@Autowired
//	private StatusRepository statusRepo;
//
//	int DecimalToBinary(String A,int number) {
//        int Binary_number = 0;
//        int count = 0;
//        while (number != 0) {
//            int remainder = number % 2;
//            double temp = Math.pow(10, count);
//            Binary_number += remainder * temp;
//            number /= 2;
//            
//            count++;
//        }
//        
//        Status status = new Status(A,String.valueOf(String.format("%04d", Binary_number)));
//        statusRepo.save(status);
//        return Binary_number;
//    }
//
//	
//	@Test
//	public void StatusInsert() {
//		Status status = new Status();
//		
//		for(int i=1;i<5;i++) {
//			System.out.println( DecimalToBinary("A",i));
//		}
//		
//		for(int i=1;i<4;i++) {
//			System.out.println( DecimalToBinary("B",i));
//		}
//		
//		for(int i=1;i<8;i++) {
//		System.out.println( DecimalToBinary("C",i));
//		}
//		
//		for(int i=1;i<5;i++) {
//		System.out.println( DecimalToBinary("D",i));
//		}
//		
//	}
//	
//	@Autowired
//	ReservationRepository reservationRepo;
//	
//	
//	
//	@Test
//	public Reservation reservationList() {
//		
//		Reservation reservation = new Reservation();
//		
//		
//		
//		return reservationRepo.save(null);
//	}
//	
//	
//}
//
