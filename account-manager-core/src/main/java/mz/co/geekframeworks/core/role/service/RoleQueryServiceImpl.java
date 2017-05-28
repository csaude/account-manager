/**
 * 
 */
package mz.co.geekframeworks.core.role.service;

import java.util.Collection;

import javax.inject.Inject;

import mz.co.geekframeworks.core.role.dao.RoleDAO;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.service.AbstractQueryService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.stereotype.Service;

/**
 * @author Eudson Bambo
 * 
 */
@Service(RoleQueryService.NAME)
public class RoleQueryServiceImpl extends AbstractQueryService implements
		RoleQueryService
{
	@Inject
	private RoleDAO roleDAO;
	
	@Override
	public Collection<Role> findRoles(final UserContext userContext)
			throws BusinessException
	{
		return this.roleDAO.findRoles();
	}
	
	@Override
	public Role findById(final Long id) throws BusinessException
	{
		return this.roleDAO.findById(id);
	}
}
