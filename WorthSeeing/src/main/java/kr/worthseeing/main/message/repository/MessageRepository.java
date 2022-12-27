package kr.worthseeing.main.message.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.main.message.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Integer>, QuerydslPredicateExecutor<Message> {


}