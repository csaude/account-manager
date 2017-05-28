/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.service;

import static mz.co.geekframeworks.core.util.CodeParams.UNIT;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.unit.dao.UnitDAO;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
@Service(UnitService.NAME)
public class UnitServiceImpl extends AbstractService implements UnitService {
	@Inject
	private UnitDAO unitDAO;

	@Override
	public String generateUnitCode(final UserContext userContext) throws BusinessException {
		return this.unitDAO.generateCode(UNIT.getPrefix(), UNIT.getLenght(), UNIT.getCompleteValue());
	}

	@Override
	public Unit createUnit(final UserContext userContext, final Unit unit) throws BusinessException {
		unit.setCode(this.generateUnitCode(userContext));
		return this.unitDAO.create(userContext.getUuid(), unit);
	}

	@Override
	public Unit updateUnit(final UserContext userContext, final Unit unit) throws BusinessException {
		return this.unitDAO.update(userContext.getUuid(), unit);
	}

	@Override
	public void deleteUnit(final UserContext userContext, final Unit unit) throws BusinessException {
		this.unitDAO.delete(userContext.getUuid(), unit);
	}
}
