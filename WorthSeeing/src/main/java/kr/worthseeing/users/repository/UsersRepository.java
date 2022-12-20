package kr.worthseeing.users.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.users.entity.Users;


public interface UsersRepository  extends CrudRepository<Users, String>,QuerydslPredicateExecutor<Users>{

	@Query("select u From Users u")
	Page<Users> findAll(Pageable pageable);
	
	
	
}