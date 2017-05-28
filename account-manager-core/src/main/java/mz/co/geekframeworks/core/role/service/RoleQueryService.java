/**
 * 
 */
package mz.co.geekframeworks.core.role.service;

import java.util.Collection;

import mz.co.geekframeworks.core.role.model.Role;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Eudson Bambo
 * 
 */
public interface RoleQueryService
{
	public static final String NAME = "mz_co_geekframeworks_core_role_service_RoleQueryService";
	
	public abstract Collection<Role> findRoles(final UserContext userContext)
			throws BusinessException;
	
	public abstract Role findById(final Long id) throws BusinessException;
}
