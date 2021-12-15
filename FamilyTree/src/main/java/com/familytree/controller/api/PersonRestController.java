package com.familytree.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.familytree.entity.Person;
import com.familytree.filter.PersonFilter;
import com.familytree.form.PersonForm;
import com.familytree.service.PersonService;

@RestController
@RequestMapping(value = "/api/persons", name="PersonsAPI")
public class PersonRestController {
	@Autowired PersonService sc;
	@RequestMapping(value="/search", name="search")
	public ResponseEntity<List<Person>> search(PersonForm form){
		PersonFilter filter= form.toFilter();
		System.out.println(filter);
		List<Person> list= sc.getAllByFilter(filter);
		return ResponseEntity.ok(list);
	}
}
