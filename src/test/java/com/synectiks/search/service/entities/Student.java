package com.synectiks.search.service.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

//import org.springframework.data.elasticsearch.annotations.Document;

//import com.synectiks.commons.interfaces.IESEntity;


//@Entity
//@Table(name = "Student")
//@Document(indexName="student")
public class Student{
	private int id;
	private String name;
	private float fee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

}
