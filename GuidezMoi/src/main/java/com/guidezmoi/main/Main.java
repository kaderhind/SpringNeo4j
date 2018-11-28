package com.guidezmoi.main;

import org.neo4j.graphdb.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;

import com.guidezmoi.config.AppConfig;
import com.guidezmoi.entity.Student;
import com.guidezmoi.repository.StudentRepository;

public class Main {
	public static void toto(String[] args) {
		   AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	       ctx.register(AppConfig.class);
	       ctx.refresh();
	       GraphDatabase graphDatabase = (GraphDatabase)ctx.getBean("graphDatabase");
	       StudentRepository studentRepository = ctx.getBean(StudentRepository.class);
	       Transaction tx = graphDatabase.beginTx();
	       Student ram = new Student("Ram");
	       Student shyam = new Student("Shyam");
	       Student mohan = new Student("Mohan");
	       Student krishn = new Student("Krishn");
	       try {
	    	   //Delete if exists already
	    	   studentRepository.deleteAll();
	    	   //Save student
	    	   studentRepository.save(ram);
	    	   studentRepository.save(shyam);
	    	   studentRepository.save(mohan);
	    	   //Build Relation
	    	   krishn.mySeniors(mohan);
	    	   krishn.mySeniors(ram);
	    	   krishn.mySeniors(shyam);
	    	   studentRepository.save(krishn);
	    	   //Get Student By Name
	    	   Student s = studentRepository.getStudentByName(ram.name);
	    	   System.out.println(s.name);
	    	   //Fetch all seniors of student
	    	   Iterable<Student> seniors = studentRepository.getSeniorsByStudentName(krishn.name);
	    	   System.out.println("----Seniors----");
	    	   for (Student student : seniors) {
				System.out.println(student.name);
			   }
	    	   tx.success();
	        } finally {
	           tx.close();
	        }
	   }
}
