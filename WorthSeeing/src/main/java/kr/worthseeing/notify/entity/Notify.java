package kr.worthseeing.notify.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import groovy.transform.ToString;
import kr.worthseeing.reply.entity.Reply;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@ToString(excludes = {"status", "users"})
@AllArgsConstructor
@NoArgsConstructor
public class Notify {

	@Id
	@GeneratedValue
	private int notifySeq;
	private String title;
	private String content;
	
	@Column(columnDefinition = "number default 0")
	private int viewCnt;
	
	private Date notifyTime = new Date();
	
	@ManyToOne 
	@JoinColumn(name = "status_seq", nullable = false)
	private Status status;
	
	public void setStatus(Status status) {
		this.status = status;
		status.getNotifyList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users users;
	
	public void setUsers(Users users) {
		this.users = users;
		users.getNotifyList().add(this);
	}
	
	@OneToMany(mappedBy = "notify", orphanRemoval = true)
	private List<Reply> replyList = new ArrayList<Reply>();
	
	public Notify(int notifySeq, String title, String content, int viewCnt) {
		this.notifySeq = notifySeq;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
	}
}
