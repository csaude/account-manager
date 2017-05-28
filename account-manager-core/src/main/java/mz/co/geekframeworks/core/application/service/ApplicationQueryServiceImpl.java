/**
 * 
 */
package mz.co.geekframeworks.core.application.service;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.dao.ApplicationAuditDAO;
import mz.co.geekframeworks.core.application.dao.ApplicationDAO;
import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.stereotype.Service;

/**
 * @author Stélio Moiane
 * 
 */
@Service(ApplicationQueryService.NAME)
public class ApplicationQueryServiceImpl extends AbstractService implements
		ApplicationQueryService
{
	@Inject
	private ApplicationDAO applicationDAO;
	
	@Inject
	private ApplicationAuditDAO applicationAuditDAO;
	
	@Override
	public Collection<Application> findApplications(
			final UserContext userContext) throws BusinessException
	{
		return this.applicationDAO.findApplications();
	}
	
	@Override
	public Application findById(final Long id) throws BusinessException
	{
		return this.applicationDAO.findById(id);
	}
	
	@Override
	public Collection<Application> findApplicationRevisionsById(final Long id)
			throws BusinessException
	{
		return this.applicationAuditDAO.findRevisions(id);
	}
	
	@Override
	public Collection<Application> findApplicationRevisionsByIds(
			final List<Long> ids) throws BusinessException
	{
		return this.applicationAuditDAO.findRevisions(ids);
	}
}
