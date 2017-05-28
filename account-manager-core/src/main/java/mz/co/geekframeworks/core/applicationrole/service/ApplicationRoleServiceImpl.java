/**
 * 
 */
package mz.co.geekframeworks.core.applicationrole.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.dao.ApplicationRoleDAO;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * @author Eudson Bambo
 */

@Service(ApplicationRoleService.NAME)
public class ApplicationRoleServiceImpl extends AbstractService implements ApplicationRoleService {
	@Inject
	private ApplicationRoleDAO applicationRoleDAO;

	@Override
	public ApplicationRole createApplicationRole(final UserContext userContext, final Application application,
			final Role role) throws BusinessException {

		ApplicationRole applicationRole = new ApplicationRole();
		applicationRole.setApplication(application);
		applicationRole.setRole(role);

		return this.applicationRoleDAO.create(userContext.getUuid(), applicationRole);
	}

	@Override
	public ApplicationRole updateApplicationRole(final UserContext userContext, final ApplicationRole applicationRole)
			throws BusinessException {
		return this.applicationRoleDAO.update(userContext.getUuid(), applicationRole);
	}

	@Override
	public void deleteApplicationRole(final UserContext userContext, final ApplicationRole applicationRole)
			throws BusinessException {
		this.applicationRoleDAO.delete(userContext.getUuid(), applicationRole);
	}
}
