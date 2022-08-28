package com.example.demo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "telefonos")
public class Phone {
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 */

	@Id
	@Column(unique = true)
	private String number;

	private String cityCode;

	private String countryCode;

}// Class Closure