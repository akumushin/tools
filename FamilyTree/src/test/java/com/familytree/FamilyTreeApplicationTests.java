package com.familytree;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.familytree.entity.Person;
import com.familytree.filter.PersonFilter;
import com.familytree.jpa.PersonManager;
import com.familytree.service.PersonService;

@SpringBootTest
class FamilyTreeApplicationTests {
	@Autowired PersonManager ma;
	@Autowired PersonService sc; 
	//@Test
	void contextLoads() {
		Person person =new Person();
		person.setId(5L);
		person.setName("van anh");
		//person.setSex(Sex.MALE);
		person.setBirthday(20191112);
		//person.setAlive(true);
		person.setDeadday(0);
		person.setFather(new Person());
		person.getFather().setId(2L);
		person.setMother(new Person());
		person.getMother().setId(1L);
		sc.saveOrUpdate(person);
		System.out.println(person.getId());
	}
	//@Test
	void test2() {
		PersonFilter filter =new PersonFilter();
		filter.setFather(new PersonFilter());
		filter.getFather().setName("thuong");;
		List<Person> list = sc.getAllByFilter(filter);
		list.forEach((p)->{System.out.println(p.getName());});
		System.out.println(ma.totalByFilter(filter));
	}
	//@Test
	void test3(){
		Person p =sc.getOneById(5);
		System.out.println(p.getName() +"--" + p.getFather().getName());
	}
	@Test
	void delete() {
		sc.getOneById(5L);
	}
}
