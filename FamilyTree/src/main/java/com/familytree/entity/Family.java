package com.familytree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@NoArgsConstructor
@Table(name = "family")
@Entity
public class Family {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "family_id_seq")
    @SequenceGenerator(name = "family_id_seq",allocationSize = 1,initialValue = 0)
	@Id
	private long id;
	@Column(nullable = false)
	private String place;
	
	@Column(nullable = false)
	private String name;
	@Override
	public boolean equals(Object obj) {
		if (obj == null ||! (obj instanceof Family) )
			return false;
		Family p = (Family)obj;
		return this.id == p.id;
	}
	
	
	public Family(long id) {
		this.id =id;
	}
}
