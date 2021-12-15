package com.familytree.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.familytree.common.exception.NotFoundException;
import com.familytree.detail.FamilyDetail;
import com.familytree.entity.Family;
import com.familytree.filter.FamilyFilter;
import com.familytree.form.FamilyForm;
import com.familytree.service.FamilyService;

@Controller
@RequestMapping(value = "/families", name = "Families")
public class FamilyController {
	public static final String PATH="families/";
	@Autowired FamilyService sc;
	
	@GetMapping(name = "add",value = "/add")
	public String add(@ModelAttribute("form") FamilyForm form) {
	 	return PATH+"FamilyAdd"; 
	}
	@PostMapping(value = "/add")
	public String add(@ModelAttribute("form") FamilyForm form, BindingResult result) {
		if(result.hasErrors())
			return PATH+"FamilyAdd"; 
		
		Family family = form.toEntity();
		sc.saveOrUpdate(family);
		return "redirect:"+MvcUriComponentsBuilder.fromMappingName("Families#detail").arg(0, family.getId()).build();
	}
	
	@GetMapping(name = "edit",value = "/edit/{pk:\\d+}")
	public String edit(@PathVariable("pk")long pk,Model model) {
		Family family = sc.getOneById(pk);
		if(family==null) {
			throw new NotFoundException("Không tìm thấy");
		}
		model.addAttribute("form", family);
	 	return PATH+"FamilyEdit"; 
	}
	@PostMapping(value = "/edit/{pk:\\d+}")
	public String edit(@PathVariable("pk")long pk,@ModelAttribute("form") FamilyForm form, BindingResult result) {
		Family family = sc.getOneById(pk);
		if(family==null) {
			throw new NotFoundException("Không tìm thấy");
		}
		if(result.hasErrors()) {
			return PATH+"FamilyEdit";
		}
		form.toEntity(family);
		sc.saveOrUpdate(family);
		return "redirect:"+MvcUriComponentsBuilder.fromMappingName("Families#detail").arg(0, family.getId()).build();
	}
	
	@GetMapping(value="/detail/{pk:\\d+}",name = "detail")
	public String detail(@PathVariable("pk") Long pk, Model model) {
		FamilyDetail family= sc.getDetail(pk);
		if(family== null) {
			throw new NotFoundException("Không tồn tại");
		}
		model.addAttribute("obj", family);
		return PATH+"FamilyDetail";
	}
	@RequestMapping(value="/delete/{pk:\\d+}",name = "delete")
	public String delete(@PathVariable("pk") Long pk) {
		if(!sc.delete(pk)) {
			throw new NotFoundException("Không tồn tại");
		}
		return "redirect:" + MvcUriComponentsBuilder.fromMappingName("Families#list").build();
	}
	@RequestMapping(value="/list", method = RequestMethod.GET, name="list")
	public String familyListInitial(@ModelAttribute("form") FamilyForm form) {
		return PATH +"FamilyList";
	}
	@RequestMapping(value="/list", method = RequestMethod.GET, params = "search")
	public String familyList(Model model,@ModelAttribute("form") FamilyForm form) {
		FamilyFilter filter= form.toFilter();
		System.out.println(filter);
		List<Family> list= sc.getAllByFilter(filter);
		model.addAttribute("result", list);
		return PATH +"FamilyList";
	}
	@RequestMapping(value = "/search/{key}", method = RequestMethod.GET, name = "search")
	public String searchInitial(@PathVariable("key") String key,@ModelAttribute("form") FamilyForm form) {
		return PATH+ "FamilySearch";
	}
	
	@RequestMapping(value = "/search/{key}", method = RequestMethod.GET, params = "search")
	public String search(@PathVariable("key") String key,Model model ,@ModelAttribute("form") FamilyForm form) {
		FamilyFilter filter= form.toFilter();
		System.out.println(filter);
		List<Family> list= sc.getAllByFilter(filter);
		model.addAttribute("result", list);
		model.addAttribute("key", key);
		return PATH+ "FamilySearch";
	}
}
