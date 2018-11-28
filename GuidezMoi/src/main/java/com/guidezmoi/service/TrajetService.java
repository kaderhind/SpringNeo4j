package com.guidezmoi.service;

import java.util.List;

import org.neo4j.graphdb.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

import com.guidezmoi.config.AppConfig;
import com.guidezmoi.entity.Trajet;
import com.guidezmoi.repository.TrajetRepository;

@Service
public class TrajetService {

		private TrajetRepository repository;
		private AnnotationConfigApplicationContext ctx;
		private GraphDatabase graphDatabase;
		public TrajetService()
		{
			ctx = new AnnotationConfigApplicationContext();
		    ctx.register(AppConfig.class);
		    ctx.refresh();
		    repository =ctx.getBean(TrajetRepository.class);
		    graphDatabase=(GraphDatabase)ctx.getBean("graphDatabase");
		}
		
		public Trajet AjouteTrajet(Trajet trajet)
		{
			Transaction tx = graphDatabase.beginTx();
			Trajet temp=null;
			 try {
		    	   
				  temp=repository.save(trajet);
		    	   tx.success();
		        } finally {
		           tx.close();
		        }
			return temp;
		}
		
		public Trajet  getTrajetById(long id)
		{
			return repository.getById(id);
		}
		public List<Trajet> getAllTrajetTramway()
		{
			return repository.getAllTramway();
		}
		public List<Trajet> getAllTrajetTramway(long id)
		{
			return repository.getAllTramway(id);
		}
		public List<Trajet> getAllTrajetBus()
		{
			return repository.getAllBus();
		}
		public List<Trajet> getAllTrajetBus(long id)
		{
			return repository.getAllBus(id);
		}
		public void close()
		{
			ctx.close();
		}
		public void CreateRelationShip(long id_trajet,long id_station)
		{
			repository.createRelationShip(id_trajet, id_station);
		}
		
		public void DeleteTrajet(long id)
		{
			repository.DeleteTrajet(id);
		}
		public void DeleteRelationShip(long id_trajet,long id_station)
		{
			repository.DeleteReltionShip(id_trajet, id_station);
		}
		public Trajet UpTrajet( Trajet tr)
		{
			return repository.setTrajet(tr.getId(),tr.getDuree());
		}

		public List<Trajet> getTrajetByidTramway(long id_s,long id_t)
		{
			return repository.GetTrajectTramway(id_s, id_t);
		}
		public List<Trajet> getTrajetByidBus(long id_s,long id_t)
		{
			return repository.GetTrajectBus(id_s, id_t);
		}
}
