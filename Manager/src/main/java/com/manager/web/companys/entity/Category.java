package com.manager.web.companys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.manager.web.accounts.entity.SuperEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name="category")
@ToString(exclude = "parentCategory")
@EqualsAndHashCode(callSuper = false)
public class Category extends SuperEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	@Column(length = 50,nullable = false, unique = true)
	private String name;
	@Column(length = 50, nullable = false)
	private String displayName;
	@Column
	private int level;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parrent_id", nullable = true)
	private Category parentCategory;
	@OneToMany(mappedBy = "parentCategory")
	private List<Category> childrenCategories;
}
