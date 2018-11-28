package com.guidezmoi.config;  

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

import com.guidezmoi.repository.StudentRepository;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.guidezmoi")
public class AppConfig extends Neo4jConfiguration  {
	public AppConfig() {
        setBasePackage("com.guidezmoi");
    }
	@Autowired
    GraphDatabase graphDatabase;
	@Autowired
    StudentRepository studentRepository;  
	@Bean
    GraphDatabaseService graphDatabaseService() {
        return new SpringRestGraphDatabase("http://localhost:7474/db/data");
    }
}  
  