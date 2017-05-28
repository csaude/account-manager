/**
 * 
 */
package mz.co.geekframeworks.web.controller;

import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mz.co.geekframeworks.core.security.AuthenticationService;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stelio Moiane
 * 
 */
public abstract class AbstractBean
{
	@ManagedProperty(value = "#{" + AuthenticationService.NAME + "}")
	private AuthenticationService authenticationService;
	
	@PostConstruct
	public void init()
	{
	}
	
	// pega a label atraves do ficheiro de internacionalização
	public String getLabel(final String label)
	{
		// Pega o contexto
		FacesContext context = FacesContext.getCurrentInstance();
		
		// Pega o bundle
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "labels");
		
		// Pega a mensagem de acordo com a chave
		return bundle.getString(label);
	}
	
	public ExternalContext getFacesExternalContext()
	{
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public Map<String, Object> getSession()
	{
		return this.getFacesExternalContext().getSessionMap();
	}
	
	public UserContext getUserContext()
	{
		return (UserContext) this.getSession().get("userContext");
	}
	
	public AuthenticationService getAuthenticationService()
	{
		return this.authenticationService;
	}
	
	public void setAuthenticationService(
			final AuthenticationService authenticationService)
	{
		this.authenticationService = authenticationService;
	}
	
	public User getAuthenticate()
	{
		return this.authenticationService.getAuthenticated();
	}
}
