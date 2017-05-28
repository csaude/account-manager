package mz.co.geekframeworks.web.application.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationQueryService;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 */
@ManagedBean
public class ApplicationConverter implements Converter
{
	@ManagedProperty(value = "#{" + ApplicationQueryService.NAME + "}")
	private ApplicationQueryService applicationQueryService;
	
	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value)
	{
		
		if ((value != null) && (value.trim().length() > 0))
		{
			try
			{
				return this.applicationQueryService.findById(Long
						.valueOf(value));
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
		return value != null ? ((Application) value).getId().toString() : "";
	}
	
	public ApplicationQueryService getApplicationQueryService()
	{
		return this.applicationQueryService;
	}
	
	public void setApplicationQueryService(
			final ApplicationQueryService applicationQueryService)
	{
		this.applicationQueryService = applicationQueryService;
	}
}
