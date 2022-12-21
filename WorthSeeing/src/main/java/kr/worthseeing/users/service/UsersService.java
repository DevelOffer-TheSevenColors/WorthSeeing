package kr.worthseeing.users.service;

import java.util.List;

import kr.worthseeing.users.entity.Users;

public interface UsersService {

	Users getUsers(Users member);

	public void deleteUsers(Users member);

	public void insertUsers(Users member);

	public void updateUsers(Users member);
	
	public List<Users> listUsers();
	
	public String findUser(Users user);
	
}