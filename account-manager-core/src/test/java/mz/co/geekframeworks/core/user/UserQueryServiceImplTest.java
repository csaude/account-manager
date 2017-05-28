/**
 *
 */
package mz.co.geekframeworks.core.user;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.inject.Inject;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleService;
import mz.co.geekframeworks.core.framework.fixturefactory.templates.UnitTemplate;
import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleService;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.unit.service.UnitService;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.service.UserQueryService;
import mz.co.geekframeworks.core.user.service.UserService;
import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.geekframeworks.core.userapplicationrole.service.UserApplicationRoleService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Test;

/**
 * @author St�lio Moiane
 * 
 */
public class UserQueryServiceImplTest extends AbstractSpringContextTests
{
	@Inject
	private UserService userService;
	
	@Inject
	private UserQueryService userQueryService;
	
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private ApplicationRoleService applicationRoleService;
	
	@Inject
	private UnitService unitService;
	
	@Inject
	private UserApplicationRoleService userApplicationRoleService;
	
	private User user;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.user = EntityFactory.gimme(User.class,
				FixtureFactoryConstants.VALID_OBJECT);
		this.user.setEmail(this.user.getEmail() + "" + this.randomicInt());
	}
	
	@Test
	public void shouldFindAllUsers() throws BusinessException
	{
		
		this.userService.createUser(this.getUserContext(), this.user);
		
		Collection<User> users = this.userQueryService.findAllUsers(this
				.getUserContext());
		
		assertThat(users.isEmpty(), is(false));
		// assertThat(users, contains(createdUser));
	}
	
	@Test
	public void shouldFindUserBySessionId() throws BusinessException
	{
		String sessionId = "steliomo";
		
		this.user.setSessionId(sessionId);
		this.userService.createUser(this.getUserContext(), this.user);
		
		User foundUser = this.userQueryService.findUserBySessionId(sessionId);
		
		assertTrue(this.user.equals(foundUser));
	}
	
	@Test
	public void shouldFetchUserByApplicationCodeAndUnitCodeAndUsername()
			throws BusinessException
	{
		this.userService.createUser(this.getUserContext(), this.user);
		Application application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
		application.setCode("99");
		this.applicationService.createApplication(this.getUserContext(),
				application);
		Role role = EntityFactory.gimme(Role.class,
				FixtureFactoryConstants.VALID_OBJECT);
		this.roleService.createRole(this.getUserContext(), role);
		
		ApplicationRole applicationRole = this.applicationRoleService
				.createApplicationRole(this.getUserContext(), application, role);
		
		Unit unit = EntityFactory.gimme(Unit.class, UnitTemplate.VALID);
		this.unitService.createUnit(this.getUserContext(), unit);
		
		UserApplicationRole userApplicationRole = new UserApplicationRole();
		userApplicationRole.setApplicationRole(applicationRole);
		userApplicationRole.setUser(this.user);
		userApplicationRole.setUnits(new HashSet<>(Arrays.asList(unit)));
		this.userApplicationRoleService.createUserApplicationRole(
				this.getUserContext(), userApplicationRole);
		
		User user = this.userQueryService
				.fetchUserByApplicationCodeAndUnitCodeAndUsername(
						this.getUserContext(), application.getCode(),
						unit.getCode(), this.user.getUsername());
		
		assertNotNull(user);
		assertEquals(1, user.getAuthorities().size());
		assertEquals(application.getCode(), user.getAuthorities().iterator()
				.next().getApplicationRole().getApplication().getCode());
		assertEquals(unit.getCode(), user.getAuthorities().iterator().next()
				.getUnits().iterator().next().getCode());
		assertEquals(user.getUsername(), user.getUsername());
	}
}
