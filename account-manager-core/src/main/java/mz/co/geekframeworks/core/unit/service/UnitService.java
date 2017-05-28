/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.service;

import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public interface UnitService
{
	String NAME = "mz_co_geekframeworks_core_unit_service_UnitService";
	
	public String generateUnitCode(final UserContext userContext)
			throws BusinessException;
	
	public Unit createUnit(final UserContext userContext, final Unit unit)
			throws BusinessException;
	
	public Unit updateUnit(final UserContext userContext, final Unit unit)
			throws BusinessException;
	
	public void deleteUnit(final UserContext userContext, final Unit unit)
			throws BusinessException;
}
