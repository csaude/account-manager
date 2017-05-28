/**
 * 
 */
package mz.co.geekframeworks.core.application.dao;

import java.util.Collection;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.util.ParamBuilder;
import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 * 
 */
@Repository(ApplicationDAO.NAME)
public class ApplicationDAOImpl extends GenericDAOImpl<Application, Long>
implements ApplicationDAO
{
	
	@Override
	public Collection<Application> findApplications()
	{
		return this.findByNamedQuery(
				ApplicationDAO.QUERY_NAME.findApplications, new ParamBuilder()
				.add("lifeCycleStatus", LifeCycleStatus.ACTIVE)
				.process());
	}
}
