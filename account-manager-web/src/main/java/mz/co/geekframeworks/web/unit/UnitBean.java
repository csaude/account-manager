/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.web.unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.unit.service.UnitQueryService;
import mz.co.geekframeworks.core.unit.service.UnitService;
import mz.co.geekframeworks.web.controller.AbstractBean;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.jsf.util.Labels;
import mz.co.mozview.frameworks.jsf.util.PageMessages;

/**
 * @author Stélio Moiane
 *
 */
@ManagedBean
@ViewScoped
public class UnitBean extends AbstractBean
{
	@ManagedProperty(value = "#{" + UnitService.NAME + "}")
	private UnitService unitService;
	
	@ManagedProperty(value = "#{" + UnitQueryService.NAME + "}")
	private UnitQueryService unitQueryService;
	
	private Unit unit;
	
	private List<Unit> units;
	
	private Application application;
	
	@Override
	public void init()
	{
		this.cleanUnit();
	}
	
	public void saveUnit()
	{
		try
		{
			this.unitService.createUnit(this.getUserContext(), this.unit);
			
			PageMessages.addInfoMessage(Labels.getLabel(
					"units.created.success", this.unit.getCode()));
			
			this.cleanUnit();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void updateUnit()
	{
		try
		{
			this.unitService.updateUnit(this.getUserContext(), this.unit);
			
			PageMessages.addInfoMessage(Labels.getLabel(
					"units.updated.success", this.unit.getCode()));
			
			this.cleanUnit();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void removeUnit(final Unit unit)
	{
		try
		{
			this.unitService.deleteUnit(this.getUserContext(), unit);
			
			PageMessages.addInfoMessage(Labels.getLabel(
					"units.removed.success", unit.getCode()));
			
			this.cleanUnit();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void searchByApplication() throws BusinessException
	{
		this.units = this.unitQueryService.fetchUnitsByApplication(
				this.getUserContext(), this.application);
	}
	
	public void cleanUnit()
	{
		this.unit = new Unit();
		this.unit.setApplications(new HashSet<>());
		this.units = new ArrayList<>();
		this.application = null;
	}
	
	public boolean isEdit()
	{
		return this.unit.getId() != null ? true : false;
	}
	
	public Unit getUnit()
	{
		return this.unit;
	}
	
	public void setUnit(final Unit unit)
	{
		this.unit = unit;
	}
	
	public void setUnitService(final UnitService unitService)
	{
		this.unitService = unitService;
	}
	
	public void setUnitQueryService(final UnitQueryService unitQueryService)
	{
		this.unitQueryService = unitQueryService;
	}
	
	public List<Unit> getUnits()
	{
		return this.units;
	}
	
	public Application getApplication()
	{
		return this.application;
	}
	
	public void setApplication(final Application application)
	{
		this.application = application;
	}
}
