package com.manager.web.accounts.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@MappedSuperclass
public abstract class SuperEntity  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="create_at",nullable = false)
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	protected LocalDateTime createAt;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name="update_at", nullable = false)
	protected LocalDateTime updateAt;
	@PreUpdate
	public void preUpdate() {
		updateAt = LocalDateTime.now();
	}
	
	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
		updateAt = createAt;
	}
}
