/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.dao;

import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

/**
 * @author Stélio Moiane
 *
 */
public interface UnitDAO extends GenericDAO<Unit, Long>
{
	
	String NAME = "mz.co.geekframeworks.core.unit.dao.UnitDAO";
	
	public static class QUERY
	{
		public static final String findAll = "select u from Unit u where u.lifeCycleStatus = :lifeCycleStatus";
		
		public static final String fetchByAppliaction = "select u from Unit u join fetch u.applications app where app = :application and u.lifeCycleStatus = :lifeCycleStatus";
		
		public static final String findByCode = "select u from Unit u where u.code = :unitCode and u.lifeCycleStatus = :lifeCycleStatus";
	}
	
	public static class QUERY_NAME
	{
		public static final String findAll = "Unit.findAll";
		
		public static final String fetchByAppliaction = "Unit.fetchByAppliaction";
		
		public static final String findByCode = "Unit.findByCode";
	}
	
	public abstract List<Unit> findAll(final LifeCycleStatus lifeCycle);
	
	public abstract List<Unit> fetchByAppliaction(
			final LifeCycleStatus lifeCycleStatus, final Application appliaction);
	
	public abstract Unit findByCode(final String unitCode,
			final LifeCycleStatus lifeCycleStatus);
}
