package com.guidezmoi.service;

import java.util.List;

import org.neo4j.graphdb.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

import com.guidezmoi.config.AppConfig;
import com.guidezmoi.entity.Station;
import com.guidezmoi.repository.StationRepository;

@Service
public class StationService {
	private StationRepository repository;
	private AnnotationConfigApplicationContext ctx;
	private GraphDatabase graphDatabase;
	public StationService()
	{
		ctx = new AnnotationConfigApplicationContext();
	    ctx.register(AppConfig.class);
	    ctx.refresh();
	    repository =ctx.getBean(StationRepository.class);
	    graphDatabase=(GraphDatabase)ctx.getBean("graphDatabase");
	}
	
	public Station AjouteStation(Station station)
	{
		 Transaction tx = graphDatabase.beginTx();
		 Station temp=null;
		 try {
	    	   
			  temp=repository.save(station);
	    	   tx.success();
	        } finally {
	           tx.close();
	        }
		return temp;
		
	}
	
	public  Station getStationTramwayById(long id)
	{
		return repository.getById(id);
	}
	public List<Station> getAllStation()
	{
		return repository.getAll();
	}
	public void close()
	{
		ctx.close();
	}
	
	public GraphDatabase getGraphDatabase()
	{
		return (GraphDatabase)ctx.getBean("graphDatabase");
	}
	public void DeleteAloneStation(long id)
	{
		repository.deleteAloneStation(id);
	}
	public void DeleteAllSingleStation()
	{
		repository.deleteAllSingleStation();
	}
	public Station UpStation(Station st)
	{		
		return repository.UpdateStation(st.getId(),st.getNom(),st.getAdresse());		
	}
	
}
