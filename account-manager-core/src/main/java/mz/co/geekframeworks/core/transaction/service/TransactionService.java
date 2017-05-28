/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction.service;

import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public interface TransactionService
{
	String NAME = "mz_co_geekframeworks_core_transaction_TransactionService";
	
	public Transaction createTransaction(final UserContext userContext,
			final Transaction transaction) throws BusinessException;
	
	public Transaction updateTransaction(final UserContext userContext,
			final Transaction transaction) throws BusinessException;
	
	public void removeTransaction(final UserContext userContext,
			final Transaction transaction) throws BusinessException;
}
