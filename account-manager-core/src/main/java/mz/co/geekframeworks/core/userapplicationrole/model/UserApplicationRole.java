package mz.co.geekframeworks.core.userapplicationrole.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Eudson Bambo
 * 
 */
@Entity
@Table(name = "USER_APPLICATION_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = {
		"USER_ID", "APPLICATION_ROLE_ID" }))
public class UserApplicationRole extends GenericEntity implements
GrantedAuthority
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLICATION_ROLE_ID", nullable = false)
	private ApplicationRole applicationRole;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "UNIT_USER_APP_ROLES", joinColumns = @JoinColumn(name = "UNIT_ID"), inverseJoinColumns = @JoinColumn(name = "USER_APPLICATION_ROLE_ID"))
	private Set<Unit> units;
	
	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(final User user)
	{
		this.user = user;
	}
	
	public ApplicationRole getApplicationRole()
	{
		return this.applicationRole;
	}
	
	public void setApplicationRole(final ApplicationRole applicationRole)
	{
		this.applicationRole = applicationRole;
	}
	
	@Override
	public String getAuthority()
	{
		return this.applicationRole.getRole().getName();
	}
	
	public Set<Unit> getUnits()
	{
		return this.units;
	}
	
	public void setUnits(final Set<Unit> units)
	{
		this.units = units;
	}
}