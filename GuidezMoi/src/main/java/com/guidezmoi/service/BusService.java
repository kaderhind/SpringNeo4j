package com.guidezmoi.service;

import java.util.List;

import org.neo4j.graphdb.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

import com.guidezmoi.config.AppConfig;
import com.guidezmoi.entity.Bus;
import com.guidezmoi.repository.BusRepository;
import com.guidezmoi.repository.BusRepository.BusData;
import com.guidezmoi.repository.BusRepository.BusData2;
import com.guidezmoi.repository.TramwayRepository.TramwayData;
import com.guidezmoi.repository.TramwayRepository.TramwayData2;

@Service
public class BusService {

	private BusRepository repository;
	private AnnotationConfigApplicationContext ctx;
	private GraphDatabase graphDatabase;
	
	public BusService()
	{
		ctx = new AnnotationConfigApplicationContext();
	    ctx.register(AppConfig.class);
	    ctx.refresh();
		repository =ctx.getBean(BusRepository.class);
		graphDatabase=(GraphDatabase)ctx.getBean("graphDatabase");
	}
	
	public Bus AddBus(Bus bus)
	{
		 Transaction tx = graphDatabase.beginTx();
		 
		 Bus temp=null;
		 try{
			 temp=repository.save(bus);
			 tx.success();
		 }finally{
			 tx.close();
		 }
		
		 return temp;
	}
	
	public Bus getBusByID(long id)
	{
		return repository.getById(id);
	}
	
	public List<Bus> getAllBus()
	{
		return repository.getAll();
	}
	
	public void DeleteBus(long id)
	{
		repository.DeleteBus(id);
	}
	
	
	public GraphDatabase getGraphDatabase()
	{
		return (GraphDatabase)ctx.getBean("graphDatabase");
	}
	public void close()
	{
		ctx.close();
	}
	
	public List<BusData> getIdNumDeLigneAllBus()
	{
		return repository.getIdNumDeLigneAll();
	}
	public List<BusData> getIdNumDeLigneBusByStationDepartStationArrivee(long id_depart,long id_arrivee)
	{
		return repository.getIdNumDeLigneByStationDepartStationArrivee(id_depart, id_arrivee);
	}
	public BusData2 getIdNumDeLigneBus(long id)
	{
		return repository.getIdNumDeLigne(id);
	}
	
}
