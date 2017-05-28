/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction.service;

import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public interface TransactionQueryService
{
	String NAME = "mz_co_geekframeworks_core_transaction_service_TransactionQueryService";
	
	public List<Transaction> findAllTransactions(final UserContext userContext)
			throws BusinessException;
	
	public List<Transaction> fetchTransactionsByApplication(
			final UserContext userContext, final Application application)
					throws BusinessException;
	
	public Transaction findTransactionById(final Long transactionId)
			throws BusinessException;
}
