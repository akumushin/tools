package com.manager.web.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.web.accounts.entity.UserInfo;
import com.manager.web.accounts.service.UserInfoService;

@Controller
@RequestMapping("/accounts")

public class MyUserController {
	public static final String PATH ="accounts/";
	@Autowired UserInfoService userService;
	/**
	 * profile
	 * @param authentication
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/profiles")
	String profilesPage( Authentication authentication ) {
		//System.out.println(userService.getById(1).getUsername());
		return PATH + "Profiles";
	}
	
	/*
	 * @PreAuthorize("isAuthenticated()")
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/profiles/json") UserInfo profilesPage2(Authentication
	 * authentication) { System.out.println(authentication);
	 * 
	 * // System.out.println(userService.getById(1).getUsername()); return return
	 * (UserInfo)authentication.getPrincipal(); }
	 */
}
