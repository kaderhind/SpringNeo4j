package com.guidezmoi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.guidezmoi.entity.Bus;
import com.guidezmoi.entity.Point;
import com.guidezmoi.entity.Station;
import com.guidezmoi.entity.Trajet;
import com.guidezmoi.entity.Tramway;
import com.guidezmoi.entity.Views;
import com.guidezmoi.repository.TramwayRepository.TramwayData;
import com.guidezmoi.repository.TramwayRepository.TramwayData2;
import com.guidezmoi.service.BusService;
import com.guidezmoi.service.PointService;
import com.guidezmoi.service.StationService;
import com.guidezmoi.service.TrajetService;
import com.guidezmoi.service.TramwayService;


@Controller
public class TramwayController {
	
	
	public TramwayController() {
		
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index()
	{
		return "index";
		
	}
	@RequestMapping(value = "/tramway", method = RequestMethod.GET)
	
	public ModelAndView getdata() {
		
	        TramwayService tramwayservice =new TramwayService();
	       		
			ModelAndView model = new ModelAndView("tramway");
			model.addObject("trams",( List<TramwayData>)tramwayservice.getIdDepartArriveeAllTramway());
			
			tramwayservice.close();
			
		return model;

	}
	
	@JsonView(Views.PublicSet.class)
	@RequestMapping(value="/getalltramway")
		public @ResponseBody List<Tramway>  getAllTramway() {
		TramwayService ts=new TramwayService();
		List<Tramway> ltram;
		ltram=ts.getAllTramway();
		ts.close();
		return ltram;
		}
	@RequestMapping(value = "/addTramway", method = RequestMethod.POST)
	public  @ResponseBody String addTramway(HttpServletRequest req, HttpServletResponse res)
	{
		String result="";
		TramwayService tramwayservice=new TramwayService();
		GraphDatabase graphDatabase = tramwayservice.getGraphDatabase();
		Tramway tram=new Tramway();
		
		tram.setDepart(req.getParameter("ajoute_tramway_depart"));
		tram.setArrivee(req.getParameter("ajoute_tramway_arrivee"));
		tram.setPrix(new Double(req.getParameter("ajoute_tramway_prix")));		
		 
		Tramway temp=tramwayservice.AjouteTram(tram);
	    result+="<option value=\""+temp.getId()+"\">"+temp.getDepart()+"->"+temp.getArrivee()+"</option>" ;
	    	   
		 tramwayservice.close();
		return result;
	}
	
	@RequestMapping(value = "/addTramwayData", method = RequestMethod.POST)
	public  @ResponseBody ModelAndView adddTramwayData(HttpServletRequest req, HttpServletResponse res)
	{
		   TramwayService tramwayservice=new TramwayService();
	       
	       String [] id_tramway=req.getParameter("trajet_tramway").split(",");
		   
	       Station s1,s2;
	       s1=new Station();
	       s2=new Station();
	       if((new Integer(req.getParameter("station_depart_id")))!= -1)
	    	   s1.setId(new Integer(req.getParameter("station_depart_id")).longValue());
	       
	       s1.setLatitude(new Double(req.getParameter("station_depart_latitude")));
	       s1.setLongitude(new Double(req.getParameter("station_depart_longitude")));
	       s1.setAdresse(req.getParameter("station_depart_adresse_station"));
	       s1.setNom(req.getParameter("station_depart_nom_station"));
	       
	       if((new Integer(req.getParameter("station_arrivee_id")))!= -1)
	    	   s2.setId(new Integer(req.getParameter("station_arrivee_id")).longValue());
	       s2.setLatitude(new Double(req.getParameter("station_arrivee_latitude")));
	       s2.setLongitude(new Double(req.getParameter("station_arrivee_longitude")));
	       s2.setAdresse(req.getParameter("station_arrivee_adresse_station"));
	       s2.setNom(req.getParameter("station_arrivee_nom_station"));
	       
		   for(int j=0 ;j<id_tramway.length;j++)
		   {
			   Tramway tram=tramwayservice.getTramwayById(new Integer(id_tramway[j]));

		       Trajet trj=new Trajet();
		       trj.setDuree(new Integer(req.getParameter("trajet_duree")));
		       trj.setDepart(s1);
		       trj.setArrivee(s2);
		       
		       int nombre_position=new Integer(req.getParameter("nbr_posi"));
		  
		       
		       for(int i=0;i<nombre_position;i++)
		       {
		    	   Point point=new Point();
		    	   point.setLatitude(new Double(req.getParameter("p_lat"+i)));
		    	   point.setLongitude(new Double(req.getParameter("p_lng"+i)));
		    	   point.setIndic(i);
		    	   trj.getPoint().add(point);
		       }
		       
		       tram.getItineraires().add(trj);
		       tramwayservice.AjouteTram(tram);
		   }
	       
	       tramwayservice.close();
		return  new ModelAndView("redirect:/tramway");
	}

	
	/*@RequestMapping(value = "/getTramway", method = RequestMethod.GET)
	public  @ResponseBody String gettram(HttpServletRequest req, HttpServletResponse res, Model model)
	{
		
		  AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	       ctx.register(AppConfig.class);
	       ctx.refresh();
	       GraphDatabase graphDatabase = (GraphDatabase)ctx.getBean("graphDatabase");
	       
	       TramwayRepository tramwayRepository =ctx.getBean(TramwayRepository.class);
	       Transaction tx = graphDatabase.beginTx();
	      
	       String s="";
	       try {
	    	  
	    	   Iterable<Tramway> list=tramwayRepository.getAll();
	    	   for (Tramway t: list) {
	    		  s+="<option value=\""+t.getId()+"\">"+t.getDepart()+"->"+t.getArrivee()+"</option>" ;
	    	   }
	    	  
	    	   tx.success();
	        } finally {
	           tx.close();
	        }
	       
		
		return s;
	}*/
	
	@JsonView(Views.PublicSet.class)
	@RequestMapping(value="/getStationTramway/{id_tramway}")
		public @ResponseBody List<Trajet>  getStation(@PathVariable  long id_tramway) {
		TrajetService tr=new TrajetService();
		List<Trajet> list;
		if(id_tramway==-1)
			list=tr.getAllTrajetTramway();
		else
			list=tr.getAllTrajetTramway(id_tramway);
		tr.close();
		for(int i=0 ; i<list.size();i++)
		{
			 SortedSet set = new TreeSet();
			 set.addAll(list.get(i).getPoint());
			 list.get(i).getPoint().clear();
			 list.get(i).getPoint().addAll(set);
		}
		return list;
		}
	
	@RequestMapping(value="/getTramwayByStationDepartAndArrivee/{id_depart}/{id_arrivee}")
	public @ResponseBody String gettramway(@PathVariable  long id_depart,@PathVariable long id_arrivee) {
	TramwayService tramwayservice=new TramwayService();
	List<TramwayData> l = tramwayservice.getIdDepartArriveeTramwayByStationDepartStationArrivee(id_depart, id_arrivee);
	tramwayservice.close();
	String s="";
	if(l.size()>0) s+=""+l.get(0).getId();
	for(int i=1;i<l.size();i++)
		s+=","+l.get(i).getId();
	return s;
	}
	
	@RequestMapping(value = "/updateStationTramway", method = RequestMethod.POST)
	public  @ResponseBody ModelAndView updateStation(HttpServletRequest req, HttpServletResponse res)
	{		
		StationService stservice = new StationService();		
		Station st=new Station();
		st.setId(new Integer(req.getParameter("modifier_station_id")).longValue());
		st.setNom(req.getParameter("modifier_station_nom"));
		st.setAdresse(req.getParameter("modifier_station_adresse"));		 
		st = stservice.UpStation(st);	   	   
		stservice.close();
		return  new ModelAndView("redirect:/tramway");
	}
	
	@RequestMapping(value = "/updateTrajetTramway", method = RequestMethod.POST)
	public  @ResponseBody ModelAndView  updateTrajet(HttpServletRequest req, HttpServletResponse res)
	{		
		TrajetService trservice = new TrajetService();		
		Trajet tr=new Trajet();
		tr.setId(new Integer(req.getParameter("modifier_trajet_id")).longValue());
		tr.setDuree(new Integer(req.getParameter("modifier_trajet_duree")));	 
		tr = trservice.UpTrajet(tr);	   	   
		trservice.close();	
		return  new ModelAndView("redirect:/tramway");
	}
	@RequestMapping(value = "/updateTramway", method = RequestMethod.POST)
	public  @ResponseBody ModelAndView updateTramway(HttpServletRequest req, HttpServletResponse res)
	{		
		TramwayService tramwayservice = new TramwayService();		
		Tramway tram=new Tramway();
		tram.setId(new Long(req.getParameter("modifier_tramway_id")));
		tram.setDepart(req.getParameter("modifier_tramway_depart"));
		tram.setArrivee(req.getParameter("modifier_tramway_arrivee"));
		tram.setPrix(new Double(req.getParameter("modifier_tramway_prix")));			 
		Tramway temp=tramwayservice.ModifTram(tram);	   	   
		 tramwayservice.close();
		 return  new ModelAndView("redirect:/tramway");
	}
	@RequestMapping(value="/deleteTram", method=RequestMethod.POST)
	public @ResponseBody ModelAndView SupprimerTram(HttpServletRequest req, HttpServletResponse res){
		 TramwayService tramwayservice =new TramwayService();
		 tramwayservice.DeleteTramway(new Integer(req.getParameter("supprimer_tramway_id")).longValue());
		 tramwayservice.close();
		return new ModelAndView("redirect:/tramway");
		
	}
	@RequestMapping(value="/deleteStationTramway",method = RequestMethod.POST)
	public @ResponseBody ModelAndView DeleteStationByTram(HttpServletRequest req, HttpServletResponse res)
	{
		
		long id_Station=new Integer(req.getParameter("supprimer_station_id")).longValue();
		long id_tram=new Integer(req.getParameter("supprimer_station_tramway_id")).longValue();
		TrajetService trajetService=new TrajetService();
	    StationService stationService=new StationService();
	    PointService pointService=new PointService();
	   
		List<Trajet> trajets=trajetService.getTrajetByidTramway(id_Station,id_tram);
		if(trajets.size()==2)
		{
			if(trajets.get(0).getArrivee().getId()==id_Station){
				trajetService.CreateRelationShip(trajets.get(0).getId(),trajets.get(1).getArrivee().getId());
				Iterator<Point> it=trajets.get(1).getPoint().iterator();
				while(it.hasNext()){
					trajets.get(0).getPoint().add(it.next());
				}
				trajetService.DeleteTrajet(trajets.get(1).getId());
				trajetService.DeleteRelationShip(trajets.get(0).getId(),id_Station);
				stationService.DeleteAllSingleStation();
				pointService.DeleteAllSinglePoints();
			}
			else
			{
				trajetService.CreateRelationShip(trajets.get(1).getId(),trajets.get(0).getArrivee().getId());
				Iterator<Point> it=trajets.get(0).getPoint().iterator();
				while(it.hasNext()){
					trajets.get(1).getPoint().add(it.next());
				}
				trajetService.DeleteTrajet(trajets.get(0).getId());
				trajetService.DeleteRelationShip(trajets.get(1).getId(),id_Station);
				stationService.DeleteAllSingleStation();
				pointService.DeleteAllSinglePoints();
			}
		}
	   else{
			if(trajets.size()==1)
			{
			trajetService.DeleteTrajet(trajets.get(0).getId());
			stationService.DeleteAllSingleStation();
			pointService.DeleteAllSinglePoints();
			}
			else
			{
				System.out.println("-------------------------------------------- la taille est 0");
			}
		}
		stationService.close();
		trajetService.close();
		pointService.close();
		return new ModelAndView("redirect:/tramway");
	}
	@RequestMapping(value="/deleteTrajetTramway", method=RequestMethod.POST)
	public @ResponseBody ModelAndView SupprimerTrajet(HttpServletRequest req, HttpServletResponse res){
		 TrajetService trajetService=new TrajetService();
		 trajetService.DeleteTrajet(new Integer(req.getParameter("supprimer_trajet_id")).longValue());
		 trajetService.close();
		 StationService stationService=new StationService();
		 stationService.DeleteAllSingleStation();
		 stationService.close();
		
		 PointService pointService=new PointService();
		 pointService.DeleteAllSinglePoints();
		 
		 return new ModelAndView("redirect:/tramway");
	}
	
	
	@JsonView(Views.PublicSet.class)
	@RequestMapping(value="/getTramway/{id}")
		public @ResponseBody TramwayData2  gettramway(@PathVariable  long id) {
		TramwayService tramwayservice=new TramwayService();
		TramwayData2 tram= tramwayservice.getIdDepartArriveeTramway(id);
		tramwayservice.close();
		return tram;
		}
		
}
