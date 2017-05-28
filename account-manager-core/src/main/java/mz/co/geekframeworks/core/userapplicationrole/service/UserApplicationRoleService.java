/**
 * 
 */
package mz.co.geekframeworks.core.userapplicationrole.service;

import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * 
 */
public interface UserApplicationRoleService
{
	
	public static final String NAME = "mz_co_geekframeworks_core_userapplicationrole_service_UserApplicationRoleService";
	
	public UserApplicationRole createUserApplicationRole(
			final UserContext userContext,
			final UserApplicationRole userApplicationRole)
					throws BusinessException;
	
	public UserApplicationRole updateUserApplicationRole(
			final UserContext userContext,
			final UserApplicationRole userApplicationRole)
					throws BusinessException;
}
