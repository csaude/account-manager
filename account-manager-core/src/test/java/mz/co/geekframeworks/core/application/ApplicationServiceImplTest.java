/**
 * 
 */
package mz.co.geekframeworks.core.application;

import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.dao.ApplicationDAO;
import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.framework.test.util.TestUtils;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Test;

/**
 * @author Stélio Moiane
 * 
 */
public class ApplicationServiceImplTest extends AbstractSpringContextTests
{
	
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private ApplicationDAO applicationDAO;
	
	@Override
	public void setUp()
	{
	}
	
	@Test
	public void shouldCreateApplication() throws BusinessException
	{
		Application application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
		application.setCode("50");
		this.applicationService.createApplication(this.getUserContext(),
				application);
		
		TestUtils.assertCreation(application);
	}
	
	@Test
	public void shouldUpdateApplication() throws BusinessException
	{
		Application application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
		application.setCode("20");
		
		this.applicationService.createApplication(this.getUserContext(),
				application);
		
		application
				.setDescription("Esta aplicação é bastante util na gestão de finanças");
		
		this.applicationService.updateApplication(this.getUserContext(),
				application);
		
		TestUtils.assertUpdate(application);
	}
	
	@Test
	public void shouldDeleteApplication() throws BusinessException
	{
		Application application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
		application.setCode("30");
		
		Application createdApplication = this.applicationService
				.createApplication(this.getUserContext(), application);
		
		this.applicationService.deleteApplication(this.getUserContext(),
				createdApplication);
		
		assertNull(this.applicationDAO.findById(createdApplication.getId()));
	}
}
