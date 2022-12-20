package kr.worthseeing.reply.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.notify.entity.Notify;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "notify")
@NoArgsConstructor
public class Reply {

	@Id
	@GeneratedValue
	private int reply_seq;
	private String replyContent;
	private String replyer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date replyDate;
	
	@ManyToOne
	@JoinColumn(name="notifySeq", nullable = false)
	private Notify notify;
	
	public void setNotify(Notify notify) {
		this.notify = notify;
		notify.getReplyList().add(this);
	}
	
	
}