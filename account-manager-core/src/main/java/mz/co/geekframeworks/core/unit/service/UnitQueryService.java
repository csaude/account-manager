/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.service;

import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public interface UnitQueryService
{
	String NAME = "mz_co_geekframeworks_core_unit_service_UnitQueryService";
	
	public List<Unit> findAllUnits(final UserContext userContext)
			throws BusinessException;
	
	public List<Unit> fetchUnitsByApplication(final UserContext userContext,
			final Application application) throws BusinessException;
	
	public Unit findById(Long unitId) throws BusinessException;
	
	public Unit findUnitByCode(final UserContext userContext,
			final String unitCode) throws BusinessException;
}
