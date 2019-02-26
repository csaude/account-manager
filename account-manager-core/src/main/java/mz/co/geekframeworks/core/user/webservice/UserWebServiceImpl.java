/**
 *
 */
package mz.co.geekframeworks.core.user.webservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.jersey.api.JResponse;
import mz.co.geekframeworks.core.dto.UserContextDTO;
import mz.co.geekframeworks.core.security.AuthenticationService;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.model.UserContextFactory;
import mz.co.geekframeworks.core.user.service.UserQueryService;
import mz.co.geekframeworks.core.user.service.UserService;
import mz.co.geekframeworks.core.util.ConfigUtils;
import mz.co.geekframeworks.core.util.UserContextUtil;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.adapter.Entry;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Date;

/**
 * @author Stélio Moiane
 *
 */
@Service(UserWebService.NAME)
@Path("users")
public class UserWebServiceImpl implements UserWebService {
	// Hard code the secret key for encrypting the JWT token for now.
	private static String JWT_KEY;

	@Inject
	private UserQueryService userQueryService;

	@Inject
	private UserService userService;

	@Inject
	private AuthenticationService authenticationService;

	static {
		try {
			JWT_KEY = ConfigUtils.retrieveJWTKeyFromRunTime();
			System.out.println("Key is:" + JWT_KEY);
			if(JWT_KEY == null) {
				JWT_KEY = "secret";
			}
		} catch (IOException e) {
			// We don't care, we gonna use secret anyway (the default)
			e.printStackTrace();
			JWT_KEY = "secret";
		}
	}

	@Override
	public Response findUserBySessionId(final String sessionId) throws BusinessException {
		UserContext userContext = new UserContext();

		try {
			final User user = this.userQueryService.fetchUserByApplicationCodeAndUnitCodeAndUsername(userContext,
			        UserContextUtil.getApplicationCode(sessionId), UserContextUtil.getUnitCode(sessionId),
			        UserContextUtil.getUsername(sessionId));

			userContext = UserContextFactory.getUsercoContext(user);
		}
		catch (final NoResultException e) {
			return Response.noContent().build();
		}

		return Response.ok(userContext).build();
	}

	@Override
	public JResponse<UserContext> updatePassword(final UserContext userContext) throws BusinessException {

		final User user = this.userQueryService.findUserByUuid(userContext.getUuid());
		user.setPassword(userContext.getPassword());

		this.userService.updateUserPassword(userContext, user);

		return JResponse.ok(userContext).build();
	}

	@Override
	public Response login(final UserContext userContext) throws BusinessException, IOException {
		try {
			final UserContextDTO context = new UserContextDTO();
			final User authenticated = (User)this.authenticationService.getAuthentication(userContext.getUsername(),
					userContext.getPassword()).getPrincipal();

			if(authenticated != null && authenticated.isAccountNonExpired() && authenticated.isAccountNonLocked()) {
				context.setUsername(authenticated.getUsername());
				context.setFullName(authenticated.getFullName());
				context.setEmail(authenticated.getEmail());
				context.setUuid(authenticated.getUuid());
				Algorithm algHS = Algorithm.HMAC256(JWT_KEY);
				String authToken = JWT.create().withIssuer("account-manager").withSubject(authenticated.getUsername()).withIssuedAt(new Date())
						.withClaim("uuid", authenticated.getUuid())
						.sign(algHS);

				context.setJwtToken(authToken);

				return Response.ok(context).build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED)
						.header("Content-Type", "text/plain")
						.entity("Account expired or locked. Contact the system administrator").build();
			}

		} catch (BusinessException be) {
			be.printStackTrace();
			return Response.status(Response.Status.UNAUTHORIZED).header("Content-Type", "text/plain").entity(be.getMessage()).build();
		}
	}

	@Override
	public JResponse<User> createUser(final UserContext context) throws BusinessException {

		final User user = new User();
		user.setFullName(context.getFullName());
		user.setEmail(context.getEmail());
		user.setUsername(context.getUsername());
		user.setPassword(context.getPassword());
		user.setUuid(context.getPropertyValue(Entry.UUID));

		this.userService.createUser(context, user);

		return JResponse.ok(user).build();
	}
}
