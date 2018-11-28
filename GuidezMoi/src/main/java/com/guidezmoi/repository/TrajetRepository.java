package com.guidezmoi.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.guidezmoi.entity.Trajet;


public interface TrajetRepository extends CrudRepository<Trajet, String> {
	   @Query("Match (elem:Trajet) Where id(elem) = {0} return elem")	
	   Trajet getById(long id);
	   @Query("match(T:Tramway)-[PARCOURT]->(elem:Trajet) return DISTINCT  elem")	
	   List<Trajet> getAllTramway();
	   @Query("match(T:Tramway)-[PARCOURT]->(trj:Trajet) where id(T)={0} return DISTINCT trj;")	
	   List<Trajet> getAllTramway(long id);
	   @Query("Match (elem:Trajet) Where id(elem)={0} SET elem.Duree={1} return elem")
	   Trajet setTrajet(long id,int duree);
	   @Query("MATCH(T:Tramway)-[r]->(TR:Trajet)-[rr]->(S: Station) where ID(S)= {0} and ID(T)= {1} return TR")
	   List<Trajet> GetTrajectTramway(long id_station,long id_tram);
	   
	   @Query("match(B:Bus)-[PARCOURT]->(elem:Trajet) return DISTINCT  elem")	
	   List<Trajet> getAllBus();
	   @Query("match(B:Bus)-[PARCOURT]->(trj:Trajet) where id(B)={0} return DISTINCT trj;")	
	   List<Trajet> getAllBus(long id);
	   @Query("MATCH(B:Bus)-[r]->(TR:Trajet)-[rr]->(S: Station) where ID(S)= {0} and ID(B)= {1} return TR")
	   List<Trajet> GetTrajectBus(long id_station,long id_tram);
	   
	   @Query("Match(TR: Trajet),(S: Station) where ID(S)= {1} and ID(TR)={0} create (TR)-[r: ARRIVEE]->(S)")
	   void createRelationShip(long id_trajet,long id_station);
	   
	   @Query("Match(T: Trajet) where ID(T)={0} optional match(T)-[r]-() delete r,T")
	   void DeleteTrajet(long id);
	   
	   @Query("Match(TR:Trajet)-[rr]->(S: Station) where ID(TR)={0} and ID(S)={1} delete rr")
	   void DeleteReltionShip(long id_trajet,long id_station);
}
