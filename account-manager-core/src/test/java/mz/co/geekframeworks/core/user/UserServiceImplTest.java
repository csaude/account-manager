package mz.co.geekframeworks.core.user;

import static org.junit.Assert.assertNull;

import java.util.Collection;

import javax.inject.Inject;

import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.framework.test.util.TestUtils;
import mz.co.geekframeworks.core.user.dao.UserDAO;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.service.UserQueryService;
import mz.co.geekframeworks.core.user.service.UserService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Stélio Moiane
 *
 */
public class UserServiceImplTest extends AbstractSpringContextTests
{
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private UserQueryService userQueryService;
	
	private User user;
	
	@Override
	public void setUp()
	{
		this.user = EntityFactory.gimme(User.class,
				FixtureFactoryConstants.VALID_OBJECT);
		this.user.setEmail(this.user.getEmail() + "" + this.randomicInt());
	}
	
	@Test
	public void shouldCreateUser() throws BusinessException
	{
		this.userService.createUser(this.getUserContext(), this.user);
		
		TestUtils.assertCreation(this.user);
	}
	
	@Test
	public void shouldUpdateUser() throws BusinessException
	{
		User createdUser = this.userService.createUser(this.getUserContext(),
				this.user);
		createdUser.setPassword("steliomo");
		
		this.userService.updateUser(this.getUserContext(), createdUser);
		
		TestUtils.assertUpdate(this.user);
		
	}
	
	@Test
	public void shouldDeleteUser() throws BusinessException
	{
		User createdUser = this.userService.createUser(this.getUserContext(),
				this.user);
		
		this.userService.deleteUser(this.getUserContext(), createdUser);
		
		assertNull(this.userDAO.findById(createdUser.getId()));
	}
	
	@Test
	@Ignore
	public void shouldUpdateAllPasswords() throws BusinessException
	{
		Collection<User> allUsers = this.userQueryService.findAllUsers(this
				.getUserContext());
		
		for (User user : allUsers)
		{
			user.setPassword("stelioklesio");
			this.userService.updateUser(this.getUserContext(), user);
		}
	}
}
