package mz.co.geekframeworks.web.user.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.service.UserQueryService;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author St�lio Moiane
 * 
 */
@ManagedBean
public class UserConverter implements Converter
{
	@ManagedProperty(value = "#{" + UserQueryService.NAME + "}")
	private UserQueryService userQueryService;
	
	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value)
	{
		if ((value != null) && (value.trim().length() > 0))
		{
			try
			{
				return this.userQueryService.findById(Long.valueOf(value));
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
		return value != null ? ((User) value).getId().toString() : "";
	}
	
	public UserQueryService getUserQueryService()
	{
		return this.userQueryService;
	}
	
	public void setUserQueryService(final UserQueryService userQueryService)
	{
		this.userQueryService = userQueryService;
	}
}