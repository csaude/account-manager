/**
 * 
 */
package mz.co.geekframeworks.web.applicationrole.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleQueryService;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author St�lio Moiane
 * 
 */
@ManagedBean
public class ApplicationRoleConverter implements Converter
{
	@ManagedProperty(value = "#{" + ApplicationRoleQueryService.NAME + "}")
	private ApplicationRoleQueryService applicationRoleQueryService;
	
	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value)
	{
		
		if ((value != null) && (value.trim().length() > 0))
		{
			try
			{
				return this.getApplicationRoleQueryService().findById(
						Long.valueOf(value));
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
		return value != null ? ((ApplicationRole) value).getId().toString()
				: "";
	}
	
	public ApplicationRoleQueryService getApplicationRoleQueryService()
	{
		return this.applicationRoleQueryService;
	}
	
	public void setApplicationRoleQueryService(
			final ApplicationRoleQueryService applicationRoleQueryService)
	{
		this.applicationRoleQueryService = applicationRoleQueryService;
	}
}
