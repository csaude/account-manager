/**
 * 
 */
package mz.co.geekframeworks.core.security;

import org.springframework.security.core.Authentication;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 * 
 */
public interface AuthenticationService {
	public static final String NAME = "mz_co_mozview_sales_core_security_AuthenticationService";

	public void login(final String username, final String password) throws BusinessException;

	public void logout();

	public User getAuthenticated();

	public Authentication getAuthentication(final String username, final String password) throws BusinessException;
}
