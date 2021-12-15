package com.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.manager.web.accounts.service.UserInfoService;
import com.vvt.bootstrap.Bootstrap;
import com.vvt.bootstrap.Bootstrap;
//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserInfoService loginService;
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	// thêm cái này vào config
	@Bean(name = "bootstrap")
	public Bootstrap bootstrap() {
		return new Bootstrap();
	}
	
	
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
        .authorizeRequests()
        .antMatchers("/**"
        		).permitAll() // ログインしなくてもアクセスできるページ
        .anyRequest().authenticated() //	以前のページ以外全部はログインしなきゃいけない
        .and()
        .formLogin()
        //.loginProcessingUrl("/doLogin")
        .permitAll()	//	ログインした後redirectページ
        .and()
        .logout()// ログアウトできる
        .permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService)
        .passwordEncoder(passwordEncoder());
	}

}
