/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.user;

import static org.junit.Assert.assertEquals;
import mz.co.geekframeworks.core.util.UserContextUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Stélio Moiane
 *
 */
public class UserContextTest
{
	
	private String sessionId;
	
	@Before
	public void setUp()
	{
		this.sessionId = "01AMU000001steliomo";
	}
	
	@Test
	public void shouldGetApplicationCodeFromSessionId()
	{
		String applicationCode = UserContextUtil
				.getApplicationCode(this.sessionId);
		
		assertEquals("01", applicationCode);
	}
	
	@Test
	public void shouldGetUnitCodeFromSessionId()
	{
		String unitCode = UserContextUtil.getUnitCode(this.sessionId);
		assertEquals("AMU000001", unitCode);
	}
	
	@Test
	public void shouldGetUsernameFromSessionId()
	{
		String username = UserContextUtil.getUsername(this.sessionId);
		assertEquals("steliomo", username);
	}
}
