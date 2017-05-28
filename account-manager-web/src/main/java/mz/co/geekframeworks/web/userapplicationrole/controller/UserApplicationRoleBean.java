/**
 *
 */
package mz.co.geekframeworks.web.userapplicationrole.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationQueryService;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleQueryService;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.unit.service.UnitQueryService;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.service.UserQueryService;
import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.geekframeworks.core.userapplicationrole.service.UserApplicationRoleService;
import mz.co.geekframeworks.web.controller.AbstractBean;
import mz.co.geekframeworks.web.util.PageMessages;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.exception.DataBaseException;

/**
 * @author Stélio Moiane
 * 
 */
@ManagedBean
@ViewScoped
public class UserApplicationRoleBean extends AbstractBean
{
	private UserApplicationRole userApplicationRole;
	
	private Application application;
	
	@ManagedProperty(value = "#{" + UserQueryService.NAME + "}")
	private UserQueryService userQueryService;
	
	@ManagedProperty(value = "#{" + ApplicationQueryService.NAME + "}")
	private ApplicationQueryService applicationQueryService;
	
	@ManagedProperty(value = "#{" + ApplicationRoleQueryService.NAME + "}")
	private ApplicationRoleQueryService applicationRoleQueryService;
	
	@ManagedProperty(value = "#{" + UserApplicationRoleService.NAME + "}")
	private UserApplicationRoleService userApplicationRoleService;
	
	@ManagedProperty(value = "#{" + UnitQueryService.NAME + "}")
	private UnitQueryService unitQueryService;
	
	@Override
	public void init()
	{
		this.cleanUserApplicationRole();
	}
	
	public Collection<User> getUsers() throws BusinessException
	{
		return this.userQueryService.findAllUsers(this.getUserContext());
	}
	
	public Collection<Application> getApplications() throws BusinessException
	{
		return this.applicationQueryService.findApplications(this
				.getUserContext());
	}
	
	public Collection<ApplicationRole> getApplicationRoles()
			throws BusinessException
	{
		return this.applicationRoleQueryService
				.fetchAppliactionRolesByApplication(this.getUserContext(),
						this.application);
	}
	
	public boolean isEdit()
	{
		return this.userApplicationRole.getId() != null ? true : false;
	}
	
	public void linkUserToApplication()
	{
		try
		{
			this.userApplicationRoleService.createUserApplicationRole(
					this.getUserContext(), this.userApplicationRole);
			
			PageMessages.addInfoMessage(this
					.getLabel("userapplicationroles.created.success"));
			
			this.cleanUserApplicationRole();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
		catch (DataBaseException e)
		{
			PageMessages.addErrorMessage(this
					.getLabel("userapplicationroles.constraints.violation"));
		}
	}
	
	public void cleanUserApplicationRole()
	{
		this.userApplicationRole = new UserApplicationRole();
		this.userApplicationRole.setUnits(new HashSet<>());
		this.application = null;
	}
	
	public List<Unit> getUnits() throws BusinessException
	{
		return this.unitQueryService.fetchUnitsByApplication(
				this.getUserContext(), this.application);
	}
	
	public void setUserApplicationRole(
			final UserApplicationRole userApplicationRole)
	{
		this.userApplicationRole = userApplicationRole;
	}
	
	public UserApplicationRole getUserApplicationRole()
	{
		return this.userApplicationRole;
	}
	
	public Application getApplication()
	{
		return this.application;
	}
	
	public void setApplication(final Application application)
	{
		this.application = application;
	}
	
	public void setUserQueryService(final UserQueryService userQueryService)
	{
		this.userQueryService = userQueryService;
	}
	
	public void setApplicationQueryService(
			final ApplicationQueryService applicationQueryService)
	{
		this.applicationQueryService = applicationQueryService;
	}
	
	public void setApplicationRoleQueryService(
			final ApplicationRoleQueryService applicationRoleQueryService)
	{
		this.applicationRoleQueryService = applicationRoleQueryService;
	}
	
	public void setUserApplicationRoleService(
			final UserApplicationRoleService userApplicationRoleService)
	{
		this.userApplicationRoleService = userApplicationRoleService;
	}
	
	public void setUnitQueryService(final UnitQueryService unitQueryService)
	{
		this.unitQueryService = unitQueryService;
	}
}
