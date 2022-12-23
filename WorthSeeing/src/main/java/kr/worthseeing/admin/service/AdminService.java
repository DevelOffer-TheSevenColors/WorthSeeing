package kr.worthseeing.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.users.entity.Users;

public interface AdminService { 

Page<Users> selectUsers(Pageable pageable);

void blackList(List<String> userId, List<String> blackYn);

}
