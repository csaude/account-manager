package mz.co.geekframeworks.core.application.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mz.co.geekframeworks.core.application.dao.ApplicationDAO;
import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.model.CodedEntity;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * @author Eudson Bambo
 *
 */
@Entity
@Audited
@AuditOverrides(value = { @AuditOverride(forClass = CodedEntity.class),
		@AuditOverride(forClass = GenericEntity.class) })
@NamedQueries({ @NamedQuery(name = ApplicationDAO.QUERY_NAME.findApplications, query = ApplicationDAO.QUERY.findApplications) })
@Table(name = "APPLICATION", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class Application extends CodedEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CONTEXT_NAME", nullable = false)
	private String contextName;
	
	@OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
	private Collection<ApplicationRole> applicationRoles;
	
	@OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
	private List<Transaction> transactions;
	
	public Application()
	{
	}
	
	public String getContextName()
	{
		return this.contextName;
	}
	
	public void setContextName(final String contextName)
	{
		this.contextName = contextName;
	}
	
	public Application(final Long id)
	{
		this.setId(id);
	}
	
	public List<Transaction> getTransactions()
	{
		return this.transactions;
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this, "id", "transactions",
				"applicationRoles");
	}
	
	@Override
	public boolean equals(final Object that)
	{
		return EqualsBuilder.reflectionEquals(this, that, "id", "transactions",
				"applicationRoles");
	}
}
