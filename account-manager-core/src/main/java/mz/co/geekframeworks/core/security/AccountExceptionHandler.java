package mz.co.geekframeworks.core.security;

/*
 * Friends in Global Health - FGH © 2017
 */

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.exception.ErrorMessage;

/**
 * @author Stélio Moiane
 *
 *         This is the Exception Default Handler class for Jersey
 *
 */
@Provider
public class AccountExceptionHandler implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(final BusinessException businessException) {
		return Response.ok(new ErrorMessage(businessException.getMessage())).build();
	}
}
