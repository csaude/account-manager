package mz.co.geekframeworks.core.application.dao;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.audit.AbstractAuditDAO;

import org.springframework.stereotype.Repository;

/**
 * @author Eudson Bambo
 *
 */
@Repository(ApplicationAuditDAO.NAME)
public class ApplicationAuditDAOImpl extends
AbstractAuditDAO<Application, Long> implements ApplicationAuditDAO
{
	
}
