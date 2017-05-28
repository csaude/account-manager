/**
 * 
 */
package mz.co.geekframeworks.core.userapplicationrole.dao;

import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.mozview.frameworks.core.dao.GenericDAO;

/**
 * @author Stélio Moiane
 * 
 */
public interface UserApplicationRoleDAO extends
		GenericDAO<UserApplicationRole, Long>
{
	
	public static final String NAME = "mz.co.geekframeworks.core.userapplicationrole.dao.UserApplicationRoleDAO";
	
}
