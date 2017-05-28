/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.dao;

import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 *
 */
@Repository(UnitDAO.NAME)
public class UnitDAOImp extends GenericDAOImpl<Unit, Long> implements UnitDAO
{
	@Override
	public List<Unit> findAll(final LifeCycleStatus lifeCycleStatus)
	{
		return this.findByNamedQuery(UnitDAO.QUERY_NAME.findAll,
				new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus)
						.process());
	}
	
	@Override
	public List<Unit> fetchByAppliaction(final LifeCycleStatus lifeCycleStatus,
			final Application application)
	{
		return this.findByNamedQuery(
				UnitDAO.QUERY_NAME.fetchByAppliaction,
				new ParamBuilder().add("application", application)
						.add("lifeCycleStatus", lifeCycleStatus).process());
	}
	
	@Override
	public Unit findByCode(final String unitCode,
			final LifeCycleStatus lifeCycleStatus)
	{
		return this.findSingleByNamedQuery(
				UnitDAO.QUERY_NAME.findByCode,
				new ParamBuilder().add("unitCode", unitCode)
						.add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
