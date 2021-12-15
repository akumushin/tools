package com.familytree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.familytree.common.enums.Relationship;

import lombok.Data;

@Data
@Table(name ="relationship")
@Entity
@IdClass(RelativePk.class)
public class Relative {
	//自分
	@Id
	@ManyToOne
	@JoinColumn(name = "my_id")
	private Person me;
	//関係者
	@Id
	@ManyToOne
	@JoinColumn(name = "your_id")
	private Person you;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Relationship relationship ;
	private String remark;
}
