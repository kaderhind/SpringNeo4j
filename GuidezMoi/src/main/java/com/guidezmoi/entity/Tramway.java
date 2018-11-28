package com.guidezmoi.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonView;

@NodeEntity
public class Tramway {
	@JsonView(Views.Public.class)
	@GraphId
	private Long id;
	@JsonView(Views.Public.class)
	private String Depart;
	@JsonView(Views.Public.class)
	private String Arrivee;
	@JsonView(Views.Public.class)
	private double prix;
	@JsonView(Views.Public.class)
	@Fetch
	@RelatedTo(type="PARCOURT", direction=Direction.BOTH)
	private Set<Trajet> Itineraires;
	
	
	public Tramway()
	{
		Itineraires=new HashSet<Trajet>();
	}
	@JsonView(Views.PublicSet.class)
	public Long getId() {
		return id;
	}
	@JsonView(Views.PublicSet.class)
	public void setId(Long id) {
		this.id = id;
	}
	@JsonView(Views.PublicSet.class)
	public String getDepart() {
		return Depart;
	}
	@JsonView(Views.PublicSet.class)
	public void setDepart(String depart) {
		Depart = depart;
	}
	@JsonView(Views.PublicSet.class)
	public double getPrix() {
		return prix;
	}
	@JsonView(Views.PublicSet.class)
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getArrivee() {
		return Arrivee;
	}
	@JsonView(Views.PublicSet.class)
	public void setArrivee(String arrivee) {
		Arrivee = arrivee;
	}
	@JsonView(Views.PublicSet.class)
	public Set<Trajet> getItineraires() {
		return Itineraires;
	}
	@JsonView(Views.PublicSet.class)
	public void setItineraires(Set<Trajet> itineraires) {
		Itineraires = itineraires;
	}
}
