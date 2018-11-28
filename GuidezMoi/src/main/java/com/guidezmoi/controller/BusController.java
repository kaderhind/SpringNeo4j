package com.guidezmoi.controller;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
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
import com.guidezmoi.entity.Views;
import com.guidezmoi.repository.BusRepository.BusData;
import com.guidezmoi.repository.BusRepository.BusData2;
import com.guidezmoi.repository.TramwayRepository.TramwayData;
import com.guidezmoi.repository.TramwayRepository.TramwayData2;
import com.guidezmoi.service.BusService;
import com.guidezmoi.service.PointService;
import com.guidezmoi.service.StationService;
import com.guidezmoi.service.TrajetService;
import com.guidezmoi.service.TramwayService;

@Controller
public class BusController {
	public BusController() {
 	}
	
	@RequestMapping(value = "/bus", method = RequestMethod.GET)
	public ModelAndView getdata() {
		
	        BusService busservice =new BusService();
			ModelAndView model = new ModelAndView("bus");
			model.addObject("buss",( List<BusData>)busservice.getIdNumDeLigneAllBus());
			busservice.close();
		return model;
	}
	
	@RequestMapping(value = "/addBus", method = RequestMethod.POST)
			public  @ResponseBody String adddBus(HttpServletRequest req, HttpServletResponse res)
			{
				String result="";
				BusService busService=new BusService();
				Bus bus=new Bus();
				bus.setNumDeLigne(req.getParameter("ajoute_bus_numero"));
				bus.setPrix(new Double(req.getParameter("ajoute_bus_prix")));
				Bus temp=busService.AddBus(bus);
			    result+="<option value=\""+temp.getId()+"\">Bus :"+temp.getNumDeLigne()+"</option>" ;
				busService.close();
				return result;
			}
	@RequestMapping(value = "/addBusData", method = RequestMethod.POST)
	public @ResponseBody ModelAndView adddBusData(HttpServletRequest req, HttpServletResponse res)
	{
		  
		
		   BusService busService=new BusService();
		   String [] id_bus=req.getParameter("trajet_bus").split(",");
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
	       
		   for(int j=0 ;j<id_bus.length;j++)
		   {
			   Bus bus=busService.getBusByID(new Integer(id_bus[j]));
			   
		     
		      	       
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
		       
		       bus.getItineraires().add(trj);
		       busService.AddBus(bus);
		   }
		   
	       busService.close();
	       return  new ModelAndView("redirect:/bus");
	}
	
	@JsonView(Views.PublicSet.class)
	@RequestMapping(value="/getallbus")
		public @ResponseBody List<Bus>  getAllBus() {
		BusService bs=new BusService();
		List<Bus> lbus;
		lbus=bs.getAllBus();
		bs.close();
		return lbus;
		}
 
	
	@JsonView(Views.PublicSet.class)
	@RequestMapping(value="/getStationBus/{id_bus}")
		public @ResponseBody List<Trajet>  getStation(@PathVariable  long id_bus) {
		TrajetService tr=new TrajetService();
		List<Trajet> list;
		if(id_bus==-1)
			list=tr.getAllTrajetBus();
		else
			list=tr.getAllTrajetBus(id_bus);
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
	
	@RequestMapping(value="/getBusByStationDepartAndArrivee/{id_depart}/{id_arrivee}")
	public @ResponseBody String getBus(@PathVariable  long id_depart,@PathVariable long id_arrivee) {
		BusService busservice=new BusService();
	List<BusData> l = busservice.getIdNumDeLigneBusByStationDepartStationArrivee(id_depart, id_arrivee);
	busservice.close();
	String s="";
	if(l.size()>0) s+=""+l.get(0).getId();
	for(int i=1;i<l.size();i++)
		s+=","+l.get(i).getId();
	return s;
	}
	
	@JsonView(Views.PublicSet.class)
	@RequestMapping(value="/getBus/{id}")
		public @ResponseBody BusData2  gettramway(@PathVariable  long id) {
		BusService busservice=new BusService();
		BusData2 bus= busservice.getIdNumDeLigneBus(id);
		busservice.close();
		return bus;
		}
	
	@RequestMapping(value="/deleteBus", method=RequestMethod.POST)
	public @ResponseBody ModelAndView SupprimerBus(HttpServletRequest req, HttpServletResponse res){
		BusService busservice =new BusService();
		 busservice.DeleteBus(new Integer(req.getParameter("supprimer_bus_id")).longValue());
		 busservice.close();
		return new ModelAndView("redirect:/bus");
		
	}
	@RequestMapping(value="/deleteStationBus",method = RequestMethod.POST)
	public @ResponseBody ModelAndView DeleteStationByBus(HttpServletRequest req, HttpServletResponse res)
	{
		
		long id_Station=new Integer(req.getParameter("supprimer_station_id")).longValue();
		long id_bus=new Integer(req.getParameter("supprimer_station_bus_id")).longValue();
		TrajetService trajetService=new TrajetService();
	    StationService stationService=new StationService();
	    PointService pointService=new PointService();
	   
		List<Trajet> trajets=trajetService.getTrajetByidBus(id_Station,id_bus);
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
		return new ModelAndView("redirect:/bus");
	}
	@RequestMapping(value="/deleteTrajetBus", method=RequestMethod.POST)
	public @ResponseBody ModelAndView SupprimerTrajet(HttpServletRequest req, HttpServletResponse res){
		 TrajetService trajetService=new TrajetService();
		 trajetService.DeleteTrajet(new Integer(req.getParameter("supprimer_trajet_id")).longValue());
		 trajetService.close();
		 StationService stationService=new StationService();
		 stationService.DeleteAllSingleStation();
		 stationService.close();
		
		 PointService pointService=new PointService();
		 pointService.DeleteAllSinglePoints();
		 
		 return new ModelAndView("redirect:/bus");
	}
	
	
}
