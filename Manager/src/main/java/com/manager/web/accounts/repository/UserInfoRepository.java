package com.manager.web.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manager.web.accounts.entity.UserInfo;
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findByUsername(String username);
	@Modifying
	@Query("UPDATE UserInfo u SET u.password = :password WHERE u.id = :id")
	void updatePasswordById(String password, long id);
	@Modifying
	@Query("UPDATE UserInfo u SET u.email = :email WHERE u.id = :id")
	void updateEmailById(String email, long id);
}
