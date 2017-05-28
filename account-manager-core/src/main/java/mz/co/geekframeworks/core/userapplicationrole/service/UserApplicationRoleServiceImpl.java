/**
 * 
 */
package mz.co.geekframeworks.core.userapplicationrole.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.service.AbstractService;
import mz.co.geekframeworks.core.userapplicationrole.dao.UserApplicationRoleDAO;
import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

@Service(UserApplicationRoleService.NAME)
public class UserApplicationRoleServiceImpl extends AbstractService implements UserApplicationRoleService {
	@Inject
	private UserApplicationRoleDAO userApplicationRoleDAO;

	@Override
	public UserApplicationRole createUserApplicationRole(final UserContext userContext,
			final UserApplicationRole userApplicationRole) throws BusinessException {
		return this.userApplicationRoleDAO.create(userContext.getUuid(), userApplicationRole);
	}

	@Override
	public UserApplicationRole updateUserApplicationRole(final UserContext userContext,
			final UserApplicationRole userApplicationRole) throws BusinessException {
		return this.userApplicationRoleDAO.update(userContext.getUuid(), userApplicationRole);
	}
}
