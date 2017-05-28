/**
 * 
 */
package mz.co.geekframeworks.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Este controller gere a transição de uma página para outra
 * 
 * @author Stélio Moiane
 * 
 */
@ManagedBean
@ViewScoped
public class PageBean
{
	private String page = "home";
	
	public String getPage()
	{
		return this.page;
	}
	
	public void setPage(final String page)
	{
		this.page = page;
	}
}
