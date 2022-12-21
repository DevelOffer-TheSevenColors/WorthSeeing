package kr.worthseeing.reply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.reply.entity.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Integer>, QuerydslPredicateExecutor<Reply>{

//	@Query("select r from Reply r where r.notify.notifySeq == ?1")
//	Page<Reply> listReply(int notifySeq, Pageable pageable);
	
}
