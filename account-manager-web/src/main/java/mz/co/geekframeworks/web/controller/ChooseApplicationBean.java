package mz.co.geekframeworks.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.model.UserContextFactory;
import mz.co.geekframeworks.core.user.service.UserQueryService;
import mz.co.geekframeworks.core.user.service.UserService;
import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Eudson Bambo
 * @author Stélio Moiane
 *
 */
@ManagedBean
public class ChooseApplicationBean extends AbstractBean
{
	@ManagedProperty(value = "#{" + UserQueryService.NAME + "}")
	private UserQueryService userQueryService;
	
	@ManagedProperty(value = "#{" + UserService.NAME + "}")
	private UserService userService;
	
	private Application application;
	
	private Collection<Application> applications;
	
	private final String DISPATCHER = "/views/dispatcher";
	
	private final String DEFAULT_APPLICATION_CODE = "01";
	
	@Override
	public void init()
	{
		try
		{
			this.getUserApplications();
		}
		catch (BusinessException e)
		{
		}
	}
	
	private void getUserApplications() throws BusinessException
	{
		this.setApplications(new ArrayList<Application>());
		
		for (UserApplicationRole userApplicationRole : this.getAuthenticate()
				.getAuthorities())
		{
			this.applications.add(userApplicationRole.getApplicationRole()
					.getApplication());
		}
	}
	
	public String continueToApplication() throws BusinessException
	{
		this.getSession().put("url", this.application.getContextName());
		
		this.getSession().put(
				"userContext",
				UserContextFactory.getUsercoContext(this.getAuthenticate(),
						this.application));
		
		this.getSession()
		.put("sessionId", this.getUserContext().getSessionId());
		
		User authenticate = this.getAuthenticate();
		authenticate.setSessionId(this.getUserContext().getSessionId());
		this.userService.updateUser(this.getUserContext(), authenticate);
		
		if (this.DEFAULT_APPLICATION_CODE.equals(this.application.getCode()))
		{
			return this.DISPATCHER;
		}
		
		this.getAuthenticationService().logout();
		
		return this.DISPATCHER;
	}
	
	public Application getApplication()
	{
		return this.application;
	}
	
	public void setApplication(final Application application)
	{
		this.application = application;
	}
	
	public Collection<Application> getApplications()
	{
		return this.applications;
	}
	
	public void setApplications(final Collection<Application> applications)
	{
		this.applications = applications;
	}
	
	public void setUserQueryService(final UserQueryService userQueryService)
	{
		this.userQueryService = userQueryService;
	}
	
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
