package mz.co.geekframeworks.core.user.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.dao.GenericDAO;

/**
 * @author Eudson Bambo
 * @author Stelio Moiane
 */
public interface UserDAO extends GenericDAO<User, Long>
{
	public static final String NAME = "mz.co.geekframeworks.core.user.dao.UserDAO";
	
	public static class QUERY
	{
		public static final String findAll = "select u from User u where u.lifeCycleStatus = :lifeCycleStatus and "
				+ "u.accountNonExpired = :accountNonExpired and "
				+ "u.accountNonLocked = :accountNonLocked and "
				+ "u.credentialsNonExpired = :credentialsNonExpired and "
				+ "u.enabled = :enabled order by u.fullName";
		
		public static final String fetchByUsername = "select u from User u left join fetch u.userApplicationRoles uar "
				+ "left join fetch uar.applicationRole ar "
				+ "left join fetch ar.application a "
				+ "left join fetch ar.transactions t "
				+ "left join fetch uar.units "
				+ "left join fetch ar.role "
				+ "where u.username = :username and u.lifeCycleStatus = :lifeCycleStatus";
		
		public static final String findBySessionId = "select u from User u left join fetch u.userApplicationRoles uar "
				+ "left join fetch uar.applicationRole ar "
				+ "left join fetch ar.application a "
				+ "left join fetch ar.transactions t "
				+ "left join fetch uar.units "
				+ "left join fetch ar.role "
				+ "where u.sessionId = :sessionId";
		
		public static final String fetchByApplicationCodeAndUnitCodeAndUsername = "select u from User u left join fetch u.userApplicationRoles uar "
				+ "left join fetch uar.applicationRole ar "
				+ "left join fetch ar.application a "
				+ "left join fetch ar.transactions t "
				+ "left join fetch uar.units ut "
				+ "left join fetch ar.role "
				+ "where u.username = :username and a.code = :applicationCode and ut.code = :unitCode";
	}
	
	public static class QUERY_NAME
	{
		public static final String findAll = "User.findAll";
		
		public static final String fetchByUsername = "User.fetchByUsername";
		
		public static final String findBySessionId = "User.findBySessionId";
		
		public static final String fetchByApplicationCodeAndUnitCodeAndUsername = "User.fetchByApplicationCodeAndUnitCodeAndUsername";
	}
	
	public abstract Collection<User> findAll();
	
	public abstract User fetchByUsername(final String userName);
	
	public abstract User findBySessionId(final String sessionId);
	
	public abstract User fetchByApplicationCodeAndUnitCodeAndUsername(
			String applicationCode, String unitCode, String username);
}
