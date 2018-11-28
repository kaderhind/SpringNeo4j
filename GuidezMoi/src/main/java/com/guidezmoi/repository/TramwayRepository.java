package com.guidezmoi.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.annotation.JsonView;
import com.guidezmoi.entity.Tramway;
import com.guidezmoi.entity.Views;

public interface TramwayRepository  extends CrudRepository<Tramway, String> {
	   @Query("Match (elem:Tramway) Where id(elem) = {0} return elem")	
	   Tramway getById(long id);
	   @Query("Match (elem:Tramway) return elem")	
	   List<Tramway> getAll();
	   @Query("Match (elem:Tramway) return id(elem) AS Id ,elem.Depart AS Depart,elem.Arrivee AS Arrivee")	
	   List<TramwayData> getIdDepartArriveeAll();
	   
	   @Query("match(t:Tramway)-[PARCOURT]->(tr:Trajet)-[DEPART]->(s:Station) where id(s)={0}  OPTIONAL match(t)-[PARCOURT]->(tr)-[ARRIVEE]->(ss:Station)  where id(ss)={1}  return DISTINCT id(t) AS Id,t.Depart AS Depart, t.Arrivee AS Arrivee;")	
	   List<TramwayData> getIdDepartArriveeByStationDepartStationArrivee(long id_depart,long id_arrivee);
	   
	   
	   @Query("Match (elem:Tramway) Where id(elem) = {0}  SET elem.Arrivee={1},  elem.Depart={2}, elem.prix={3} return elem")	
	   Tramway modiftramway(long id, String arrivee,String depart,double prix);
	   
	   @Query("Match(T: Tramway) where ID(T)= {0} optional match(T)-[r]-() delete r,T")
	   void DeleteTramway(long id); 
	   @Query("Match(T:Tramway)-[*]->(S:Station) where ID(S)={0} return T")
	   List<Tramway> getTramwayByStation(long id_station);
	   @QueryResult
	    public class TramwayData {
		   @ResultColumn("Id")
		   private  Long Id;
		   @ResultColumn("Depart")
		   private String Depart;
		   @ResultColumn("Arrivee")
		   private String Arrivee;
		
		   public TramwayData() {
			 
		}
		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
		}
		public String getDepart() {
			return Depart;
		}
		public void setDepart(String depart) {
			Depart = depart;
		}
		public String getArrivee() {
			return Arrivee;
		}
		public void setArrivee(String arrivee) {
			Arrivee = arrivee;
		}
	    }
	   
	   @Query("Match (elem:Tramway) Where id(elem) = {0} return id(elem) AS Id ,elem.Depart AS Depart,elem.Arrivee AS Arrivee,elem.prix AS Prix")	
	   TramwayData2 getIdDepartArrivee(long id);
	   
	   @QueryResult
	    public class TramwayData2 {
		   @JsonView(Views.Public.class)
		   @ResultColumn("Id")
		   private  Long Id;
		   @JsonView(Views.Public.class)
		   @ResultColumn("Depart")
		   private String Depart;
		   @JsonView(Views.Public.class)
		   @ResultColumn("Arrivee")
		   private String Arrivee;
		   @JsonView(Views.Public.class)
		   @ResultColumn("Prix")
		   private double Prix;
		   public TramwayData2() {
			// TODO Auto-generated constructor stub
		}
		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
		}
		public String getDepart() {
			return Depart;
		}
		public void setDepart(String depart) {
			Depart = depart;
		}
		public String getArrivee() {
			return Arrivee;
		}
		public void setArrivee(String arrivee) {
			Arrivee = arrivee;
		}
		public double getPrix() {
			return Prix;
		}
		public void setPrix(double prix) {
			Prix = prix;
		}
	    }
}
