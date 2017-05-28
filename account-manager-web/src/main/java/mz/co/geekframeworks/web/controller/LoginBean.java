package mz.co.geekframeworks.web.controller;

/**
 *
 */

import javax.faces.bean.ManagedBean;

import mz.co.geekframeworks.web.util.PageMessages;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 */
@ManagedBean
public class LoginBean extends AbstractBean
{
	private String userName;
	
	private String password;
	
	public String login() throws BusinessException
	{
		try
		{
			this.getAuthenticationService().login(this.userName, this.password);
		}
		catch (BusinessException ex)
		{
			PageMessages.addErrorMessage(ex.getMessage());
			return "/login";
		}
		
		return "views/choose-application?faces-redirect=true";
	}
	
	public String logout()
	{
		this.getAuthenticationService().logout();
		
		return "/login?faces-redirect=true";
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(final String userName)
	{
		this.userName = userName;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(final String password)
	{
		this.password = password;
	}
}