package kr.worthseeing.main.reservation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"member","replyList","fileList"})
public class Reservation {
	
	
	@Id
	@GeneratedValue
	private int reservation_seq;
	
	private int startPrice;
	
	private int groupblock_seq;
	
	private int userCnt;
	

}
