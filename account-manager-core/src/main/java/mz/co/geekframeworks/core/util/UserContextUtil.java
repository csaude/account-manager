/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.util;

/**
 * @author Stélio Moiane
 *
 */
public class UserContextUtil
{
	public static String getApplicationCode(final String sessionId)
	{
		return sessionId.substring(0, 2);
	}
	
	public static String getUnitCode(final String sessionId)
	{
		return sessionId.substring(2, 11);
	}
	
	public static String getUsername(final String sessionId)
	{
		return sessionId.substring(11);
	}
}
