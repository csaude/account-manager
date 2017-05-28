/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.transaction.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.transaction.dao.TransactionDAO;
import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Stélio Moiane
 *
 */
@Audited
@AuditOverrides(value = @AuditOverride(forClass = GenericEntity.class))
@NamedQueries({
	@NamedQuery(name = TransactionDAO.QUERY_NAME.findAll, query = TransactionDAO.QUERY.findAll),
	@NamedQuery(name = TransactionDAO.QUERY_NAME.fetchByApplication, query = TransactionDAO.QUERY.fetchByApplication) })
@Entity
@Table(name = "TRANSACTIONS", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CODE", "APPLICATION_ID" }))
public class Transaction extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Size(min = 3, max = 5)
	@Column(name = "CODE", nullable = false, length = 10)
	private String code;
	
	@NotEmpty
	@Column(name = "NAME", nullable = false, length = 150)
	private String name;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLICATION_ID", nullable = false)
	private Application application;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TRANSACTION_APPLICATION_ROLES", joinColumns = @JoinColumn(name = "APPLICATION_ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID"))
	private List<ApplicationRole> applicationRoles;
	
	public String getCode()
	{
		return this.code;
	}
	
	public void setCode(final String code)
	{
		this.code = code;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public Application getApplication()
	{
		return this.application;
	}
	
	public void setApplication(final Application application)
	{
		this.application = application;
	}
	
	public List<ApplicationRole> getApplicationRoles()
	{
		return Collections.unmodifiableList(this.applicationRoles);
	}
	
	@Override
	public boolean equals(final Object that)
	{
		return EqualsBuilder.reflectionEquals(this, that, "application",
				"applicationRoles");
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this, "application",
				"applicationRoles");
	}
}
