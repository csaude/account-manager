/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.web.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.geekframeworks.core.transaction.service.TransactionQueryService;
import mz.co.geekframeworks.core.transaction.service.TransactionService;
import mz.co.geekframeworks.web.controller.AbstractBean;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.jsf.util.Labels;
import mz.co.mozview.frameworks.jsf.util.PageMessages;

/**
 * @author Stélio Moiane
 *
 */
@ManagedBean
@ViewScoped
public class TransactionBean extends AbstractBean
{
	@ManagedProperty(value = "#{" + TransactionService.NAME + "}")
	private TransactionService transactionService;
	
	@ManagedProperty(value = "#{" + TransactionQueryService.NAME + "}")
	private TransactionQueryService transactionQueryService;
	
	private Transaction transaction;
	
	private List<Transaction> transactions;
	
	@Override
	public void init()
	{
		this.cleanTransaction();
	}
	
	public void saveTransaction()
	{
		try
		{
			this.transactionService.createTransaction(this.getUserContext(),
					this.transaction);
			
			PageMessages.addInfoMessage(this
					.getLabel("transactions.created.success"));
			
			this.cleanTransaction();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void updateTransaction()
	{
		try
		{
			this.transactionService.updateTransaction(this.getUserContext(),
					this.transaction);
			
			PageMessages.addInfoMessage(this
					.getLabel("transactions.updated.success"));
			
			this.cleanTransaction();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void removeTransaction(final Transaction transaction)
	{
		try
		{
			this.transactionService.removeTransaction(this.getUserContext(),
					transaction);
			
			PageMessages.addInfoMessage(Labels
					.getLabel("transactions.removed.success"));
			
			this.cleanTransaction();
		}
		catch (BusinessException e)
		{
			PageMessages.addErrorMessage(e.getMessage());
		}
	}
	
	public void searchByApplication() throws BusinessException
	{
		this.transactions = this.transactionQueryService
				.fetchTransactionsByApplication(this.getUserContext(),
						this.transaction.getApplication());
	}
	
	public boolean isEdit()
	{
		return this.transaction.getId() != null ? true : false;
	}
	
	public void cleanTransaction()
	{
		this.transaction = new Transaction();
		this.transactions = new ArrayList<>();
	}
	
	public void setTransaction(final Transaction transaction)
	{
		this.transaction = transaction;
	}
	
	public Transaction getTransaction()
	{
		return this.transaction;
	}
	
	public void setTransactionService(
			final TransactionService transactionService)
	{
		this.transactionService = transactionService;
	}
	
	public List<Transaction> getTransactions()
	{
		return this.transactions;
	}
	
	public void setTransactionQueryService(
			final TransactionQueryService transactionQueryService)
	{
		this.transactionQueryService = transactionQueryService;
	}
}
