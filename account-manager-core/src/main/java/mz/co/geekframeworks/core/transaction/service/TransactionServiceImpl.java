/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.transaction.dao.TransactionDAO;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
@Service(TransactionService.NAME)
public class TransactionServiceImpl extends AbstractService implements TransactionService {
	@Inject
	private TransactionDAO transactionDAO;

	@Override
	public Transaction createTransaction(final UserContext userContext, final Transaction transaction)
			throws BusinessException {
		return this.transactionDAO.create(userContext.getUuid(), transaction);
	}

	@Override
	public Transaction updateTransaction(final UserContext userContext, final Transaction transaction)
			throws BusinessException {
		return this.transactionDAO.update(userContext.getUuid(), transaction);
	}

	@Override
	public void removeTransaction(final UserContext userContext, final Transaction transaction)
			throws BusinessException {
		this.transactionDAO.delete(userContext.getUuid(), transaction);
	}
}
