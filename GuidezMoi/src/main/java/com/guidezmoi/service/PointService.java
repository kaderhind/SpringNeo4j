package com.guidezmoi.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.guidezmoi.config.AppConfig;
import com.guidezmoi.entity.Point;
import com.guidezmoi.repository.PointRepository;

@Service
public class PointService {

	private PointRepository repository;
	
	private AnnotationConfigApplicationContext ctx;
	
	public PointService()
	{
		ctx = new AnnotationConfigApplicationContext();
	    ctx.register(AppConfig.class);
	    ctx.refresh();
	    repository =ctx.getBean(PointRepository.class);
	}
	public void AjoutePoint(Point point)
	{
		repository.save(point);
	}
	
	public Point getPointById(long id)
	{
		
		return repository.getById(id);
	}
	public List<Point> getAllPoint()
	{
		return repository.getAll();
	}
	
	public void DeleteAllSinglePoints()
	{
		repository.deleteAllSinglePoints();
	}
	public void close()
	{
		ctx.close();
	}
}
