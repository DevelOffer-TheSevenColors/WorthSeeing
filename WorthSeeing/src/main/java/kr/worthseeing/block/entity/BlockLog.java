package kr.worthseeing.block.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlockLog {

	@Id
	@GeneratedValue
	private int blockLog_seq;
	private int block_seq;
	private int groupBlock_seq;
	private int soldOutCnt;
	
}
