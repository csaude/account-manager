/**
 *
 */
package mz.co.geekframeworks.core.user.service;

import java.util.Collection;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author St�lio Moiane
 *
 */
public interface UserQueryService extends UserDetailsService
{
	public static final String NAME = "mz_co_geekframeworks_core_user_service_UserQueryService";
	
	public Collection<User> findAllUsers(final UserContext userContext)
			throws BusinessException;
	
	public User findById(final Long id) throws BusinessException;
	
	public User findUserBySessionId(final String sessionId)
			throws BusinessException;
	
	public User fetchUserByApplicationCodeAndUnitCodeAndUsername(
			final UserContext userContext, final String applicationCode,
			final String unitCode, final String username);
}
