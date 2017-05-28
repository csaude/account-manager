package mz.co.geekframeworks.core.user.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.util.ParamBuilder;
import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

import org.springframework.stereotype.Repository;

/**
 * @author Eudson Bambo
 * @author Stélio Moiane
 */
@Repository(UserDAO.NAME)
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO
{
	
	@Override
	public Collection<User> findAll()
	{
		return this.findByNamedQuery(
				UserDAO.QUERY_NAME.findAll,
				new ParamBuilder()
				.add("lifeCycleStatus", LifeCycleStatus.ACTIVE)
				.add("accountNonExpired", true)
				.add("accountNonLocked", true)
				.add("credentialsNonExpired", true)
				.add("enabled", true).process());
	}
	
	@Override
	public User fetchByUsername(final String username)
	{
		return this.findSingleByNamedQuery(
				UserDAO.QUERY_NAME.fetchByUsername,
				new ParamBuilder().add("username", username)
				.add("lifeCycleStatus", LifeCycleStatus.ACTIVE)
				.process());
	}
	
	@Override
	public User findBySessionId(final String sessionId)
	{
		return this.findSingleByNamedQuery(UserDAO.QUERY_NAME.findBySessionId,
				new ParamBuilder().add("sessionId", sessionId).process());
	}
	
	@Override
	public User fetchByApplicationCodeAndUnitCodeAndUsername(
			final String applicationCode, final String unitCode,
			final String username)
	{
		return this
				.findSingleByNamedQuery(
						UserDAO.QUERY_NAME.fetchByApplicationCodeAndUnitCodeAndUsername,
						new ParamBuilder()
						.add("applicationCode", applicationCode)
						.add("unitCode", unitCode)
						.add("username", username).process());
	}
}
