/**
 *
 */
package mz.co.geekframeworks.core.applicationrole.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.util.ParamBuilder;
import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 *
 */

@Repository(ApplicationRoleDAO.NAME)
public class ApplicationRoleDAOImpl extends
		GenericDAOImpl<ApplicationRole, Long> implements ApplicationRoleDAO
{
	
	@Override
	public Collection<ApplicationRole> fetchAll()
	{
		return this.findByNamedQuery(
				ApplicationRoleDAO.QUERY_NAME.fetchAll,
				new ParamBuilder().add("lifeCycleStatus",
						LifeCycleStatus.ACTIVE).process());
	}
	
	@Override
	public Collection<ApplicationRole> fetchByApplication(
			final Application application)
	{
		return this.findByNamedQuery(
				ApplicationRoleDAO.QUERY_NAME.fetchByApplication,
				new ParamBuilder().add("application", application)
						.add("lifeCycleStatus", LifeCycleStatus.ACTIVE)
						.process());
	}
}
