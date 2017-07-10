package mz.co.geekframeworks.core.user.service;

import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Eudson Bambo
 *
 */
public interface UserService {
	public static final String NAME = "mz_co_geekframeworks_core_user_service_UserService";

	public User createUser(final UserContext userContext, final User user) throws BusinessException;

	public User updateUser(final UserContext userContext, final User user) throws BusinessException;

	public void deleteUser(final UserContext userContext, final User user) throws BusinessException;

	public User updateUserPassword(final UserContext userContext, final User user) throws BusinessException;
}
