package com.guidezmoi.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.guidezmoi.entity.Point;

public interface PointRepository extends CrudRepository<Point, String> {
	   @Query("Match (elem:Point) Where id(elem) = {0} return elem")	
	   Point getById(long id);
	   @Query("Match (elem:Point) return elem")	
	   List<Point> getAll();
	   @Query("MATCH (P: Point) WHERE NOT ()-->(P) Delete P")
	   void deleteAllSinglePoints();
}

