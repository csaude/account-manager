/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction;

import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.framework.fixturefactory.templates.TransactionTemplate;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.transaction.dao.TransactionDAO;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.geekframeworks.core.transaction.service.TransactionService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;

import org.junit.Test;

/**
 * @author Stélio Moiane
 *
 */
public class TransactionServiceTest extends AbstractSpringContextTests
{
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private TransactionService transactionService;
	
	@Inject
	private TransactionDAO transactionDAO;
	
	private Transaction transaction;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.transaction = EntityFactory.gimme(Transaction.class,
				TransactionTemplate.VALID);
		
		this.applicationService.createApplication(this.getUserContext(),
				this.transaction.getApplication());
		
		this.transactionService.createTransaction(this.getUserContext(),
				this.transaction);
	}
	
	@Test
	public void shouldCreateTransaction() throws BusinessException
	{
		TestUtil.assertCreation(this.transaction);
	}
	
	@Test
	public void shouldUpdateTransaction() throws BusinessException
	{
		this.transactionService.updateTransaction(this.getUserContext(),
				this.transaction);
		
		TestUtil.assertUpdate(this.transaction);
	}
	
	@Test
	public void shouldDeleteTransaction() throws BusinessException
	{
		this.transactionService.removeTransaction(this.getUserContext(),
				this.transaction);
		
		assertNull(this.transactionDAO.findById(this.transaction.getId()));
	}
	
}
