package com.guidezmoi.entity;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonView;
@NodeEntity
 public class Station extends Coordonnee {
	@JsonView(Views.Public.class)
	@GraphId
	private Long id;
	@JsonView(Views.Public.class)
	private String Adresse;
	
	@JsonView(Views.Public.class)
	private String Nom;

	public Station()
	{
		
	}
	
	public String getNom() {
		return Nom;
	}
	
	public void setNom(String nom) {
		Nom = nom;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
}
