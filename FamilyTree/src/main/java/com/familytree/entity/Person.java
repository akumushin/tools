package com.familytree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.familytree.common.enums.Sex;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@NoArgsConstructor
@Table(name="person")
@Entity
public class Person {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @SequenceGenerator(name = "person_id_seq",allocationSize = 1)
	@Id
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@Column(nullable = false)
	private boolean alive;
	@Column(nullable = false)
	/**
	 * 11110101
	 */
	private int birthday;
	@Column(nullable = false)
	private int deadday;
	@JoinColumn(name="father_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Person father;
	@JoinColumn(name="mother_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Person mother;
	
	@JoinColumn(name="family_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Family family;
	@Column(length = 255)
	private String remark;
	@Override
	public boolean equals(Object obj) {
		if (obj == null ||! (obj instanceof Person) )
			return false;
		Person p = (Person)obj;
		return this.id == p.id;
	}
	
	public Person(long id) {
		this.id =id;
	}
	@Override
	public String toString() {
		return name+"("+id+")";
	}
	public Long getFatherId() {
		return (father!=null)?father.id:null;
	}
	public Long getMotherId() {
		return (mother!=null)?mother.id:null;
	}
	public Long getFamilyId() {
		return (family!=null)?family.getId():null;
	}
}
