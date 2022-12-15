package kr.worthseeing.users.service;

import java.util.List;

import kr.worthseeing.users.entity.Users;

public interface UsersService {

	void insertUsers(Users users);
	
	void updateUsers(Users users);
	
	void deleteUsers(Users users);
	
	Users getUsers(Users users);
	
	Users listUsers();
	
}
