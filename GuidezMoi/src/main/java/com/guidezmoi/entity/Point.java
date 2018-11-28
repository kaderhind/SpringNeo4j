package com.guidezmoi.entity;


import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonView;
@NodeEntity
public class Point extends Coordonnee  implements Comparable<Point> {
	
	@JsonView(Views.Public.class)
	@GraphId
	private Long id;
	
	@JsonView(Views.Public.class)
	private int indic;
	
	public Point()
	{
		
	};
	

	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}




	public int getIndic() {
		return indic;
	}




	public void setIndic(int indic) {
		this.indic = indic;
	}




	@Override
	public int compareTo(Point p) {
		return new Integer(indic).compareTo(p.indic);
	}
	
}
