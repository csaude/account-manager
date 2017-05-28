package mz.co.geekframeworks.core.framework.test;

import java.io.FileNotFoundException;
import java.util.Random;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.LoaderFactory;
import mz.co.mozview.frameworks.core.util.CleanDBUtil;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * Classe para inicializar o contexto do spring para uso nos testes.
 * 
 * @author Eudson Bambo
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/test-config.xml" })
public abstract class AbstractSpringContextTests {
	private static final String FIXTURE_FACTORY_TEMPLATES_DIR = "mz.co.geekframeworks.core.framework.fixturefactory.templates";
	private static final String LOG_BACK_RESOURCE_NAME = "classpath:/test-logback.xml";

	@Inject
	private CleanDBUtil cleanDBUtil;

	@BeforeClass
	public static void setUpBeforeClass() throws BusinessException {
		AbstractSpringContextTests.initLogging();

		LoaderFactory.loadTemplates(FIXTURE_FACTORY_TEMPLATES_DIR);

		RestAssured.port = 8081;
	}

	@AfterClass
	public static void tearDownAfterClass() {
		LogbackCongigurator.shutdownLogging();
	}

	@Before
	public void setUp() throws BusinessException {
	}

	@After
	public void tearDown() throws BusinessException {
		this.cleanDBUtil.cleanDB();
	}

	public int randomicInt() {
		Random random = new Random();
		return random.nextInt(10000);
	}

	public UserContext getUserContext() {
		UserContext context = new UserContext();
		context.setUuid("682eb67387a84d54b9adf93247aefb55");
		context.setId(1L);

		return context;
	}

	protected static void initLogging() {
		try {
			LogbackCongigurator.initLogging(LOG_BACK_RESOURCE_NAME);

			LoggerFactory.getLogger(LogbackCongigurator.class)
					.info("Logback configurado usando [" + LOG_BACK_RESOURCE_NAME + "]");
		} catch (FileNotFoundException e) {
			System.err.print(e.getMessage());
		}
	}
}