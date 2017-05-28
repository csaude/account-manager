package mz.co.geekframeworks.web.role.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleQueryService;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 * 
 */
@ManagedBean
public class RoleConverter implements Converter
{
	
	@ManagedProperty(value = "#{" + RoleQueryService.NAME + "}")
	private RoleQueryService roleQueryService;
	
	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value)
	{
		if ((value != null) && (value.trim().length() > 0))
		{
			try
			{
				return this.getRoleQueryService().findById(Long.valueOf(value));
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
		return value != null ? ((Role) value).getId().toString() : "";
	}
	
	public RoleQueryService getRoleQueryService()
	{
		return this.roleQueryService;
	}
	
	public void setRoleQueryService(final RoleQueryService roleQueryService)
	{
		this.roleQueryService = roleQueryService;
	}
	
}
