package mz.co.geekframeworks.core.application.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.mozview.frameworks.core.dao.GenericDAO;

/**
 * @author Stélio Moiane
 * 
 */
public interface ApplicationDAO extends GenericDAO<Application, Long>
{
	
	public static final String NAME = "mz.co.geekframeworks.core.application.dao.AplicationDAO";
	
	public static class QUERY
	{
		public static final String findApplications = "select a from Application a where a.lifeCycleStatus = :lifeCycleStatus";
	}
	
	public static class QUERY_NAME
	{
		
		public static final String findApplications = "Application.findApplications";
		
	}
	
	public abstract Collection<Application> findApplications();
}
