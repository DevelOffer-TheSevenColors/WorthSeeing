package kr.worthseeing.users.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.users.entity.Users;

public interface UsersRepository  extends CrudRepository<Users, String>{
 
}
