/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.transaction.dao.TransactionDAO;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.stereotype.Service;

/**
 * @author Stélio Moiane
 *
 */
@Service(TransactionQueryService.NAME)
public class TransactionQueryServiceImpl implements TransactionQueryService
{
	@Inject
	private TransactionDAO transactionDAO;
	
	@Override
	public List<Transaction> findAllTransactions(final UserContext userContext)
			throws BusinessException
			{
		return this.transactionDAO.findAll(LifeCycleStatus.ACTIVE);
			}
	
	@Override
	public List<Transaction> fetchTransactionsByApplication(
			final UserContext userContext, final Application application)
					throws BusinessException
					{
		return this.transactionDAO.fetchByApplication(application,
				LifeCycleStatus.ACTIVE);
					}
	
	@Override
	public Transaction findTransactionById(final Long transactionId)
			throws BusinessException
	{
		return this.transactionDAO.findById(transactionId);
	}
}
