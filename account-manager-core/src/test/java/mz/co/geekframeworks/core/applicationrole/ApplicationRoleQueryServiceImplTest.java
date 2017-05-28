/**
 *
 */
package mz.co.geekframeworks.core.applicationrole;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleQueryService;
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleService;
import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Test;

/**
 * @author St�lio Moiane
 *
 */
public class ApplicationRoleQueryServiceImplTest extends
		AbstractSpringContextTests
{
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private ApplicationRoleService applicationRoleService;
	
	@Inject
	private ApplicationRoleQueryService applicationRoleQueryService;
	
	private Role role;
	
	private Application application;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.role = EntityFactory.gimme(Role.class,
				FixtureFactoryConstants.VALID_OBJECT);
		
		this.application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
	}
	
	@Test
	public void shouldFetchAllApplicationRoles() throws BusinessException
	{
		this.roleService.createRole(this.getUserContext(), this.role);
		this.application.setCode("111");
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		this.applicationRoleService.createApplicationRole(
				this.getUserContext(), this.application, this.role);
		
		Collection<ApplicationRole> applicationRoles = this.applicationRoleQueryService
				.fetchAllApplicationRoles(this.getUserContext());
		
		assertThat(applicationRoles.isEmpty(), is(false));
	}
	
	@Test
	public void shouldFetchApplicationRolesByApplication()
			throws BusinessException
	{
		this.roleService.createRole(this.getUserContext(), this.role);
		this.application.setCode("333");
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		this.applicationRoleService.createApplicationRole(
				this.getUserContext(), this.application, this.role);
		
		Collection<ApplicationRole> applicationRoles = this.applicationRoleQueryService
				.fetchAppliactionRolesByApplication(this.getUserContext(),
						this.application);
		
		assertThat(applicationRoles.isEmpty(), is(false));
	}
	
}
