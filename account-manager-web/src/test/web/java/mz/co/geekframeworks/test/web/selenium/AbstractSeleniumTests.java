package mz.co.geekframeworks.test.web.selenium;

import mz.co.geekframeworks.core.framework.test.AbstractSpringContextTests;
import mz.co.mozview.frameworks.core.exception.BusinessException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Eudson Bambo
 *
 */
public abstract class AbstractSeleniumTests extends AbstractSpringContextTests
{
	@BeforeClass
	public static void setUpBeforeClass()
	{
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
	}
	
	@Override
	@Before
	public void setUp() throws BusinessException
	{
		super.setUp();
	}
	
	@Override
	@After
	public void tearDown() throws BusinessException
	{
		super.tearDown();
	}
}