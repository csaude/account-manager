/**
 * 
 */
package mz.co.geekframeworks.core.role.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.role.model.Role;
import mz.co.mozview.frameworks.core.dao.GenericDAO;

/**
 * @author Stélio Moiane
 * 
 */
public interface RoleDAO extends GenericDAO<Role, Long>
{
	public static final String NAME = "mz.co.geekframeworks.core.role.model.dao.RoleDAO";
	
	public static class QUERY
	{
		public static final String findRoles = "select r from Role r where r.lifeCycleStatus = :lifeCycleStatus";
	}
	
	public static class QUERY_NAME
	{
		public static final String findRoles = "Role.findRoles";
	}
	
	public Collection<Role> findRoles();
}
