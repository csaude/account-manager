/**
 *
 */
package mz.co.geekframeworks.web.user.controller;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.service.UserQueryService;
import mz.co.geekframeworks.core.user.service.UserService;
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
public class UserBean extends AbstractBean
{
	private User user;
	
	@ManagedProperty(value = "#{" + UserService.NAME + "}")
	private UserService userService;
	
	@ManagedProperty(value = "#{" + UserQueryService.NAME + "}")
	private UserQueryService userQueryService;
	
	@Override
	public void init()
	{
		this.cleanUser();
	}
	
	public void saveUser()
	{
		try
		{
			this.userService.createUser(this.getUserContext(), this.user);
			PageMessages.addInfoMessage(this.getLabel("users.created.success"));
			this.cleanUser();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		catch (DataBaseException e)
		{
			PageMessages.addErrorMessage(this
					.getLabel("users.constraints.violation"));
		}
	}
	
	public void updateUser()
	{
		try
		{
			this.userService.updateUser(this.getUserContext(), this.user);
			PageMessages.addInfoMessage(this.getLabel("users.updated.success"));
			this.cleanUser();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
		catch (DataBaseException e)
		{
			PageMessages.addErrorMessage(this
					.getLabel("users.constraints.violation"));
		}
	}
	
	public void removeUser(final User user)
	{
		try
		{
			this.userService.deleteUser(this.getUserContext(), user);
			PageMessages.addInfoMessage(this.getLabel("users.removed.success"));
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public Collection<User> getUsers() throws BusinessException
	{
		return this.getUserQueryService().findAllUsers(this.getUserContext());
	}
	
	public boolean isEdit()
	{
		return this.user.getId() != null ? true : false;
	}
	
	public void cleanUser()
	{
		this.user = new User();
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(final User user)
	{
		this.user = user;
	}
	
	public UserService getUserService()
	{
		return this.userService;
	}
	
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
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
