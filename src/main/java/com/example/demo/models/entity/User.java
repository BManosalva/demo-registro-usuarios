package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User implements Serializable{

	@Id
	private String id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column(length = 60)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Phone> phones;

	@Column(name = "active_status")
	private Boolean isActive;
	
	@Column(name= "created")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	private Date modified;
	
	@Column(name= "last_login")
	@Temporal(TemporalType.DATE)
	private Date lastLogin;
	
}// Class Closure