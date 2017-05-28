/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction.dao;

import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;

/**
 * @author Stélio Moiane
 *
 */
public interface TransactionDAO extends GenericDAO<Transaction, Long>
{
	String NAME = "mz.co.geekframeworks.core.transaction.dao.TransactionDAO";
	
	public class QUERY
	{
		public static final String findAll = "select t from Transaction t where t.lifeCycleStatus = :lifeCycleStatus";
		
		public static final String fetchByApplication = "select t from Transaction t join fetch t.application where t.application = :application and t.lifeCycleStatus = :lifeCycleStatus";
	}
	
	public class QUERY_NAME
	{
		public static final String findAll = "Transaction.findAll";
		
		public static final String fetchByApplication = "Transaction.fetchByApplication";
	}
	
	public abstract List<Transaction> findAll(
			final LifeCycleStatus lifeCycleStatus);
	
	public abstract List<Transaction> fetchByApplication(
			final Application application, final LifeCycleStatus lifeCycleStatus);
}
