package kr.worthseeing.main.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.main.reservation.entity.ReservationUsers;

public interface ReservationUsersRepository  extends CrudRepository<ReservationUsers, Integer> ,QuerydslPredicateExecutor<ReservationUsers>{
  
	
	@Query(" SELECT r FROM ReservationUsers r Where user_Id = ?1  "  )
	List<ReservationUsers> findReservationUsers(String keyword);
}
