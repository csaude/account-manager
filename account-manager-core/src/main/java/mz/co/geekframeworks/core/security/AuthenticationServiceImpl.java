/**
 *
 */
package mz.co.geekframeworks.core.security;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.PropertyValues;

/**
 * @author Stélio Moiane
 *
 */
@Service(AuthenticationService.NAME)
public class AuthenticationServiceImpl implements AuthenticationService {

	@Inject
	private AuthenticationProvider authenticationProvider;

	@Inject
	private PropertyValues propertyValues;

	@Override
	public void login(final String username, final String password) throws BusinessException {
		Authentication authentication = this.getAuthentication(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Override
	public User getAuthenticated() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public Authentication getAuthentication(final String username, final String password) throws BusinessException {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = null;

		try {
			authentication = this.authenticationProvider.authenticate(token);
		} catch (AuthenticationServiceException ex) {
			throw new BusinessException(this.propertyValues.getPropValues("auth.authentication.service.exception"));
		} catch (BadCredentialsException ex) {
			throw new BusinessException(this.propertyValues.getPropValues("auth.bad.credentials.exception"));
		}

		return authentication;
	}
}
