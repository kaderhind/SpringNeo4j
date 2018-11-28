package com.guidezmoi.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.guidezmoi.entity.Station;

public interface StationRepository extends CrudRepository<Station, String>{
	 @Query("Match (elem:Station) Where id(elem) = {0} return elem")	
	   Station getById(long id);
	   @Query("Match (elem:Station) return elem")	
	   List<Station> getAll();
	   @Query("Match (elem:Station) Where id(elem) = {0}  SET elem.Nom={1},  elem.Adresse={2}  return elem")	
	   Station UpdateStation(Long id,String nom,String adresse);
	   @Query("MATCH (T:Trajet),(S: Station) WHERE NOT (T)-->(S) and ID(S)={0} DELETE S")
	   void deleteAloneStation(long id);
	   @Query("MATCH (S:Station) WHERE NOT ()-->(S) DELETE S")
	   void deleteAllSingleStation();
}
