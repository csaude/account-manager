/**
 *
 */
package mz.co.geekframeworks.core.user.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mz.co.geekframeworks.core.user.dao.UserDAO;
import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Stélio Moiane
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = UserDAO.QUERY_NAME.findAll, query = UserDAO.QUERY.findAll),
	@NamedQuery(name = UserDAO.QUERY_NAME.fetchByUsername, query = UserDAO.QUERY.fetchByUsername),
	@NamedQuery(name = UserDAO.QUERY_NAME.findBySessionId, query = UserDAO.QUERY.findBySessionId),
	@NamedQuery(name = UserDAO.QUERY_NAME.fetchByApplicationCodeAndUnitCodeAndUsername, query = UserDAO.QUERY.fetchByApplicationCodeAndUnitCodeAndUsername) })
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = {
		"USER_NAME", "EMAIL" }))
public class User extends GenericEntity implements UserDetails
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "FULL_NAME", nullable = false, length = 80)
	private String fullName;
	
	@Column(name = "USER_NAME", nullable = false, length = 50)
	private String username;
	
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;
	
	@Column(name = "EMAIL", length = 50)
	private String email;
	
	@Column(name = "SESSION_ID", length = 50)
	private String sessionId;
	
	@Column(name = "ACCOUNT_NON_EXPIRED")
	private boolean accountNonExpired;
	
	@Column(name = "ACCOUNT_NON_LOCKED")
	private boolean accountNonLocked;
	
	@Column(name = "CREDENTIALS_NON_EXPIRED")
	private boolean credentialsNonExpired;
	
	@Column(name = "ENABLED")
	private boolean enabled;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserApplicationRole> userApplicationRoles;
	
	public String getFullName()
	{
		return this.fullName;
	}
	
	public void setFullName(final String fullName)
	{
		this.fullName = fullName;
	}
	
	@Override
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(final String username)
	{
		this.username = username;
	}
	
	@Override
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(final String password)
	{
		this.password = password;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(final String email)
	{
		this.email = email;
	}
	
	public String getSessionId()
	{
		return this.sessionId;
	}
	
	public void setSessionId(final String sessionId)
	{
		this.sessionId = sessionId;
	}
	
	@Override
	public Set<UserApplicationRole> getAuthorities()
	{
		return this.userApplicationRoles;
	}
	
	@Override
	public boolean isAccountNonExpired()
	{
		return this.accountNonExpired;
	}
	
	public void setAccountNonExpired(final boolean accountNonExpired)
	{
		this.accountNonExpired = accountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked()
	{
		return this.accountNonLocked;
	}
	
	public void setAccountNonLocked(final boolean accountNonLocked)
	{
		this.accountNonLocked = accountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired()
	{
		return this.credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(final boolean credentialsNonExpired)
	{
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public void setEnabled(final boolean enabled)
	{
		this.enabled = enabled;
	}
	
	@Override
	public int hashCode()
	{
		return this.getId().intValue() * this.getFullName().hashCode()
				* this.getEmail().hashCode();
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		
		if (!(obj instanceof User))
		{
			return false;
		}
		
		if (this.getId() != ((User) obj).getId())
		{
			return false;
		}
		
		if (!(this.getFullName().equals(((User) obj).getFullName())))
		{
			return false;
		}
		
		if (!(this.getEmail().equals(((User) obj).getEmail())))
		{
			return false;
		}
		
		return true;
	}
}
