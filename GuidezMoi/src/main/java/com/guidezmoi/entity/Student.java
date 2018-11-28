package com.guidezmoi.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
@NodeEntity
public class Student { 
	@GraphId
	Long id;
	public String name;
	@Fetch
	@RelatedTo(type="SENIOR", direction=Direction.BOTH)
	public Set<Student> seniors;
	
	public Student(){}
	public Student(String name){
		this.name=name;
	}
	public void mySeniors(Student student){
		if(seniors == null) {
			seniors = new HashSet<Student>();
		}
		seniors.add(student);
	}
}
