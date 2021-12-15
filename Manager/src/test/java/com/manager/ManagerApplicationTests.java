package com.manager;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.manager.web.accounts.service.UserInfoService;

@SpringBootTest
class ManagerApplicationTests {
	@Autowired UserInfoService sc;
	@Autowired PasswordEncoder pwdEncoder;
	@Test
	void contextLoads() {
	}

}
