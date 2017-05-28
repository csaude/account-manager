package mz.co.geekframeworks.core.application.dao;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.audit.AuditDAO;

/**
 * @author Eudson Bambo
 *
 */
public interface ApplicationAuditDAO extends AuditDAO<Application, Long>
{
	public static final String NAME = "mz.co.geekframeworks.core.application.dao.ApplicationAuditDAO";
}
