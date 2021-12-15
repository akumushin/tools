package com.manager.web.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.manager.common.type.Role;
import com.manager.web.accounts.entity.UserInfo;
import com.manager.web.accounts.form.UserRegisterForm;
import com.manager.web.accounts.service.UserInfoService;
import com.manager.web.customers.entity.Customer;
import com.manager.web.customers.form.CustomerForm;
import com.manager.web.customers.service.CustomerService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
	public static final String PATH = "customer/";
	@Autowired
	UserInfoService userSc;
	@Autowired
	CustomerService sc;
	@GetMapping("/register")
	public String register(Model model) {
		var form = new UserRegisterForm();
		form.setUsername("thuong");
		form.setEmail("b");
		form.setRePassword("bbb");
		model.addAttribute("form", form);
		return PATH +"Register";
	}
	@PostMapping("/register")
	public String registerSuccess(@ModelAttribute(name = "form")@Validated UserRegisterForm form, BindingResult result) {
		if(result.hasErrors()) {
			return PATH +"Register";
		}
		Customer customer = new Customer();
		customer.setUser(form.toUserInfo(Role.CUSTOMER));
		sc.save(customer);
		userSc.login(customer.getUser());
		return "redirect:profiles";
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/edit")
	public String edit(Model model,Authentication authentication ) {
		UserInfo user= (UserInfo) authentication.getPrincipal();
		Customer customer= sc.findByUserId(user.getId());
		model.addAttribute("form", CustomerForm.from(customer));
		return PATH +"Edit";
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/edit")
	public String editSuccess(@ModelAttribute(name="form")@Validated CustomerForm form, BindingResult result, Authentication authentication ) {
		if(result.hasErrors()) {
			return PATH +"Edit";
		}
		//result.getFieldError(PATH).
		UserInfo user= (UserInfo) authentication.getPrincipal();
		Customer customer = form.toCustomer();
		customer.setId(user.getId());
		sc.update(customer);
		return "redirect:profiles";
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/profiles")
	public String profiles(Model model,Authentication authentication) {
		UserInfo user= (UserInfo) authentication.getPrincipal();
		Customer customer= sc.findByUserId(user.getId());
		model.addAttribute("customer", customer);
		return PATH+"Profiles";
	}
}
