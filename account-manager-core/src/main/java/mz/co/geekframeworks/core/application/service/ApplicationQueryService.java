/**
 * 
 */
package mz.co.geekframeworks.core.application.service;

import java.util.Collection;
import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * 
 */
public interface ApplicationQueryService
{
	public static final String NAME = "mz_co_geekframeworks_core_application_service_ApplicationQueryService";
	
	public abstract Collection<Application> findApplications(
			final UserContext userContext) throws BusinessException;
	
	public abstract Application findById(final Long id)
			throws BusinessException;
	
	public abstract Collection<Application> findApplicationRevisionsById(
			final Long id) throws BusinessException;
	
	public abstract Collection<Application> findApplicationRevisionsByIds(
			List<Long> ids) throws BusinessException;
}
