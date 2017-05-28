/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.geekframeworks.core.framework.test.util.Server;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.unit.service.UnitService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.webservices.model.UnitWS;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;

import com.jayway.restassured.path.xml.XmlPath;

/**
 * @author Stélio Moiane
 *
 */
public class UnitQueryServiceWSTest extends AbstractSpringContextTests
{
	@Inject
	private UnitService unitService;
	
	private HttpServer server;
	
	@Override
	public void setUp() throws BusinessException
	{
		this.server = new Server().uriBase("http://localhost/services")
				.port(8081).resourcesPackage("mz.co.geekframeworks.core")
				.context("classpath:/spring/test-config.xml").initialize();
	}
	
	@Test
	public void shouldFindUnitByCode() throws BusinessException
	{
		Unit unit = EntityFactory.gimme(Unit.class, "valid");
		this.unitService.createUnit(this.getUserContext(), unit);
		
		XmlPath xmlPath = given().header("Accept", "application/xml").expect()
				.statusCode(200).get("/services/units/unit/" + unit.getCode())
				.andReturn().xmlPath();
		
		UnitWS unitFound = xmlPath.getObject("unit", UnitWS.class);
		
		assertEquals(unit.getCode(), unitFound.getCode());
		assertEquals(unit.getName(), unitFound.getName());
	}
	
	@Override
	public void tearDown() throws BusinessException
	{
		this.server.stop();
	}
}
