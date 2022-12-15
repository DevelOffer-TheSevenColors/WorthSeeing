package kr.worthseeing.event.pointlog.entity;

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
public class PointLog {

	@Id
	@GeneratedValue
	private int pointLog_seq;

	private String userid;
	private int point;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date pointDate;
}
