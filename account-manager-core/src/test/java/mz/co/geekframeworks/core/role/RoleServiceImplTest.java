package mz.co.geekframeworks.core.role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.role.dao.RoleDAO;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.role.service.RoleService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Before;
import org.junit.Test;

public class RoleServiceImplTest extends AbstractSpringContextTests
{
	@Inject
	private RoleService roleService;
	
	@Inject
	private RoleDAO roleDAO;
	
	private Role role;
	
	@Override
	@Before
	public void setUp()
	{
		this.role = EntityFactory.gimme(Role.class,
				FixtureFactoryConstants.VALID_OBJECT);
	}
	
	@Test
	public void shouldCreateRole() throws BusinessException
	{
		assertNull(this.role.getId());
		
		this.roleService.createRole(this.getUserContext(), this.role);
		
		assertNotNull(this.role.getId());
	}
	
	@Test
	public void shouldUpdateRole() throws BusinessException
	{
		Role createdRole = this.roleService.createRole(this.getUserContext(),
				this.role);
		
		createdRole.setDescription("Updated Description");
		
		this.roleService.updateRole(this.getUserContext(), createdRole);
		
		assertNotNull(createdRole.getUpdatedAt());
		assertNotNull(createdRole.getUpdatedBy());
		assertEquals("Updated Description", createdRole.getDescription());
	}
	
	@Test
	public void shouldDeleteRole() throws BusinessException
	{
		Role createdRole = this.roleService.createRole(this.getUserContext(),
				this.role);
		
		Long createdRoleId = createdRole.getId();
		assertNotNull(createdRoleId);
		
		this.roleService.deleteRole(this.getUserContext(), createdRole);
		
		assertNull(this.roleDAO.findById(createdRoleId));
	}
}
