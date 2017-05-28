package mz.co.geekframeworks.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Utilitário para impressão de mensagens na tela por via dos beans.
 * 
 * @author Stélio Késio Adriano Moiane
 * @author Eudson Bambo
 * 
 */
public abstract class PageMessages 
{
	private static void addMessage(final Severity facesMessage,
			final String message) 
	{
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(facesMessage, message, ""));
	}

	public static void addInfoMessage(final String message) 
	{
		addMessage(FacesMessage.SEVERITY_INFO, message);
	}
	
	public static void addErrorMessage(final String message)
	{
		addMessage(FacesMessage.SEVERITY_ERROR, message);
	}
	
	public static void addWarningMessage(final String message)
	{
		addMessage(FacesMessage.SEVERITY_WARN, message);
	}
}
