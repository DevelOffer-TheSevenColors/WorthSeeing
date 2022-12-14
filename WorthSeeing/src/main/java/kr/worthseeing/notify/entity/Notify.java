package kr.worthseeing.notify.entity;

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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notify {

	@Id
	@GeneratedValue
	private int notify_seq;
	private String title;
	private String content;
	private int groupBlock_seq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date notifyTime;
	
}
