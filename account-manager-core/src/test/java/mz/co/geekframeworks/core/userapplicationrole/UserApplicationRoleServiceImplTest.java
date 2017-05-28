/**
 * 
 */
package mz.co.geekframeworks.core.userapplicationrole;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleService;
import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.framework.test.util.TestUtils;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleService;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.service.UserService;
import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.geekframeworks.core.userapplicationrole.service.UserApplicationRoleService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Test;

/**
 * @author Stélio Moiane
 * 
 */
public class UserApplicationRoleServiceImplTest extends
AbstractSpringContextTests
{
	
	@Inject
	private UserService userService;
	
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private ApplicationRoleService applicationRoleService;
	
	@Inject
	private UserApplicationRoleService userApplicationRoleService;
	
	private User user;
	
	private Application application;
	
	private Role role;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.user = EntityFactory.gimme(User.class,
				FixtureFactoryConstants.VALID_OBJECT);
		this.application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
		
		this.role = EntityFactory.gimme(Role.class,
				FixtureFactoryConstants.VALID_OBJECT);
		this.roleService.createRole(this.getUserContext(), this.role);
	}
	
	@Test
	public void shouldCreateUserApplicationRole() throws BusinessException
	{
		
		this.userService.createUser(this.getUserContext(), this.user);
		
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		ApplicationRole applicationRole = this.applicationRoleService
				.createApplicationRole(this.getUserContext(), this.application,
						this.role);
		
		UserApplicationRole userApplicationRole = new UserApplicationRole();
		userApplicationRole.setUser(this.user);
		userApplicationRole.setApplicationRole(applicationRole);
		
		userApplicationRole = this.userApplicationRoleService
				.createUserApplicationRole(this.getUserContext(),
						userApplicationRole);
		
		TestUtils.assertCreation(userApplicationRole);
	}
	
	@Test
	public void shouldUpdateUserApplicationRole() throws BusinessException
	{
		this.user.setEmail(this.user.getEmail() + 1);
		this.userService.createUser(this.getUserContext(), this.user);
		
		this.application.setCode("02");
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		ApplicationRole applicationRole = this.applicationRoleService
				.createApplicationRole(this.getUserContext(), this.application,
						this.role);
		
		UserApplicationRole userApplicationRole = new UserApplicationRole();
		userApplicationRole.setUser(this.user);
		userApplicationRole.setApplicationRole(applicationRole);
		
		userApplicationRole = this.userApplicationRoleService
				.createUserApplicationRole(this.getUserContext(),
						userApplicationRole);
		
		userApplicationRole.setUser(this.user);
		this.userApplicationRoleService.updateUserApplicationRole(
				this.getUserContext(), userApplicationRole);
		
		TestUtils.assertUpdate(userApplicationRole);
	}
}
