package mz.co.geekframeworks.core.applicationrole;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.application.service.ApplicationService;
import mz.co.geekframeworks.core.applicationrole.dao.ApplicationRoleDAO;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.applicationrole.service.ApplicationRoleService;
import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.framework.test.util.TestUtils;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.exception.DataBaseException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Eudson Bambo
 *
 */
public class ApplicationRoleServiceImplTest extends AbstractSpringContextTests
{
	
	@Inject
	private ApplicationRoleService applicationRoleService;
	
	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private ApplicationRoleDAO applicationRoleDAO;
	
	private Application application;
	
	private Role role;
	
	private Role roleForUpdate;
	
	private Application applicationForUpdate;
	
	@Override
	@Before
	public void setUp()
	{
		
		this.role = EntityFactory.gimme(Role.class,
				FixtureFactoryConstants.VALID_OBJECT);
		
		this.roleForUpdate = EntityFactory.gimme(Role.class,
				FixtureFactoryConstants.VALID_OBJECT);
		
		this.application = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
		
		this.applicationForUpdate = EntityFactory.gimme(Application.class,
				FixtureFactoryConstants.VALID_OBJECT);
	}
	
	@Test
	public void shouldCreateApplicationRole() throws BusinessException
	{
		this.roleService.createRole(this.getUserContext(), this.role);
		this.application.setCode("888");
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		TestUtils.assertCreation(this.role);
		TestUtils.assertCreation(this.application);
		
		ApplicationRole applicationRole = this.applicationRoleService
				.createApplicationRole(this.getUserContext(), this.application,
						this.role);
		
		TestUtils.assertCreation(applicationRole);
	}
	
	@Test(expected = DataBaseException.class)
	public void shouldNotCreateApplicationRoleIfRoleIsAlreadyAssociatedWithAApplication()
			throws BusinessException
	{
		
		this.roleService.createRole(this.getUserContext(), this.role);
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		TestUtils.assertCreation(this.role);
		TestUtils.assertCreation(this.application);
		
		ApplicationRole createdApplicationRole = this.applicationRoleService
				.createApplicationRole(this.getUserContext(), this.application,
						this.role);
		
		TestUtils.assertCreation(createdApplicationRole);
		
		this.applicationRoleService.createApplicationRole(
				this.getUserContext(), this.application, this.role);
	}
	
	@Test(expected = PersistenceException.class)
	public void shouldNotCreateApplicationRoleIfApplicationAndRoleDontExits()
			throws BusinessException
	{
		this.applicationRoleService.createApplicationRole(
				this.getUserContext(), new Application(999L), new Role(999L));
	}
	
	@Test
	public void shouldUpdateApplicationRole() throws BusinessException
	{
		this.roleService.createRole(this.getUserContext(), this.role);
		this.application.setCode("123");
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		TestUtils.assertCreation(this.role);
		TestUtils.assertCreation(this.application);
		
		ApplicationRole createdApplicationRole = this.applicationRoleService
				.createApplicationRole(this.getUserContext(), this.application,
						this.role);
		
		TestUtils.assertCreation(createdApplicationRole);
		assertEquals(createdApplicationRole.getApplication(), this.application);
		assertEquals(createdApplicationRole.getRole(), this.role);
		
		this.roleService.createRole(this.getUserContext(), this.roleForUpdate);
		TestUtils.assertCreation(this.roleForUpdate);
		
		createdApplicationRole.setRole(this.roleForUpdate);
		
		ApplicationRole updatedApplicationRole = this.applicationRoleService
				.updateApplicationRole(this.getUserContext(),
						createdApplicationRole);
		
		assertEquals(updatedApplicationRole.getId(),
				createdApplicationRole.getId());
		assertNotEquals(updatedApplicationRole.getRole(), this.role);
		assertEquals(updatedApplicationRole.getApplication().getId(),
				this.application.getId());
	}
	
	@Test(expected = DataBaseException.class)
	public void shouldNotUpdateApplicationRoleToExistedAssotioation()
			throws BusinessException
	{
		this.roleService.createRole(this.getUserContext(), this.role);
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		this.applicationRoleService.createApplicationRole(
				this.getUserContext(), this.application, this.role);
		
		this.roleService.createRole(this.getUserContext(), this.roleForUpdate);
		this.applicationService.createApplication(this.getUserContext(),
				this.applicationForUpdate);
		
		ApplicationRole applicationRoleForUpdate = this.applicationRoleService
				.createApplicationRole(this.getUserContext(),
						this.applicationForUpdate, this.roleForUpdate);
		
		applicationRoleForUpdate.setRole(this.role);
		applicationRoleForUpdate.setApplication(this.application);
		
		this.applicationRoleService.updateApplicationRole(
				this.getUserContext(), applicationRoleForUpdate);
	}
	
	@Test
	public void shouldDeleteApplicationRole() throws BusinessException
	{
		this.roleService.createRole(this.getUserContext(), this.role);
		this.application.setCode("890");
		this.applicationService.createApplication(this.getUserContext(),
				this.application);
		
		TestUtils.assertCreation(this.role);
		TestUtils.assertCreation(this.application);
		
		ApplicationRole createdApplicationRole = this.applicationRoleService
				.createApplicationRole(this.getUserContext(), this.application,
						this.role);
		
		TestUtils.assertCreation(createdApplicationRole);
		
		assertNotNull(this.applicationRoleDAO.findById(createdApplicationRole
				.getId()));
		
		this.applicationRoleService.deleteApplicationRole(
				this.getUserContext(), createdApplicationRole);
		
		assertNull(this.applicationRoleDAO.findById(createdApplicationRole
				.getId()));
	}
}
