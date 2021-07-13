package com.danielmehlber.web.jax.rs;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="people")
public class Person {

	@Id @Column(name="name", nullable=false)
	private String name;

	@Column(name="age", nullable=false)
	private int age;

	@Column(name="phrase", nullable=false)
	private String phrase;


	public Person(String name, int age, String phrase) {
		super();
		this.name = name;
		this.age = age;
		this.phrase = phrase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Person other = (Person) obj;
		return Objects.equals(name, other.name);
	}



}
