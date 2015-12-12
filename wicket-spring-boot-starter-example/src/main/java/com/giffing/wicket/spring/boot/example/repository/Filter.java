package com.giffing.wicket.spring.boot.example.repository;

import java.io.Serializable;

public interface Filter extends Serializable {
	
	Sort sort();
	
}
