package com.giffing.wicket.spring.boot.example.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.giffing.wicket.spring.boot.example.repository.Domain;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer implements Domain<Long>, Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String firstname;
	
	private String lastname;
	
	private boolean active;

}
