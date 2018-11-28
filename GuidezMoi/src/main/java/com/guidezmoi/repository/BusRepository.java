package com.guidezmoi.repository;


import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.annotation.JsonView;
import com.guidezmoi.entity.Bus;
import com.guidezmoi.entity.Views;
import com.guidezmoi.repository.TramwayRepository.TramwayData;
import com.guidezmoi.repository.TramwayRepository.TramwayData2;


public interface BusRepository extends CrudRepository<Bus,String>{
	
	 @Query("Match (elem:Bus) Where id(elem) = {0} return elem")	
	   Bus getById(long id);
	 @Query("Match (elem:Bus) return elem")	
	   List<Bus> getAll();
	 @Query("Match(B: Bus) where ID(B)= {0} optional match(B)-[r]-() delete r,B")
	   void DeleteBus(long id); 
	 @Query("Match (elem:Bus) return id(elem) AS Id ,elem.NumDeLigne AS NumDeLigne")	
	   List<BusData> getIdNumDeLigneAll();
	   
	   @Query("match(t:Bus)-[PARCOURT]->(tr:Trajet)-[DEPART]->(s:Station) where id(s)={0}  OPTIONAL match(t)-[PARCOURT]->(tr)-[ARRIVEE]->(ss:Station)  where id(ss)={1}  return DISTINCT id(t) AS Id,t.NumDeLigne AS NumDeLigne;")	
	   List<BusData> getIdNumDeLigneByStationDepartStationArrivee(long id_depart,long id_arrivee);
	   @QueryResult
	    public class BusData {
		   @ResultColumn("Id")
		   private  Long Id;
		   @ResultColumn("NumDeLigne")
		   private String NumDeLigne;

		   public BusData() {
			 
		}
		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
		}
		public String getNumDeLigne() {
			return NumDeLigne;
		}
		public void setNumDeLigne(String numDeLigne) {
			NumDeLigne = numDeLigne;
		}
		
	    }
	   
	   @Query("Match (elem:Bus) Where id(elem) = {0} return id(elem) AS Id ,elem.NumDeLigne AS NumDeLigne,elem.prix AS Prix")	
	   BusData2 getIdNumDeLigne(long id);
	   
	   @QueryResult
	    public class BusData2 {
		   @JsonView(Views.Public.class)
		   @ResultColumn("Id")
		   private  Long Id;
		   @JsonView(Views.Public.class)
		   @ResultColumn("NumDeLigne")
		   private String NumDeLigne;

		   @JsonView(Views.Public.class)
		   @ResultColumn("Prix")
		   private double Prix;
		   public BusData2() {
			// TODO Auto-generated constructor stub
		}
		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
		}

		public String getNumDeLigne() {
			return NumDeLigne;
		}
		public void setNumDeLigne(String numDeLigne) {
			NumDeLigne = numDeLigne;
		}
		
		public double getPrix() {
			return Prix;
		}
		public void setPrix(double prix) {
			Prix = prix;
		}
	    }
	 
}
