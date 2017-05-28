/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.unit.dao.UnitDAO;
import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Stélio Moiane
 *
 */
@Audited
@AuditOverrides(value = @AuditOverride(forClass = GenericEntity.class))
@NamedQueries({
	@NamedQuery(name = UnitDAO.QUERY_NAME.findAll, query = UnitDAO.QUERY.findAll),
	@NamedQuery(name = UnitDAO.QUERY_NAME.fetchByAppliaction, query = UnitDAO.QUERY.fetchByAppliaction),
	@NamedQuery(name = UnitDAO.QUERY_NAME.findByCode, query = UnitDAO.QUERY.findByCode) })
@Entity
@Table(name = "UNITS", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CODE", "NUIT", "NAME" }))
public class Unit extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 30)
	private String code;
	
	@NotEmpty
	@Size(min = 9, max = 9)
	@Column(name = "NUIT", nullable = false, length = 30)
	private String nuit;
	
	@NotEmpty
	@Column(name = "NAME", nullable = false, length = 80)
	private String name;
	
	@Email
	@Column(name = "EMAIL", length = 80)
	private String email;
	
	@NotEmpty
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	
	@NotEmpty
	@Column(name = "CONTACT", nullable = false)
	private String contact;
	
	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APPLICATION_UNIT", joinColumns = @JoinColumn(name = "UNIT_ID"), inverseJoinColumns = @JoinColumn(name = "APPLICATION_ID"))
	private Set<Application> applications;
	
	public String getCode()
	{
		return this.code;
	}
	
	public void setCode(final String code)
	{
		this.code = code;
	}
	
	public String getNuit()
	{
		return this.nuit;
	}
	
	public void setNuit(final String nuit)
	{
		this.nuit = nuit;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(final String email)
	{
		this.email = email;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress(final String address)
	{
		this.address = address;
	}
	
	public String getContact()
	{
		return this.contact;
	}
	
	public void setContact(final String contact)
	{
		this.contact = contact;
	}
	
	public Set<Application> getApplications()
	{
		return this.applications;
	}
	
	public void setApplications(final Set<Application> applications)
	{
		this.applications = applications;
	}
	
	@Override
	public boolean equals(final Object that)
	{
		return EqualsBuilder.reflectionEquals(this, that, "id", "applications");
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this, "id", "applications");
	}
}
