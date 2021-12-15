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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.familytree.entity.Family;
import com.familytree.entity.Person;
import com.familytree.filter.FamilyFilter;
import com.familytree.jpa.FamilyManager;
@Repository
public class FamilyManagerImpl implements FamilyManager {
	@PersistenceContext EntityManager em;
	
	@Override
	@Transactional
	public void saveOrUpdate(Family entity) {
		Family f=em.merge(entity);
		entity.setId(f.getId());
	}

	@Override
	@Transactional
	public int delete(Long pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaUpdate<Person> updateQuery = cb.createCriteriaUpdate(Person.class);
		//father
		Root<?> root = updateQuery.from(Person.class);
		updateQuery.set(root.<Family>get("family"), cb.nullLiteral(Family.class));
		updateQuery.where(cb.equal(root.get("family").get("id"), pk));
		em.createQuery(updateQuery).executeUpdate();
		updateQuery = cb.createCriteriaUpdate(Person.class);
		
		CriteriaDelete<Family> query = cb.createCriteriaDelete(Family.class);
		root =  query.from(Family.class);
		query.where(cb.equal(root.get("id"), pk));
		return em.createQuery(query).executeUpdate();

	}

	@Override
	@Transactional
	public Family getOne(Long pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Family> query = cb.createQuery(Family.class);
		Root<Family> root =  query.from(Family.class);
		query.where(cb.equal(root.get("id"), pk));
		return em.createQuery(query).getSingleResult();
	}

	@Override
	@Transactional
	public List<Family> findByFilter(FamilyFilter ...filters) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Family> query = cb.createQuery(Family.class);
		Root<Family> root =  query.from(Family.class);
		Predicate p = cb.disjunction();
		for(int i=0;i<filters.length;i++) {
			p.getExpressions().add(predicateFromFilter(filters[i], root, cb, query));
		}
		query.where(p);
		return em.createQuery(query).getResultList();
	}

	@Override
	@Transactional
	public List<Family> findByFilterOnePage(FamilyFilter filter, int pageNo, int pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Family> query = cb.createQuery(Family.class);
		Root<Family> root =  query.from(Family.class);
		query.where(predicateFromFilter(filter, root, cb, query)); 
		return em.createQuery(query).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).getResultList();
	}

	@Override
	@Transactional
	public long totalByFilter(FamilyFilter filter) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Family> root =  query.from(Family.class);
		query.select(cb.count(root));
		query.where(predicateFromFilter(filter, root, cb, query));
		return em.createQuery(query).getSingleResult();
	}
	public static Predicate predicateFromFilter(FamilyFilter filter, From<?,Family> root, CriteriaBuilder cb, AbstractQuery<?> query) {
		Predicate p =cb.conjunction();
		if(filter.getId() !=null)
			p.getExpressions().add(cb.equal(root.get("id"), filter.getId()));
		if(filter.getName()!=null) {
			String name = '%' + filter.getName().toUpperCase().replace(' ', '%') + '%';
			p.getExpressions().add(cb.like(cb.upper(root.get("name")), name));
		}
		if(filter.getPlace()!=null) {
			String place = '%' + filter.getPlace().toUpperCase().replace(' ', '%') + '%';
			p.getExpressions().add(cb.like(cb.upper(root.get("place")), place));
		}
		if(filter.getMember()!=null) {
			 Subquery<Person> sub= query.subquery(Person.class);
			 Root<Person> subRoot= sub.from(Person.class);
			 sub.select(subRoot.get("family"));
			 sub.where(PersonManagerImpl.predicateFromFilter(filter.getMember(), subRoot, cb, sub));
			 p.getExpressions().add(root.in(sub));
		}
		return p;
		
	}


}
