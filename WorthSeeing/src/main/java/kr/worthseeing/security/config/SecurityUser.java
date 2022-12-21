package kr.worthseeing.security.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.worthseeing.users.entity.Users;

public class SecurityUser extends User{
	
	private static final long serialVersionUID = 121452452463L;
	private Users user;
	
	public SecurityUser(Users user) {
		super(user.getUserId(), user.getUserPw(), 
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}
	
	public Users getUsers() {
		return user;
	}
	
}