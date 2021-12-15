package com.familytree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="marry")
@Data
@NoArgsConstructor
public class Marry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marry_id_seq")
    @SequenceGenerator(name = "marry_id_seq",allocationSize = 1)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "husband_id", nullable = false)
	private Person husband;
	@Column(name="husband_flag",nullable = false)
	private int husbandFlag;
	
	@ManyToOne
	@JoinColumn(name = "wife_id", nullable = false)
	private Person wife;
	@Column(name= "marry_flag", nullable = false)
	private int wifeFlag;
	
	@Column(name ="marry_date", nullable = false)
	private int marryDate;
	@Column(name="divorce_date", nullable = false)
	private int divorceDate;
	@Column(nullable = false)
	private boolean divorced;
	private String remark;
	
	public Marry(long pk) {
		this.id =pk;
	}
	
	public long getHusbandId() {
		return husband.getId();
	}
	public long getWifeId() {
		return wife.getId();
	}
	public String getHusbandName() {
		return husband.getName();
	}
	public String getWifeName() {
		return wife.getName();
	}
}
