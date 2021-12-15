package com.manager.common.type;

import java.io.Serializable;

import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Table;

import com.manager.web.accounts.entity.SuperEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Address extends SuperEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Column(name="zip_code", length = 10)
	private String zipCode;
	@Column(name="local1", length = 50)
	private String local1;// tỉnh, thành phố
	@Column(name="local2", length = 50)
	private String local2;// quận huyện, thành phố trực thuộc tỉnh
	@Column(name="local3", length = 50)
	private String local3;// thị trấn, xã, phường
	@Column(name="local4", length = 100)
	private String local4; // địa chỉ cụ thể
	@Column(name="name", length = 100)
	private String name;// tên người nhận
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(name).append(", ");
		if(local4!=null && local4.length()>0)	
			sb.append(local4).append(", ");
		if(local3!=null && local3.length()>0)
			sb.append(local3).append(", ");
		if(local2!=null && local2.length()>0)
			sb.append(local2).append(", ");
		if(local1!=null && local1.length()>0)
			sb.append(local1).append(", ");
		if(zipCode!=null && zipCode.length()>0)
			sb.append(zipCode);
		return sb.toString();
	}
	public String toStringJapan() {
		StringBuilder sb= new StringBuilder();
		if(zipCode!=null && zipCode.length()>0)
			sb.append(zipCode).append(", ");
		if(local1!=null && local1.length()>0)
			sb.append(local1);
		if(local2!=null && local2.length()>0)
			sb.append(local2);
		if(local3!=null && local3.length()>0)
			sb.append(local3);
		if(local4!=null && local4.length()>0)	
			sb.append(local4).append(",");
		sb.append(name);
		return sb.toString();
	}
}
