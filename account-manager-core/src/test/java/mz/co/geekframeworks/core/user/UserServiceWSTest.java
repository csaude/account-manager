/**
 *
 */
package mz.co.geekframeworks.core.user;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.framework.test.util.Server;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.geekframeworks.core.user.model.UserContext;
import mz.co.geekframeworks.core.user.service.UserService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.path.xml.XmlPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Stélio Moiane
 * 
 */
public class UserServiceWSTest extends AbstractSpringContextTests
{
	@Inject
	private UserService userService;
	
	private HttpServer server;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.server = new Server().uriBase("http://localhost/services")
				.port(8081).resourcesPackage("mz.co.geekframeworks.core")
				.context("classpath:/spring/test-config.xml").initialize();
	}
	
	@Override
	public void tearDown() throws BusinessException
	{
		this.server.stop();
	}
	
	@Test
	@Ignore
	public void shouldFindUserBySessionId() throws BusinessException
	{
		String sessionId = "steliomo";
		
		User user = EntityFactory.gimme(User.class,
				FixtureFactoryConstants.VALID_OBJECT);
		user.setEmail(user.getEmail() + "" + this.randomicInt());
		user.setSessionId(sessionId);
		
		this.userService.createUser(this.getUserContext(), user);
		
		XmlPath xmlPath = given().header("Accept", "application/xml").expect()
				.statusCode(200).get("/services/users/" + sessionId)
				.andReturn().xmlPath();
		
		UserContext userFound = xmlPath.getObject("user", UserContext.class);
		
		assertEquals(user.getFullName(), userFound.getFullName());
		assertEquals(user.getSessionId(), userFound.getSessionId());
	}
	
	@Test
	@Ignore
	public void shouldNotFindUserBySessionId() throws BusinessException
	{
		String sessionId = "kamilah";
		
		given().header("Accept", "application/xml").expect().statusCode(204)
		.get("/services/users/" + sessionId).andReturn().xmlPath();
	}
	
	@Test
	public void shouldGetAllUsers()
	{
		Client client = Client.create();
		
		WebResource resource = client
				.resource("http://localhost:8081/services/users");
		
		GenericType<List<UserContext>> genericType = new GenericType<List<UserContext>>()
				{
				};
				
				List<UserContext> list = resource.accept(MediaType.APPLICATION_XML)
						.type(MediaType.APPLICATION_XML).get(genericType);
				
				assertTrue(!list.isEmpty());
				assertEquals(4, list.size());
				assertEquals(200, resource.head().getStatus());
	}
	
	@Test
	@Ignore
	public void shouldUpdateUserPassword() throws BusinessException
	{
		UserContext userContext = EntityFactory.gimme(UserContext.class,
				"valid");
		
		User user = EntityFactory.gimme(User.class,
				FixtureFactoryConstants.VALID_OBJECT);
		user.setEmail(user.getEmail() + "" + this.randomicInt());
		user.setSessionId(userContext.getSessionId());
		user.setPassword(userContext.getOldPassword());
		
		this.userService.createUser(this.getUserContext(), user);
		
		Client client = Client.create();
		
		WebResource resource = client
				.resource("http://localhost:8081/services/users/update");
		
		UserContext updateContext = resource.accept(MediaType.APPLICATION_XML)
				.type(MediaType.APPLICATION_XML)
				.put(UserContext.class, userContext);
		
		assertEquals(userContext.getNewPassword(),
				updateContext.getNewPassword());
		
		assertNull(updateContext.getOldPassword());
	}
	
	@Test(expected = UniformInterfaceException.class)
	public void shouldNotUpdateUserPassword() throws BusinessException
	{
		UserContext userContext = EntityFactory.gimme(UserContext.class,
				"valid");
		userContext.setOldPassword("humm!");
		
		User user = EntityFactory.gimme(User.class,
				FixtureFactoryConstants.VALID_OBJECT);
		user.setEmail(user.getEmail() + "" + this.randomicInt());
		user.setSessionId(userContext.getSessionId());
		
		this.userService.createUser(this.getUserContext(), user);
		
		Client client = Client.create();
		
		WebResource resource = client
				.resource("http://localhost:8081/services/users/update");
		
		resource.accept(MediaType.APPLICATION_XML)
		.type(MediaType.APPLICATION_XML)
		.put(UserContext.class, userContext);
	}
}
