package com.familytree.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.familytree.entity.Family;
import com.familytree.entity.Marry;
import com.familytree.entity.Person;
import com.familytree.filter.PersonFilter;
import com.familytree.jpa.PersonManager;
@Repository
public class PersonManagerImpl implements PersonManager {
	@PersistenceContext EntityManager em;
	
	@Override
	@Transactional
	public void saveOrUpdate(Person entity) {
		Person p=em.merge(entity);
		entity.setId(p.getId());
	}

	@Override
	@Transactional
	public int delete(Long pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//update child
		CriteriaUpdate<Person> updateQuery = cb.createCriteriaUpdate(Person.class);
		//father
		Root<?> root = updateQuery.from(Person.class);
		updateQuery.set(root.<Person>get("father"), cb.nullLiteral(Person.class));
		updateQuery.where(cb.equal(root.get("father").get("id"), pk));
		em.createQuery(updateQuery).executeUpdate();
		updateQuery = cb.createCriteriaUpdate(Person.class);
		//mother
		root = updateQuery.from(Person.class);
		updateQuery.set(root.<Person>get("mother"), cb.nullLiteral(Person.class));
		updateQuery.where(cb.equal(root.get("mother").get("id"), pk));
		em.createQuery(updateQuery).executeUpdate();
		//marry
		CriteriaDelete<Marry> marryDelete =cb.createCriteriaDelete(Marry.class);
		root = marryDelete.from(Marry.class);
		marryDelete.where(cb.or(cb.equal(root.get("husband").get("id"), pk),cb.equal(root.get("wife").get("id"), pk)));
		em.createQuery(marryDelete).executeUpdate();
		//delete
		CriteriaDelete<Person> query = cb.createCriteriaDelete(Person.class);
		root =  query.from(Person.class);
		query.where(cb.equal(root.get("id"), pk));
		return em.createQuery(query).executeUpdate();
	}

	@Override
	@Transactional
	public Person getOne(Long pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> root =  query.from(Person.class);
		root.fetch("father", JoinType.LEFT);
		root.fetch("mother", JoinType.LEFT);
		root.fetch("family", JoinType.LEFT);
		query.where(cb.equal(root.get("id"), pk));
		return em.createQuery(query).getSingleResult();
	}

	@Override
	@Transactional
	public List<Person> findByFilter(PersonFilter ...filters) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> root =  query.from(Person.class);
		Predicate p = cb.disjunction();
		for(int i=0;i<filters.length;i++) {
			p.getExpressions().add(predicateFromFilter(filters[i], root, cb, query));
		}
		query.where(p);
		root.fetch("father", JoinType.LEFT);
		root.fetch("mother", JoinType.LEFT);
		root.fetch("family", JoinType.LEFT);
		return em.createQuery(query).getResultList();
		
	}

	@Override
	@Transactional
	public List<Person> findByFilterOnePage(PersonFilter filter, int pageNo, int pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> root =  query.from(Person.class);
		root.fetch("father", JoinType.LEFT);
		root.fetch("mother", JoinType.LEFT);
		root.fetch("family", JoinType.LEFT);
		query.where(predicateFromFilter(filter, root, cb, query));
		return em.createQuery(query).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).getResultList();
	}

	@Override
	@Transactional
	public long totalByFilter(PersonFilter filter) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Person> root =  query.from(Person.class);
		query.select(cb.count(root));
		query.where(predicateFromFilter(filter, root, cb, query));
		return em.createQuery(query).getSingleResult();
	}

	public static Predicate predicateFromFilter(PersonFilter filter, From<?,Person> root, CriteriaBuilder cb, AbstractQuery<?> query) {
		Predicate p =cb.conjunction();
		if(filter.getId() !=null)
			p.getExpressions().add(cb.equal(root.get("id"), filter.getId()));
		if(filter.getName()!=null) {
			String name = '%' + filter.getName().toUpperCase().replace(' ', '%') + '%';
			p.getExpressions().add(cb.like(cb.upper(root.get("name")), name));
		}
			
		if(filter.getSex()!=null)
			p.getExpressions().add(cb.equal(root.get("sex"), filter.getSex()));
		if(filter.getAlive()!=null)
			p.getExpressions().add(cb.equal(root.get("alive"), filter.getAlive()));
		if(filter.getBirthday()!=null)
			p.getExpressions().add(cb.equal(root.get("birthday"), filter.getBirthday()));
		if(filter.getDeadday()!=null)
			p.getExpressions().add(cb.equal(root.get("deadday"), filter.getDeadday()));
		if(filter.getFather() != null) {
			Join<Person, Person> join = root.join("father");
			p.getExpressions().add(predicateFromFilter(filter.getFather(), join, cb, query));
		}
		if(filter.getMother()!=null) {
			Join<Person, Person> join = root.join("mother");
			p.getExpressions().add(predicateFromFilter(filter.getMother(), join, cb, query));
		}
		if(filter.getFamily()!=null) {
			Join<Person, Family> join = root.join("family");
			p.getExpressions().add(FamilyManagerImpl.predicateFromFilter(filter.getFamily(), join, cb, query));
		}
		
		return p;
	}
	
}
