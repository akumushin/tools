package com.familytree.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.familytree.common.exception.NotFoundException;
import com.familytree.common.valid.ValidType;
import com.familytree.detail.PersonDetail;
import com.familytree.entity.Person;
import com.familytree.filter.PersonFilter;
import com.familytree.form.PersonForm;
import com.familytree.service.MarryService;
import com.familytree.service.PersonService;

@Controller
@RequestMapping(value=PersonController.URL, name ="Persons")
public class PersonController {
	public static final String PATH="persons/";
	public static final String URL ="/persons";
	@Autowired PersonService sc;
	@Autowired MarryService marrySc;
	/**
	 * ADD 
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.GET,name = "add")
	public String addGet(Model model,UriComponentsBuilder builder, @ModelAttribute("form") PersonForm form) {
		//System.out.println(MvcUriComponentsBuilder.fromMappingName(PATH));
		return PATH + "PersonAdd";
	}
	/**
	 * Add
	 * @param form
	 * @param result
	 * @param builder
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addPost(
			@Validated(ValidType.Register.class) @ModelAttribute("form") PersonForm form, 
			BindingResult result,UriComponentsBuilder builder) {
		if(result.hasErrors())
			return PATH + "PersonAdd";
		System.out.println(form);
		Person person = form.toEntity();
		
		System.out.println(person);
		sc.saveOrUpdate(person);
		//URI successUrl ;
		return "redirect:" + MvcUriComponentsBuilder.fromMappingName("Persons#detail").arg(0, person.getId()).build();
	}
	
	@RequestMapping(value="/edit/{pk}", method = RequestMethod.GET, name ="edit" )
	public String editGet(@PathVariable(name = "pk",required = true) long pk,Model model) {
		Person person= sc.getOneById(pk);
		if(person==null) {
			throw new NotFoundException("Không tìm thấy");
		}
		model.addAttribute("obj", person);
		return PATH + "PersonEdit";
	}
	
	@RequestMapping(value="/edit/{pk:\\d+}", method = RequestMethod.POST)
	public String editPost(Model model,@PathVariable("pk") long pk, 
			@Validated(ValidType.Edit.class) PersonForm form, BindingResult result,
			UriComponentsBuilder builder
			) {
		Person person= sc.getOneById(pk);
		System.out.println(person);
		if(person==null) {
			throw new NotFoundException("Không tìm thấy");
		}
		if(result.hasErrors()) {
			model.addAttribute("obj", person);
			return PATH+ "PersonEdit";
		}
		form.toEntity(person);
		sc.saveOrUpdate(person);
		return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("Persons#detail").arg(0, person.getId()).build();
	}
	@RequestMapping(value = "/search/{key}", method = RequestMethod.GET, name = "search")
	public String searchInitial(@PathVariable("key") String key,@ModelAttribute("form") PersonForm form) {
		return PATH+ "PersonSearch";
	}
	
	@RequestMapping(value = "/search/{key}", method = RequestMethod.GET, params = "search")
	public String search(@PathVariable("key") String key,Model model ,@ModelAttribute("form") PersonForm form) {
		PersonFilter filter= form.toFilter();
		System.out.println(filter);
		List<Person> list= sc.getAllByFilter(filter);
		model.addAttribute("result", list);
		model.addAttribute("key", key);
		return PATH+ "PersonSearch";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET, name="list")
	public String personListInitial(@ModelAttribute("form") PersonForm form) {
		return PATH +"PersonList";
	}
	@RequestMapping(value="/list", method = RequestMethod.GET, params = "search")
	public String personList(Model model,@ModelAttribute("form") PersonForm form) {
		PersonFilter filter= form.toFilter();
		System.out.println(filter);
		List<Person> list= sc.getAllByFilter(filter);
		model.addAttribute("result", list);
		return PATH +"PersonList";
	}
	
	@RequestMapping(value="/detail/{pk}", method = RequestMethod.GET, name ="detail" )
	public String personDetail(@PathVariable(name = "pk",required = true) long pk,Model model) {
		PersonDetail person= sc.getDetailById(pk);
		if(person==null) {
			throw new NotFoundException("Không tìm thấy");
		}
		model.addAttribute("obj", person);
		return PATH + "PersonDetail";
	}
	@RequestMapping(value="/delete/{pk}", method = {RequestMethod.POST,RequestMethod.GET}, name ="delete" )
	public String personDelete(@PathVariable(name = "pk",required = true) long pk,Model model) {
		if(!sc.delete(pk)) {;
			throw new NotFoundException("Không tìm thấy");
		}
		return "redirect:"+ MvcUriComponentsBuilder.fromMappingName("Persons#list").build();
	}
	
}
