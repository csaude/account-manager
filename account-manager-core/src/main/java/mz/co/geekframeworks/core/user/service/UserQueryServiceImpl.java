/**
 *
 */
package mz.co.geekframeworks.core.user.service;

import java.util.Collection;

import javax.inject.Inject;

import mz.co.geekframeworks.core.user.dao.UserDAO;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author St�lio Moiane
 *
 */
@Service(UserQueryService.NAME)
public class UserQueryServiceImpl implements UserQueryService
{
	@Inject
	private UserDAO userDAO;
	
	@Override
	public Collection<User> findAllUsers(final UserContext userContext)
			throws BusinessException
			{
		return this.userDAO.findAll();
			}
	
	@Override
	public User findById(final Long id) throws BusinessException
	{
		return this.userDAO.findById(id);
	}
	
	@Override
	public User loadUserByUsername(final String username)
			throws UsernameNotFoundException
	{
		return this.userDAO.fetchByUsername(username);
	}
	
	@Override
	public User findUserBySessionId(final String sessionId)
			throws BusinessException
	{
		return this.userDAO.findBySessionId(sessionId);
	}
	
	@Override
	public User fetchUserByApplicationCodeAndUnitCodeAndUsername(
			final UserContext userContext, final String applicationCode,
			final String unitCode, final String username)
	{
		return this.userDAO.fetchByApplicationCodeAndUnitCodeAndUsername(
				applicationCode, unitCode, username);
	}
}
