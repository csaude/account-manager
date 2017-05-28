package mz.co.geekframeworks.core.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import mz.co.mozview.frameworks.core.model.GenericEntity;

/**
 * @author Eudson Bambo
 * 
 */
@MappedSuperclass
public class CodedEntity extends GenericEntity
{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CODE", nullable = false)
	private String code;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public String getCode()
	{
		return this.code;
	}
	
	public void setCode(final String code)
	{
		this.code = code;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(final String description)
	{
		this.description = description;
	}
}
