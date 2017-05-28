package mz.co.geekframeworks.core.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import mz.co.mozview.frameworks.core.model.GenericEntity;

/**
 * @author Eudson Bambo
 * 
 */
@MappedSuperclass
public class DescriptionedEntity extends GenericEntity
{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(final String description)
	{
		this.description = description;
	}
	
	public String getRemarks()
	{
		return this.remarks;
	}
	
	public void setRemarks(final String remarks)
	{
		this.remarks = remarks;
	}
}
