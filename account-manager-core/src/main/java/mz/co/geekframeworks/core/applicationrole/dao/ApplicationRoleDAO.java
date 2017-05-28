/**
 *
 */
package mz.co.geekframeworks.core.applicationrole.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.mozview.frameworks.core.dao.GenericDAO;

/**
 * @author Stélio Moiane
 *
 */
public interface ApplicationRoleDAO extends GenericDAO<ApplicationRole, Long>
{
	
	public static final String NAME = "mz.co.geekframeworks.core.applicationrole.dao.ApplicationRoleDAO";
	
	public static class QUERY
	{
		public static final String fetchAll = "select distinct ar from ApplicationRole ar "
				+ "inner join fetch ar.application a "
				+ "inner join fetch ar.role r "
				+ "left join fetch ar.transactions "
				+ "where ar.lifeCycleStatus = :lifeCycleStatus order by a.code, r.name";
		
		public static final String fetchByApplication = "select ar from ApplicationRole ar "
				+ "inner join fetch ar.application a "
				+ "inner join fetch ar.role r "
				+ "where a = :application and "
				+ "ar.lifeCycleStatus = :lifeCycleStatus order by a.code, r.name";
	}
	
	public static class QUERY_NAME
	{
		public static final String fetchAll = "ApplicationRole.fetchAll";
		
		public static final String fetchByApplication = "ApplicationRole.fetchByApplication";
	}
	
	public abstract Collection<ApplicationRole> fetchAll();
	
	public abstract Collection<ApplicationRole> fetchByApplication(
			final Application application);
	
}
