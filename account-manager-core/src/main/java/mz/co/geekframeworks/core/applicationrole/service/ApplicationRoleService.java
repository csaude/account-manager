/**
 * 
 */
package mz.co.geekframeworks.core.applicationrole.service;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * @author Eudson Bambo
 */
public interface ApplicationRoleService
{
	
	public static final String NAME = "mz_co_geekframeworks_core_applicationrole_service_ApplicationRoleService";
	
	public ApplicationRole createApplicationRole(final UserContext userContext,
			final Application application, Role role) throws BusinessException;
	
	public ApplicationRole updateApplicationRole(final UserContext userContext,
			final ApplicationRole applicationRole) throws BusinessException;
	
	public void deleteApplicationRole(final UserContext userContext,
			final ApplicationRole applicationRole) throws BusinessException;
}
