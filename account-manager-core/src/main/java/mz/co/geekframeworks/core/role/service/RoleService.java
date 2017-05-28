/**
 * 
 */
package mz.co.geekframeworks.core.role.service;

import mz.co.geekframeworks.core.role.model.Role;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * 
 */
public interface RoleService
{
	public static final String NAME = "mz_co_geekframeworks_core_role_model_service_RoleService";
	
	public Role createRole(final UserContext userContext, final Role role)
			throws BusinessException;
	
	public Role updateRole(final UserContext userContext, final Role role)
			throws BusinessException;
	
	public void deleteRole(final UserContext userContext, final Role role)
			throws BusinessException;
}
