package com.manager.web.accounts.service;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manager.web.accounts.entity.UserInfo;
import com.manager.web.accounts.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService{
	@Autowired
	UserInfoRepository userRep;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> op = userRep.findByUsername(username);
		if(!op.isPresent())
			throw new UsernameNotFoundException("NOT FOUND "+username);
		return op.get();
	}
	@Transactional
	public void save(UserInfo user) {
		userRep.save(user);
	}
	@Transactional
	//@PreAuthorize("hasAnyAuthority('ALL_PRIVILEGES','ANY_USER_DELETE') || hasAuthority('MY_USER_DELETE') AND #id == authentication.principal.id")
	public void deleteById(long id) {
		userRep.deleteById(id);
	}
	@Transactional
	//@PreAuthorize("hasAnyAuthority('ALL_PRIVILEGES','ANY_USER_SELECT') || hasAuthority('MY_USER_SELECT') AND #id == authentication.principal.id")
	//@PreAuthorize("hasRole('ADMIN')")
	public UserInfo getById(long id) {
		return userRep.findById(id).orElse(null);
	}
	
	public void login(UserInfo user) {
		UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
		auth.eraseCredentials();
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
