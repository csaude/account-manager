/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.web.unit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.unit.service.UnitQueryService;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 */
@ManagedBean
public class UnitConverter implements Converter
{
	@ManagedProperty(value = "#{" + UnitQueryService.NAME + "}")
	private UnitQueryService unitQueryService;
	
	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value)
	{
		
		if ((value != null) && (value.trim().length() > 0))
		{
			try
			{
				return this.unitQueryService.findById(Long.valueOf(value));
			}
			catch (NumberFormatException | BusinessException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@Override
	public String getAsString(final FacesContext context,
			final UIComponent component, final Object value)
	{
		return value != null ? ((Unit) value).getId().toString() : "";
	}
	
	public void setUnitQueryService(final UnitQueryService unitQueryService)
	{
		this.unitQueryService = unitQueryService;
	}
}
