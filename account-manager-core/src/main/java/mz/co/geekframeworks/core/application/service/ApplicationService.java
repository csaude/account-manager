/**
 * 
 */
package mz.co.geekframeworks.core.application.service;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * 
 */
public interface ApplicationService
{
	
	public static final String NAME = "mz_co_geekframeworks_core_application_service_ApplicationService";
	
	public Application createApplication(final UserContext userContext,
			final Application application) throws BusinessException;
	
	public Application updateApplication(final UserContext userContext,
			final Application application) throws BusinessException;
	
	public void deleteApplication(final UserContext userContext,
			final Application application) throws BusinessException;
}
