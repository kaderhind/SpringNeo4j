package com.guidezmoi.service;

import java.util.List;

import org.neo4j.graphdb.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

import com.guidezmoi.config.AppConfig;
import com.guidezmoi.entity.Tramway;
import com.guidezmoi.repository.TramwayRepository;
import com.guidezmoi.repository.TramwayRepository.TramwayData;
import com.guidezmoi.repository.TramwayRepository.TramwayData2;

@Service
public class TramwayService {
	
	private  TramwayRepository respository;
	private AnnotationConfigApplicationContext ctx;
	private GraphDatabase graphDatabase;
	
	public TramwayService()
	{
		ctx = new AnnotationConfigApplicationContext();
	    ctx.register(AppConfig.class);
	    ctx.refresh();
		respository =ctx.getBean(TramwayRepository.class);
		graphDatabase=(GraphDatabase)ctx.getBean("graphDatabase");
	}
	public Tramway AjouteTram(Tramway tram)
	{
		 Transaction tx = graphDatabase.beginTx();
		 Tramway temp=null;
		 try {
	    	   
			  temp=respository.save(tram);
	    	   tx.success();
	        } finally {
	           tx.close();
	        }
		return temp;
	}
	
	public Tramway getTramwayById(long id)
	{
		return respository.getById(id);
	}
	
	public  List<Tramway> getAllTramway()
	{
		return respository.getAll();
	}
	
	public List<TramwayData> getIdDepartArriveeAllTramway()
	{
		return respository.getIdDepartArriveeAll();
	}
	public void close()
	{
		ctx.close();
	}
	
	public GraphDatabase getGraphDatabase()
	{
		return (GraphDatabase)ctx.getBean("graphDatabase");
	}
	
	public List<TramwayData> getIdDepartArriveeTramwayByStationDepartStationArrivee(long id_depart,long id_arrivee)
	{
		return respository.getIdDepartArriveeByStationDepartStationArrivee(id_depart, id_arrivee);
	}
	public Tramway ModifTram(Tramway temp)
	{		
		return respository.modiftramway(temp.getId(),temp.getArrivee(),temp.getDepart(),temp.getPrix());
	    	
	}
	public void DeleteTramway(Long id)
	{
		respository.DeleteTramway(id);
	}
	public TramwayData2 getIdDepartArriveeTramway(long id)
	{
		return respository.getIdDepartArrivee(id);
	}
}
