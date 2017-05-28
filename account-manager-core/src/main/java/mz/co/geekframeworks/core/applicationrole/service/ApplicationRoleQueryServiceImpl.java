/**
 *
 */
package mz.co.geekframeworks.core.applicationrole.service;

import java.util.Collection;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.dao.ApplicationRoleDAO;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.stereotype.Service;

/**
 * @author Stélio Moiane
 * 
 */
@Service(ApplicationRoleQueryService.NAME)
public class ApplicationRoleQueryServiceImpl extends AbstractService implements
		ApplicationRoleQueryService
{
	@Inject
	private ApplicationRoleDAO applicationRoleDAO;
	
	@Override
	public Collection<ApplicationRole> fetchAllApplicationRoles(
			final UserContext userContext) throws BusinessException
	{
		return this.applicationRoleDAO.fetchAll();
	}
	
	@Override
	public Collection<ApplicationRole> fetchAppliactionRolesByApplication(
			final UserContext userContext, final Application application)
			throws BusinessException
	{
		return this.applicationRoleDAO.fetchByApplication(application);
	}
	
	@Override
	public ApplicationRole findById(final Long id) throws BusinessException
	{
		return this.applicationRoleDAO.findById(id);
	}
}
