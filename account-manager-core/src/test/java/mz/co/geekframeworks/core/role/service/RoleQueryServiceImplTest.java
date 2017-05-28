/**
 * 
 */
package mz.co.geekframeworks.core.role.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import javax.inject.Inject;

import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Eudson Bambo
 * 
 */
public class RoleQueryServiceImplTest extends AbstractSpringContextTests
{
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private RoleQueryService roleQueryService;
	
	private Role role;
	
	@Override
	@Before
	public void setUp()
	{
		this.role = EntityFactory.gimme(Role.class,
				FixtureFactoryConstants.VALID_OBJECT);
	}
	
	@Test
	public void shouldFindAllActiveRoles() throws BusinessException
	{
		this.roleService.createRole(this.getUserContext(), this.role);
		assertNotNull(this.role);
		Collection<Role> findRoles = this.roleQueryService.findRoles(null);
		
		assertFalse(findRoles.isEmpty());
	}
	
}
