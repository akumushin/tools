package com.familytree.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.familytree.entity.Person;
import com.familytree.entity.Relative;
import com.familytree.entity.RelativePk;
import com.familytree.filter.RelativeFilter;
import com.familytree.jpa.RelativeManager;

@Repository
public class ReativeManagerImpl implements RelativeManager {
	@PersistenceContext EntityManager em;
	@Override
	public void saveOrUpdate(Relative entity) {
		em.merge(entity);
	}

	@Override
	public int delete(RelativePk pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Relative> query = cb.createCriteriaDelete(Relative.class);
		Root<Relative> root =  query.from(Relative.class);
		Predicate p =cb.conjunction();
		p.getExpressions().add(cb.equal(root.get("me").get("id"), pk.getMe().getId()));
		p.getExpressions().add(cb.equal(root.get("you").get("id"), pk.getYou().getId()));
		query.where(p);
		return em.createQuery(query).executeUpdate();
	}

	@Override
	public Relative getOne(RelativePk pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relative> query = cb.createQuery(Relative.class);
		Root<Relative> root =  query.from(Relative.class);
		Predicate p =cb.conjunction();
		p.getExpressions().add(cb.equal(root.get("me").get("id"), pk.getMe().getId()));
		p.getExpressions().add(cb.equal(root.get("you").get("id"), pk.getYou().getId()));
		root.fetch("first");
		root.fetch("second");
		query.where(p);
		return em.createQuery(query).getSingleResult();
	}

	@Override
	public List<Relative> findByFilter(RelativeFilter ...filters) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relative> query = cb.createQuery(Relative.class);
		Root<Relative> root =  query.from(Relative.class);
		root.fetch("me");
		root.fetch("you");
		Predicate p = cb.disjunction();
		for(int i=0;i<filters.length;i++) {
			p.getExpressions().add(predicateFromFilter(filters[i], root, cb, query));
		}
		query.where(p);
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Relative> findByFilterOnePage(RelativeFilter filter, int pageNo, int pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Relative> query = cb.createQuery(Relative.class);
		Root<Relative> root =  query.from(Relative.class);
		root.fetch("me");
		root.fetch("you");
		query.where(predicateFromFilter(filter, root, cb, query));
		return em.createQuery(query).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).getResultList();
	}

	@Override
	public long totalByFilter(RelativeFilter filter) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Relative> root =  query.from(Relative.class);
		query.select(cb.count(root));
		query.where(predicateFromFilter(filter, root, cb, query));
		return em.createQuery(query).getSingleResult();
	}

	public static Predicate predicateFromFilter(RelativeFilter filter, From<?,Relative> root, CriteriaBuilder cb, AbstractQuery<?> query) {
		Predicate p=cb.conjunction();
		if(filter.getMe()!=null) {
			Join<Relative,Person> join = root.join("me");
			p.getExpressions().add(PersonManagerImpl.predicateFromFilter(filter.getMe(), join, cb, query));
		}
		if(filter.getYou()!=null) {
			Join<Relative,Person> join = root.join("you");
			p.getExpressions().add(PersonManagerImpl.predicateFromFilter(filter.getYou(), join, cb, query));
		}
		if(filter.getRelationship() !=null) {
			p.getExpressions().add(cb.equal(root.get("relationship"), filter.getRelationship()));
		}
		return p;
	}
}
