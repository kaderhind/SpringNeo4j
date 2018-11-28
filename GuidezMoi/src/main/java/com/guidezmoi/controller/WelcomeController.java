package com.guidezmoi.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guidezmoi.repository.PointRepository;
import com.guidezmoi.repository.StationRepository;
import com.guidezmoi.repository.StudentRepository;
import com.guidezmoi.repository.TrajetRepository;
import com.guidezmoi.repository.TramwayRepository;
import com.guidezmoi.config.AppConfig;
import com.guidezmoi.entity.Point;
 import com.guidezmoi.entity.Student;
import com.guidezmoi.entity.Trajet;
import com.guidezmoi.entity.Tramway;
import com.guidezmoi.service.HelloWorldService;

@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}



	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete() {
		
		
		
		   AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	       ctx.register(AppConfig.class);
	       ctx.refresh();
	       GraphDatabase graphDatabase = (GraphDatabase)ctx.getBean("graphDatabase");
	       
	       StationRepository stationTramwayRepository = ctx.getBean(StationRepository.class);
	       TrajetRepository trajetRepository = ctx.getBean(TrajetRepository.class);
	       TramwayRepository tramwayRepository =ctx.getBean(TramwayRepository.class);
	      PointRepository pointRepository =ctx.getBean(PointRepository.class);
	       Transaction tx = graphDatabase.beginTx();
	      
	       try {
	    	  
	    	   stationTramwayRepository.deleteAll();	
	    	   trajetRepository.deleteAll();
	    	   tramwayRepository.deleteAll();
	    	   pointRepository.deleteAll();
	    	   tx.success();
	        } finally {
	           tx.close();
	        }
	       
		return "index";
	}
	
	@RequestMapping(value = "/put", method = RequestMethod.GET)
	public String put() {
		

		return "index";
	}
	
	
	@RequestMapping(value = "/put2", method = RequestMethod.GET)
	public  String put2(HttpServletRequest req, HttpServletResponse res) {
				
		 
	       
		return "index";
	}
	
	

}