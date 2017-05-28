/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction.dao;

import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 *
 */
@Repository(TransactionDAO.NAME)
public class TransactionDAOImpl extends GenericDAOImpl<Transaction, Long>
		implements TransactionDAO
{
	
	@Override
	public List<Transaction> findAll(final LifeCycleStatus lifeCycleStatus)
	{
		return this.findByNamedQuery(TransactionDAO.QUERY_NAME.findAll,
				new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus)
						.process());
	}
	
	@Override
	public List<Transaction> fetchByApplication(final Application application,
			final LifeCycleStatus lifeCycleStatus)
	{
		return this.findByNamedQuery(
				TransactionDAO.QUERY_NAME.fetchByApplication,
				new ParamBuilder().add("application", application)
						.add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
