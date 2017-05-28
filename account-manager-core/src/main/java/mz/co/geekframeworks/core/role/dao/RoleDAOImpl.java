/**
 * 
 */
package mz.co.geekframeworks.core.role.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.util.ParamBuilder;
import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 * 
 */
@Repository(RoleDAO.NAME)
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO
{
	
	@Override
	public Collection<Role> findRoles()
	{
		return this.findByNamedQuery(
				RoleDAO.QUERY_NAME.findRoles,
				new ParamBuilder().add("lifeCycleStatus",
						LifeCycleStatus.ACTIVE).process());
	}
}
