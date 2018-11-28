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
public class Trajet {
	@JsonView(Views.Public.class)
	@GraphId
	private Long id;
	@JsonView(Views.Public.class)
	private int Duree;
	@JsonView(Views.Public.class)
	@Fetch
	@RelatedTo(type="DEPART", direction=Direction.BOTH)
	private Station Depart;
	@JsonView(Views.Public.class)
	@Fetch
	@RelatedTo(type="ARRIVEE", direction=Direction.BOTH)
	private Station Arrivee;
	
	@JsonView(Views.Public.class)
	@Fetch
	@RelatedTo(type="DEBUT", direction=Direction.BOTH)
	private Set<Point> point;
	

	
	public Trajet()
	{
		setPoint(new HashSet<Point>());
	}
	@JsonView(Views.PublicSet.class)
	public int getDuree() {
		return Duree;
	}
	@JsonView(Views.PublicSet.class)
	public void setDuree(int duree) {
		Duree = duree;
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
	public Station getDepart() {
		return Depart;
	}
	@JsonView(Views.PublicSet.class)
	public void setDepart(Station depart) {
		Depart = depart;
	}
	@JsonView(Views.PublicSet.class)
	public Station getArrivee() {
		return Arrivee;
	}
	@JsonView(Views.PublicSet.class)
	public void setArrivee(Station arrivee) {
		Arrivee = arrivee;
	}
	public Set<Point> getPoint() {
		return point;
	}
	public void setPoint(Set<Point> point) {
		this.point = point;
	}
	
	
}
