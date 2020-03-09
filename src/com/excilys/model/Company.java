package com.excilys.model;


public class Company {
 public int id;
 public char name;

 public Company(int id, char name) {
	super();
	this.id = id;
	this.name = name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public char getName() {
	return name;
}

public void setName(char name) {
	this.name = name;
}

 
}
