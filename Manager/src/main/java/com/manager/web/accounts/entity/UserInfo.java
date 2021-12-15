package com.manager.web.accounts.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manager.common.type.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "user_info")
public class UserInfo extends SuperEntity implements UserDetails,CredentialsContainer {
	private static final long serialVersionUID = 1L;
	public static UserInfo createNewUserInfo(String username, String password) {
		UserInfo user = new UserInfo();
		user.username = username;
		user.password =password;
		user.enabled =true;
		user.accountNonExpired =true;
		user.accountNonLocked= true;
		user.credentialsNonExpired =true;
		return user;
	}

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",allocationSize = 1)
	@Id
	private Long id;
	@Column(name = "username", unique = true, length = 50, nullable = false)
	private String username;
	@JsonIgnore
	@Column(name = "password", unique = false, length = 255, nullable = false)
	private String password;
	@Column (name="email", unique =false, length = 255, nullable = true)
	private String email;
	@Column(name ="enabled",nullable = false)
	private boolean enabled;
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Role role; 
	@JsonIgnore
	@Column(name ="account_non_expired",nullable = false)
	private boolean accountNonExpired;
	@JsonIgnore
	@Column(name ="credentials_non_expired",nullable = false)
	private boolean credentialsNonExpired;
	@JsonIgnore
	@Column(name ="account_non_locked",nullable = false)
	private boolean accountNonLocked;
	// Xoá password sau khi đăng nhập
	@Override
	public void eraseCredentials() {
		password = null;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(role); 
	}
}
