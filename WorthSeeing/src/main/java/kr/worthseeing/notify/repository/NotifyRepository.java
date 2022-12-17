package kr.worthseeing.notify.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.notify.entity.Notify;

public interface NotifyRepository extends CrudRepository<Notify, Integer>, QuerydslPredicateExecutor<Notify> {

	@Query("select n from Notify n")
	Page<Notify> getNotifyList(Pageable pageable);

}
