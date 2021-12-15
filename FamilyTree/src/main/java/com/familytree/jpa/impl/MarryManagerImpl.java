package com.familytree.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.familytree.entity.Marry;
import com.familytree.entity.Person;
import com.familytree.filter.MarryFilter;
import com.familytree.jpa.MarryManager;
@Repository
public class MarryManagerImpl implements MarryManager {
	@PersistenceContext EntityManager em;
	
	@Override
	@Transactional
	public void saveOrUpdate(Marry entity) {
		Marry m = em.merge(entity);
		entity.setId(m.getId());
	}

	@Override
	@Transactional
	public int delete(Long pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Marry> query = cb.createCriteriaDelete(Marry.class);
		Root<Marry> root =  query.from(Marry.class);
		query.where(cb.equal(root.get("id"), pk));
		return em.createQuery(query).executeUpdate();
		
	}

	@Override
	@Transactional
	public Marry getOne(Long pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Marry> query = cb.createQuery(Marry.class);
		Root<Marry> root =  query.from(Marry.class);
		query.where(cb.equal(root.get("id"), pk));
		root.fetch("husband");
		root.fetch("wife");
		return em.createQuery(query).getSingleResult();
	}

	@Override
	@Transactional
	public List<Marry> findByFilter(MarryFilter ...filters) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Marry> query = cb.createQuery(Marry.class);
		Root<Marry> root =  query.from(Marry.class);
		root.fetch("husband");
		root.fetch("wife");
		Predicate p = cb.disjunction();
		for(int i=0;i<filters.length;i++) {
			p.getExpressions().add(predicateFromFilter(filters[i], root, cb, query));
		}
		query.where(p);
		return em.createQuery(query).getResultList();
	}

	@Override
	@Transactional
	public List<Marry> findByFilterOnePage(MarryFilter filter, int pageNo, int pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Marry> query = cb.createQuery(Marry.class);
		Root<Marry> root =  query.from(Marry.class);
		root.fetch("husband");
		root.fetch("wife");
		query.where(predicateFromFilter(filter, root, cb, query));
		return em.createQuery(query).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).getResultList();
	}

	@Override
	@Transactional
	public long totalByFilter(MarryFilter filter) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Marry> root =  query.from(Marry.class);
		query.select(cb.count(root));
		query.where(predicateFromFilter(filter, root, cb, query));
		return em.createQuery(query).getSingleResult();

	}

	public static Predicate predicateFromFilter(MarryFilter filter, Root<Marry> root, CriteriaBuilder cb, AbstractQuery<?> query) {
		Predicate p =cb.conjunction();
		if(filter.getHusband()!=null) {
			Join<Marry, Person> pRoot= root.join("husband");
			p.getExpressions().add(PersonManagerImpl.predicateFromFilter(filter.getHusband(), pRoot, cb, query));
		}
		if(filter.getWife()!=null) {
			Join<Marry, Person> pRoot= root.join("wife");
			p.getExpressions().add(PersonManagerImpl.predicateFromFilter(filter.getWife(), pRoot, cb, query));
		}
		if(filter.getHusbandFlag()!=null) 
			p.getExpressions().add(cb.equal(root.get("husbandFlag"), filter.getHusbandFlag()));
		if(filter.getWifeFlag()!=null)
			p.getExpressions().add(cb.equal(root.get("wifeFlag"), filter.getWifeFlag()));
		if(filter.getMarryDate()!=null)
			p.getExpressions().add(cb.equal(root.get("marryDate"), filter.getMarryDate()));
		if(filter.getDivorced()!=null)
			p.getExpressions().add(cb.equal(root.get("divorced"), filter.getDivorced()));
		if(filter.getDivorceDate()!=null)
			p.getExpressions().add(cb.equal(root.get("divorceDate"), filter.getDivorceDate()));
		return p;
	}

}
