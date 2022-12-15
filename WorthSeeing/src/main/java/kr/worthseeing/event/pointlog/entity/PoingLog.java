package kr.worthseeing.event.pointlog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class PoingLog {

	@Id
	@GeneratedValue
	private int pointLog_seq;

	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private String userid;
	
	private int point;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pointDate;
}
