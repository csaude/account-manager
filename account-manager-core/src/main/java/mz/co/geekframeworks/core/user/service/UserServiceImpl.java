package mz.co.geekframeworks.core.user.service;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.service.AbstractService;
import mz.co.geekframeworks.core.user.dao.UserDAO;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Eudson Bambo
 */
@Service(UserService.NAME)
public class UserServiceImpl extends AbstractService implements UserService {
	@Inject
	private UserDAO userDAO;

	@Inject
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(final UserContext userContext, final User user) throws BusinessException {
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		return this.userDAO.create(userContext.getUuid(), user);
	}

	@Override
	public User updateUser(final UserContext userContext, final User user) throws BusinessException {
		return this.userDAO.update(userContext.getUuid(), user);
	}

	@Override
	public void deleteUser(final UserContext userContext, final User user) throws BusinessException {
		this.userDAO.delete(userContext.getUuid(), user);
	}
}
