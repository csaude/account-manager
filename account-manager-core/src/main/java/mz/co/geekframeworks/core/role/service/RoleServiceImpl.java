/**
 * 
 */
package mz.co.geekframeworks.core.role.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.role.dao.RoleDAO;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * @author Eudson Bambo
 */
@Service(RoleService.NAME)
public class RoleServiceImpl extends AbstractService implements RoleService {
	@Inject
	private RoleDAO roleDAO;

	@Override
	public Role createRole(final UserContext userContext, final Role role) throws BusinessException {
		return this.roleDAO.create(userContext.getUuid(), role);
	}

	@Override
	public Role updateRole(final UserContext userContext, final Role role) {
		return this.roleDAO.update(userContext.getUuid(), role);
	}

	@Override
	public void deleteRole(final UserContext userContext, final Role role) {
		this.roleDAO.delete(userContext.getUuid(), role);
	}
}
