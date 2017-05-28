/**
 *
 */
package mz.co.geekframeworks.web.applicationrole.contoller;

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
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleService;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleQueryService;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.geekframeworks.core.transaction.service.TransactionQueryService;
import mz.co.geekframeworks.web.controller.AbstractBean;
import mz.co.geekframeworks.web.util.PageMessages;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.exception.DataBaseException;

/**
 * @author St�lio Moiane
 *
 */
@ManagedBean
@ViewScoped
public class ApplicationRoleBean extends AbstractBean
{
	private Application application;
	
	private Role role;
	
	private ApplicationRole applicationRole;
	
	@ManagedProperty(value = "#{" + ApplicationRoleService.NAME + "}")
	private ApplicationRoleService applicationRoleService;
	
	@ManagedProperty(value = "#{" + ApplicationQueryService.NAME + "}")
	private ApplicationQueryService applicationQueryService;
	
	@ManagedProperty(value = "#{" + RoleQueryService.NAME + "}")
	private RoleQueryService roleQueryService;
	
	@ManagedProperty(value = "#{" + ApplicationRoleQueryService.NAME + "}")
	private ApplicationRoleQueryService applicationRoleQueryService;
	
	@ManagedProperty(value = "#{" + TransactionQueryService.NAME + "}")
	private TransactionQueryService transactionQueryService;
	
	@Override
	public void init()
	{
		this.cleanApplicationRole();
	}
	
	public void saveApplicationRole()
	{
		try
		{
			this.applicationRoleService.createApplicationRole(
					this.getUserContext(), this.application, this.role);
			
			PageMessages.addInfoMessage(this
					.getLabel("applicationroles.linked.success"));
			
			this.cleanApplicationRole();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
		catch (DataBaseException e)
		{
			PageMessages.addErrorMessage(this
					.getLabel("applicationroles.constraints.violation"));
		}
	}
	
	public void cleanApplicationRole()
	{
		this.application = null;
		this.role = null;
		this.applicationRole = new ApplicationRole();
		this.applicationRole.setTransactions(new HashSet<>());
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
		return this.applicationRole.getId() != null ? true : false;
	}
	
	public Collection<ApplicationRole> getApplicationRoles()
			throws BusinessException
			{
		return this.getApplicationRoleQueryService().fetchAllApplicationRoles(
				this.getUserContext());
			}
	
	public void updateApplicationRole()
	{
		try
		{
			this.applicationRole.setApplication(this.application);
			this.applicationRole.setRole(this.role);
			
			this.applicationRoleService.updateApplicationRole(
					this.getUserContext(), this.applicationRole);
			
			PageMessages.addInfoMessage(this
					.getLabel("applicationroles.updated.success"));
			
			this.cleanApplicationRole();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
		catch (DataBaseException e)
		{
			PageMessages.addErrorMessage(this
					.getLabel("applicationroles.constraints.violation"));
		}
		
	}
	
	public void removeApplicationRole(final ApplicationRole applicationRole)
	{
		try
		{
			this.applicationRoleService.deleteApplicationRole(
					this.getUserContext(), applicationRole);
			PageMessages.addInfoMessage(this
					.getLabel("applicationroles.removed.success"));
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public List<Transaction> getTransactions() throws BusinessException
	{
		return this.transactionQueryService.fetchTransactionsByApplication(
				this.getUserContext(), this.application);
	}
	
	public Application getApplication()
	{
		return this.application;
	}
	
	public void setApplication(final Application application)
	{
		this.application = application;
	}
	
	public Role getRole()
	{
		return this.role;
	}
	
	public void setRole(final Role role)
	{
		this.role = role;
	}
	
	public void setApplicationRole(final ApplicationRole applicationRole)
	{
		this.applicationRole = applicationRole;
		this.application = applicationRole.getApplication();
		this.role = applicationRole.getRole();
	}
	
	public ApplicationRole getApplicationRole()
	{
		return this.applicationRole;
	}
	
	public ApplicationRoleService getApplicationRoleService()
	{
		return this.applicationRoleService;
	}
	
	public void setApplicationRoleService(
			final ApplicationRoleService applicationRoleService)
	{
		this.applicationRoleService = applicationRoleService;
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
	
	public ApplicationRoleQueryService getApplicationRoleQueryService()
	{
		return this.applicationRoleQueryService;
	}
	
	public void setApplicationRoleQueryService(
			final ApplicationRoleQueryService applicationRoleQueryService)
	{
		this.applicationRoleQueryService = applicationRoleQueryService;
	}
	
	public void setTransactionQueryService(
			final TransactionQueryService transactionQueryService)
	{
		this.transactionQueryService = transactionQueryService;
	}
}