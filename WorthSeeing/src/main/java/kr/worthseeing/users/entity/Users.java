package kr.worthseeing.users.entity;

import javax.persistence.Column;
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
public class Users {

	
	@Id
	@GeneratedValue
	private String userId;
	
	private String nickName;
	private String userPw;
	private String adress;
	private String detatiladress;
	private String	email;
	
	//@Column(nullable=false, columnDefinition = "n default 0")
	private String	adminyn;
	private String	blackyn;
	
	private String	joindate;
	private String	tel;
	private String	name;
	private int point;
	private int reservationCnt;
	private int finishedAuctionCnt;
	private int totalMoney;

}
 