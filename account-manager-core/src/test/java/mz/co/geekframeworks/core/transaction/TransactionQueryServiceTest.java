/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.framework.fixturefactory.templates.TransactionTemplate;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.geekframeworks.core.transaction.service.TransactionQueryService;
import mz.co.geekframeworks.core.transaction.service.TransactionService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Test;

/**
 * @author Stélio Moiane
 *
 */
public class TransactionQueryServiceTest extends AbstractSpringContextTests
{
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private TransactionService transactionService;
	
	@Inject
	private TransactionQueryService transactionQueryService;
	
	private Transaction transaction;
	
	private Application application;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.transaction = EntityFactory.gimme(Transaction.class,
				TransactionTemplate.VALID);
		
		this.application = this.applicationService.createApplication(
				this.getUserContext(), this.transaction.getApplication());
		
		this.transactionService.createTransaction(this.getUserContext(),
				this.transaction);
	}
	
	@Test
	public void shouldFindAllTransactions() throws BusinessException
	{
		List<Transaction> transactions = this.transactionQueryService
				.findAllTransactions(this.getUserContext());
		
		assertFalse(transactions.isEmpty());
		assertEquals(1, transactions.size());
	}
	
	@Test
	public void shouldFetchTransactionsByApplication() throws BusinessException
	{
		List<Transaction> transactions = this.transactionQueryService
				.fetchTransactionsByApplication(this.getUserContext(),
						this.application);
		
		assertFalse(transactions.isEmpty());
		assertEquals(1, transactions.size());
	}
	
}
