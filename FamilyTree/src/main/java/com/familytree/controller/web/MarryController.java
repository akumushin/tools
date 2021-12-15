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
import com.familytree.detail.MarryDetail;
import com.familytree.entity.Marry;
import com.familytree.filter.MarryFilter;
import com.familytree.form.MarryForm;
import com.familytree.service.MarryService;

@Controller
@RequestMapping(value = "/marries", name="Marries")
public class MarryController {
	public static final String PATH="marries/";
	@Autowired MarryService sc;
	
	@GetMapping(value = "/add", name="add")
	public String add(@ModelAttribute("form") MarryForm form) {
		return PATH+ "MarryAdd";
	}
	@PostMapping(value = "/add")
	public String add(@ModelAttribute("form") MarryForm form, BindingResult result) {
		if(result.hasErrors())
			return PATH+"MarryAdd"; 
		
		Marry marry = form.toEntity();
		sc.saveOrUpdate(marry);
		return "redirect:"+MvcUriComponentsBuilder.fromMappingName("Marries#detail")
			.arg(0, marry.getId())
			.build();
	}
	
	@RequestMapping(value = "detail/{id:\\d+}", name="detail")
	public String detail(@PathVariable(name="id")long id, Model model ) {
		MarryDetail detail= sc.getDetailById(id);
		if(detail==null)
			throw new NotFoundException("Không tồn tại");
		model.addAttribute("obj", detail);
		return PATH+"MarryDetail";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET, name="list")
	public String marryListInitial(@ModelAttribute("form") MarryForm form) {
		return PATH +"MarryList";
	}
	@RequestMapping(value="/list", method = RequestMethod.GET, params = "search")
	public String marryList(Model model,@ModelAttribute("form") MarryForm form) {
		MarryFilter filter= form.toFilter();
		List<Marry> list= sc.getAllByFilter(filter);
		model.addAttribute("result", list);
		return PATH +"MarryList";
	}
	
	@RequestMapping(value="/delete/{id:\\d+}", name="delete")
	public String delete(@PathVariable(name="id")long id, Model model
			) {
		if(!sc.delete(id))
			throw new NotFoundException("Không tồn tại");
		return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("Marries#list").build();
	}
}
