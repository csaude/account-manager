/**
 * 
 */
package mz.co.geekframeworks.web.application.controller;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationQueryService;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.web.controller.AbstractBean;
import mz.co.geekframeworks.web.util.PageMessages;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 * 
 */
@ManagedBean
@ViewScoped
public class ApplicationBean extends AbstractBean
{
	private Application application;
	
	@ManagedProperty(value = "#{" + ApplicationService.NAME + "}")
	private ApplicationService applicationService;
	
	@ManagedProperty(value = "#{" + ApplicationQueryService.NAME + "}")
	private ApplicationQueryService applicationQueryService;
	
	@Override
	public void init()
	{
		this.cleanApplication();
	}
	
	public void saveApplication()
	{
		try
		{
			this.applicationService.createApplication(this.getUserContext(),
					this.application);
			
			PageMessages.addInfoMessage(this
					.getLabel("applications.create.success"));
			
			this.cleanApplication();
			
		}
		catch (BusinessException e)
		{
			
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void removeApplication(final Application application)
	{
		try
		{
			this.applicationService.deleteApplication(this.getUserContext(),
					application);
			
			PageMessages.addInfoMessage(this
					.getLabel("applications.remove.success"));
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void updateApplication()
	{
		try
		{
			this.applicationService.updateApplication(this.getUserContext(),
					this.application);
			PageMessages.addInfoMessage(this
					.getLabel("applications.update.success"));
			
			this.cleanApplication();
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
	
	public boolean isEdit()
	{
		return this.application.getId() != null ? true : false;
	}
	
	public void cleanApplication()
	{
		this.application = new Application();
	}
	
	public Application getApplication()
	{
		return this.application;
	}
	
	public void setApplication(final Application application)
	{
		this.application = application;
	}
	
	public ApplicationService getApplicationService()
	{
		return this.applicationService;
	}
	
	public void setApplicationService(
			final ApplicationService applicationService)
	{
		this.applicationService = applicationService;
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
