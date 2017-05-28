/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.unit.dao.UnitDAO;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.stereotype.Service;

/**
 * @author Stélio Moiane
 *
 */
@Service(UnitQueryService.NAME)
public class UnitQueryServiceImpl implements UnitQueryService
{
	@Inject
	private UnitDAO unitDAO;
	
	@Override
	public List<Unit> findAllUnits(final UserContext userContext)
			throws BusinessException
			{
		return this.unitDAO.findAll(LifeCycleStatus.ACTIVE);
			}
	
	@Override
	public List<Unit> fetchUnitsByApplication(final UserContext userContext,
			final Application application) throws BusinessException
			{
		return this.unitDAO.fetchByAppliaction(LifeCycleStatus.ACTIVE, application);
			}
	
	@Override
	public Unit findById(final Long unitId) throws BusinessException
	{
		return this.unitDAO.findById(unitId);
	}
	
	@Override
	public Unit findUnitByCode(final UserContext userContext,
			final String unitCode) throws BusinessException
	{
		return this.unitDAO.findByCode(unitCode, LifeCycleStatus.ACTIVE);
	}
}
