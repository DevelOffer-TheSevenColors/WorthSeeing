package kr.worthseeing.main.reservation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.main.message.entity.Message;
import kr.worthseeing.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"reservation", "users"})
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUsers  {

	@Id
	@GeneratedValue
	private int ReservationUsers_seq;

	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationUserIdDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "reservation_seq")
	private Reservation reservation;

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
		reservation.getReservationUsersList().add(this);
	}

	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users users;

	public void setUsers(Users users) {
		this.users = users;
		users.getReservationUsersList().add(this);
	}
	
//	@OneToOne
//	@JoinColumn(name = "message_seq", nullable = false)
//	private Message message;
//	
//	public void setMessage(Message message) {
//		this.message = message;
//		message.setReservationUserId(this);
//	}
	

}
