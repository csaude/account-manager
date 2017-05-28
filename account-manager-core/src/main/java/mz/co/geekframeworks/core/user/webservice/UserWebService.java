/**
 *
 */
package mz.co.geekframeworks.core.user.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public interface UserWebService
{
	public static final String NAME = "mz_co_geekframeworks_core_user_webservice.UserWebService";
	
	@GET
	@Path("{sessionId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response findUserBySessionId(
			@PathParam("sessionId") final String sessionId)
			throws BusinessException;
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<UserContext> login(final UserContext userContext)
			throws BusinessException;
	
	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updatePassWord(final UserContext userContext)
			throws BusinessException;
}
