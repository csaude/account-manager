/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.framework.fixturefactory.templates.UnitTemplate;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.unit.service.UnitQueryService;
import mz.co.geekframeworks.core.unit.service.UnitService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Test;

/**
 * @author Stélio Moiane
 *
 */
public class UnitQueryServiceTest extends AbstractSpringContextTests
{
	@Inject
	private UnitService unitService;
	
	@Inject
	private UnitQueryService unitQueryService;
	
	@Inject
	private ApplicationService applicationService;
	
	private Unit unit;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.unit = EntityFactory.gimme(Unit.class, UnitTemplate.VALID);
	}
	
	@Test
	public void shouldFindAllUnits() throws BusinessException
	{
		this.unitService.createUnit(this.getUserContext(), this.unit);
		
		List<Unit> units = this.unitQueryService.findAllUnits(this
				.getUserContext());
		
		assertFalse(units.isEmpty());
		assertEquals(1, units.size());
	}
	
	@Test
	public void shouldFetchUnitsByApplication() throws BusinessException
	{
		Application application = EntityFactory.gimme(Application.class,
				"valid");
		this.applicationService.createApplication(this.getUserContext(),
				application);
		this.unit.setApplications(new HashSet<>(Arrays.asList(application)));
		
		this.unitService.createUnit(this.getUserContext(), this.unit);
		
		List<Unit> units = this.unitQueryService.fetchUnitsByApplication(
				this.getUserContext(), application);
		
		assertFalse(units.isEmpty());
		assertEquals(1, units.size());
		assertEquals(1, units.get(0).getApplications().size());
	}
	
	@Test
	public void shouldFindUnitByCode() throws BusinessException
	{
		this.unitService.createUnit(this.getUserContext(), this.unit);
		
		Unit unit = this.unitQueryService.findUnitByCode(this.getUserContext(),
				this.unit.getCode());
		
		assertNotNull(unit);
		assertEquals(this.unit.getCode(), unit.getCode());
	}
}
