package kr.worthseeing.main.message.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUserId;
import lombok.Data;

@Entity
@Data
public class Message {
	
	@Id
	@GeneratedValue
	private String toUser;
	private String msg;
	private Date sendTime;
	
	
	@OneToMany(mappedBy = "message")
	private List<ReservationUserId> reservationList = new ArrayList<ReservationUserId>();
}
