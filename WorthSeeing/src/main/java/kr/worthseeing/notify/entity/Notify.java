package kr.worthseeing.notify.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import groovy.transform.ToString;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@ToString(excludes = {"status",  "users"})
@NoArgsConstructor
@AllArgsConstructor
public class Notify {

	@Id
	@GeneratedValue
	private int notify_seq;
	private String title;
	private String content;
	
	@Column(columnDefinition = "number default 0")
	private int viewCnt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date notifyTime;
	
	
	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false, updatable = false)
	private Status status;
	
	public void setStatus(Status status) {
		this.status = status;
		status.getNotifyList().add(this);
	}
	
//	@ManyToOne
//	@JoinColumn(name = "blockGroup_seq", nullable = false, updatable = false)
//	private BlockGroup blockGroup;
//	
//	public void setBlockGroup(BlockGroup blockGroup) {
//		this.blockGroup = blockGroup;
//		blockGroup.getNotifyList().add(this);
//	}

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false, updatable = false)
	private Users users;
	
	public void setUsers(Users users) {
		this.users = users;
		users.getNotifyList().add(this);
	}
	
	public Notify(int notify_seq, String title, String content, int viewCnt) {
		this.notify_seq = notify_seq;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
	}
	
}
