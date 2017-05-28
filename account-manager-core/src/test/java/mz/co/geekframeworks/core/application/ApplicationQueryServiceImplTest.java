package mz.co.geekframeworks.core.application;

import java.util.Collection;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationQueryService;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Eudson Bambo
 *
 */
public class ApplicationQueryServiceImplTest extends AbstractSpringContextTests
{
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private ApplicationQueryService applicationQueryService;
	
	@Test
	public void shouldFindRevisionsOnCreateAndUpdate() throws BusinessException
	{
		Application application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
		application.setCode("30");
		
		this.applicationService.createApplication(this.getUserContext(),
				application);
		
		Collection<Application> applicationHistory = this.applicationQueryService
				.findApplicationRevisionsById(application.getId());
		
		Assert.assertTrue(!applicationHistory.isEmpty());
		Assert.assertEquals(1, applicationHistory.size());
		
		application.setCode("DummyApp");
		this.applicationService.updateApplication(this.getUserContext(),
				application);
		
		applicationHistory = this.applicationQueryService
				.findApplicationRevisionsById(application.getId());
		
		Assert.assertEquals(2, applicationHistory.size());
	}
}
