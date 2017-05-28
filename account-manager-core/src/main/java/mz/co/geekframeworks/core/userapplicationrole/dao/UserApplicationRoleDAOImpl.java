/**
 * 
 */
package mz.co.geekframeworks.core.userapplicationrole.dao;

import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 * 
 */
@Repository(UserApplicationRoleDAO.NAME)
public class UserApplicationRoleDAOImpl extends
		GenericDAOImpl<UserApplicationRole, Long> implements
		UserApplicationRoleDAO
{
}
