/**
 * 
 */
package mz.co.geekframeworks.core.application.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.geekframeworks.core.application.dao.ApplicationDAO;
import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 * 
 */
@Service(ApplicationService.NAME)
public class ApplicationServiceImpl extends AbstractService implements ApplicationService {

	@Inject
	private ApplicationDAO applicationDAO;

	@Override
	public Application createApplication(final UserContext userContext, final Application application)
			throws BusinessException {
		return this.applicationDAO.create(userContext.getUuid(), application);
	}

	@Override
	public Application updateApplication(final UserContext userContext, final Application application)
			throws BusinessException {
		return this.applicationDAO.update(userContext.getUuid(), application);
	}

	@Override
	public void deleteApplication(final UserContext userContext, final Application application)
			throws BusinessException {
		this.applicationDAO.delete(userContext.getUuid(), application);
	}
}
