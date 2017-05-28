/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.util;

/**
 * @author Stélio Moiane
 *
 */
public enum CodeParams
{
	
	UNIT("AMU", 6, "0");
	
	private final String prefix;
	private final int lenght;
	private final String completeValue;
	
	private CodeParams(final String prefix, final int lenght,
			final String completeValue)
	{
		this.prefix = prefix;
		this.lenght = lenght;
		this.completeValue = completeValue;
	}
	
	public String getPrefix()
	{
		return this.prefix;
	}
	
	public int getLenght()
	{
		return this.lenght;
	}
	
	public String getCompleteValue()
	{
		return this.completeValue;
	}
}
