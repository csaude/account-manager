/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.web.transaction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.geekframeworks.core.transaction.service.TransactionQueryService;
import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 */
@ManagedBean
public class TransactionConverter implements Converter
{
	@ManagedProperty(value = "#{" + TransactionQueryService.NAME + "}")
	private TransactionQueryService transactionQueryService;
	
	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value)
	{
		
		if ((value != null) && (value.trim().length() > 0))
		{
			try
			{
				return this.transactionQueryService.findTransactionById(Long
						.valueOf(value));
			}
			catch (NumberFormatException | BusinessException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@Override
	public String getAsString(final FacesContext context,
			final UIComponent component, final Object value)
	{
		return value != null ? ((Transaction) value).getId().toString() : "";
	}
	
	public void setTransactionQueryService(
			final TransactionQueryService transactionQueryService)
	{
		this.transactionQueryService = transactionQueryService;
	}
}
