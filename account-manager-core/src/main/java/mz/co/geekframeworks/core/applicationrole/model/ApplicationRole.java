package mz.co.geekframeworks.core.applicationrole.model;

import java.util.Collections;
import java.util.Set;

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

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.applicationrole.dao.ApplicationRoleDAO;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 * @author Eudson Bambo
 * 
 */
@Entity
@Audited
@AuditOverrides(value = { @AuditOverride(forClass = GenericEntity.class) })
@NamedQueries({
	@NamedQuery(name = ApplicationRoleDAO.QUERY_NAME.fetchAll, query = ApplicationRoleDAO.QUERY.fetchAll),
	@NamedQuery(name = ApplicationRoleDAO.QUERY_NAME.fetchByApplication, query = ApplicationRoleDAO.QUERY.fetchByApplication) })
@Table(name = "APPLICATION_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = {
		"APPLICATION_ID", "ROLE_ID" }))
public class ApplicationRole extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "APPLICATION_ID", nullable = false)
	private Application application;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Role role;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TRANSACTION_APPLICATION_ROLES", joinColumns = @JoinColumn(name = "TRANSACTION_ID"), inverseJoinColumns = @JoinColumn(name = "APPLICATION_ROLE_ID"))
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Set<Transaction> transactions;
	
	public void setApplication(final Application application)
	{
		this.application = application;
	}
	
	public Application getApplication()
	{
		return this.application;
	}
	
	public void setRole(final Role role)
	{
		this.role = role;
	}
	
	public Role getRole()
	{
		return this.role;
	}
	
	public Set<Transaction> getTransactions()
	{
		return Collections.unmodifiableSet(this.transactions);
	}
	
	public void setTransactions(final Set<Transaction> transactions)
	{
		this.transactions = transactions;
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this, "id", "transactions",
				"role", "application");
	}
	
	@Override
	public boolean equals(final Object that)
	{
		return EqualsBuilder.reflectionEquals(this, that, "id", "transactions",
				"role", "application");
	}
}