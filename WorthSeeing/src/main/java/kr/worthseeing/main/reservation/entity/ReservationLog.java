package kr.worthseeing.main.reservation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class ReservationLog {

	@Id
	@GeneratedValue
	private int reservationLog_seq;

	private int startPrice;

	private int groupblock_seq;
	private int groupblockLog_seq;
	
	private int userCnt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date reservationTime; 

}