/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.geekframeworks.core.framework.fixturefactory.templates.UnitTemplate;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.unit.dao.UnitDAO;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.unit.service.UnitService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;

/**
 * @author Stélio Moiane
 *		
 */
public class UnitServiceTest extends AbstractSpringContextTests
{
	@Inject
	private UnitService unitService;
	
	@Inject
	private UnitDAO unitDAO;
	
	private Unit unit;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.unit = EntityFactory.gimme(Unit.class, UnitTemplate.VALID);
	}
	
	@Test
	public void shouldGenerateUnitCode() throws BusinessException
	{
		String unitCode = this.unitService
				.generateUnitCode(this.getUserContext());
				
		assertEquals("AMU000002", unitCode);
	}
	
	@Test
	public void shouldCreateUnit() throws BusinessException
	{
		this.unitService.createUnit(this.getUserContext(), this.unit);
		
		TestUtil.assertCreation(this.unit);
	}
	
	@Test
	public void shouldUpdateUnit() throws BusinessException
	{
		this.unitService.createUnit(this.getUserContext(), this.unit);
		this.unit.setName("DIFFERENT");
		
		this.unitService.updateUnit(this.getUserContext(), this.unit);
		
		TestUtil.assertUpdate(this.unit);
	}
	
	@Test
	public void shouldDeleteUnit() throws BusinessException
	{
		this.unitService.createUnit(this.getUserContext(), this.unit);
		this.unitService.deleteUnit(this.getUserContext(), this.unit);
		
		assertNull(this.unitDAO.findById(this.unit.getId()));
	}
}
