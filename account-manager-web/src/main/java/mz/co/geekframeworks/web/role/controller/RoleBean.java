package mz.co.geekframeworks.web.role.controller;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationQueryService;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleQueryService;
import mz.co.geekframeworks.core.role.service.RoleService;
import mz.co.geekframeworks.web.controller.AbstractBean;
import mz.co.geekframeworks.web.util.PageMessages;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * Controller para as gerir as ac��es de cria��o, edi��o, listagem e remo��o de
 * Roles.
 * 
 * @author Eudson Bambo
 *
 */
@ManagedBean
@ViewScoped
public class RoleBean extends AbstractBean
{
	private Role role;
	
	@ManagedProperty(value = "#{" + RoleService.NAME + "}")
	private RoleService roleService;
	
	@ManagedProperty(value = "#{" + ApplicationQueryService.NAME + "}")
	private ApplicationQueryService applicationQueryService;
	
	@ManagedProperty(value = "#{" + RoleQueryService.NAME + "}")
	private RoleQueryService roleQueryService;
	
	@Override
	public void init()
	{
		this.cleanRole();
	}
	
	public void saveRole()
	{
		try
		{
			this.roleService.createRole(this.getUserContext(), this.role);
			
			PageMessages.addInfoMessage(this.getLabel("roles.create.success"));
			
			this.cleanRole();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void removeRole(final Role role)
	{
		try
		{
			this.roleService.deleteRole(this.getUserContext(), role);
			PageMessages.addInfoMessage(this.getLabel("roles.remove.success"));
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void updateRole()
	{
		try
		{
			this.roleService.updateRole(this.getUserContext(), this.role);
			PageMessages.addInfoMessage(this.getLabel("roles.update.succes"));
			this.cleanRole();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public Collection<Application> getApplications() throws BusinessException
	{
		return this.applicationQueryService.findApplications(this
				.getUserContext());
	}
	
	public Collection<Role> getRoles() throws BusinessException
	{
		return this.roleQueryService.findRoles(this.getUserContext());
	}
	
	public boolean isEdit()
	{
		return this.role.getId() != null;
	}
	
	public Role getRole()
	{
		return this.role;
	}
	
	public void setRole(final Role role)
	{
		this.role = role;
	}
	
	public RoleService getRoleService()
	{
		return this.roleService;
	}
	
	public void setRoleService(final RoleService roleService)
	{
		this.roleService = roleService;
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
	
	public RoleQueryService getRoleQueryService()
	{
		return this.roleQueryService;
	}
	
	public void setRoleQueryService(final RoleQueryService roleQueryService)
	{
		this.roleQueryService = roleQueryService;
	}
	
	private void cleanRole()
	{
		this.role = new Role();
	}
}