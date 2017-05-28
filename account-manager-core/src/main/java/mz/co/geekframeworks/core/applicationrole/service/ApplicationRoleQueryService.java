/**
 *
 */
package mz.co.geekframeworks.core.applicationrole.service;

import java.util.Collection;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author St�lio Moiane
 * 
 */
public interface ApplicationRoleQueryService
{
	public static final String NAME = "mz_co_geekframeworks_core_applicationrole_service_ApplicationRoleQueryService";
	
	public Collection<ApplicationRole> fetchAllApplicationRoles(
			final UserContext userContext) throws BusinessException;
	
	public Collection<ApplicationRole> fetchAppliactionRolesByApplication(
			final UserContext userContext, final Application application)
			throws BusinessException;
	
	public ApplicationRole findById(final Long id) throws BusinessException;
}
