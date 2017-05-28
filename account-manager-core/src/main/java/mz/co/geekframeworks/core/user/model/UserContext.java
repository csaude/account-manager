/**
 *
 */
package mz.co.geekframeworks.core.user.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.geekframeworks.core.role.model.Role;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserContext
{
	private Long id;
	private String fullName;
	private String username;
	private String email;
	private String sessionId;
	private String oldPassword;
	private String newPassword;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	private Collection<Role> roles;
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setId(final Long id)
	{
		this.id = id;
	}
	
	public String getFullName()
	{
		return this.fullName;
	}
	
	public void setFullName(final String fullName)
	{
		this.fullName = fullName;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(final String username)
	{
		this.username = username;
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
	
	public String getOldPassword()
	{
		return this.oldPassword;
	}
	
	public void setOldPassword(final String oldPassword)
	{
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword()
	{
		return this.newPassword;
	}
	
	public void setNewPassword(final String newPassword)
	{
		this.newPassword = newPassword;
	}
	
	public boolean isAccountNonExpired()
	{
		return this.accountNonExpired;
	}
	
	public void setAccountNonExpired(final boolean accountNonExpired)
	{
		this.accountNonExpired = accountNonExpired;
	}
	
	public boolean isAccountNonLocked()
	{
		return this.accountNonLocked;
	}
	
	public void setAccountNonLocked(final boolean accountNonLocked)
	{
		this.accountNonLocked = accountNonLocked;
	}
	
	public boolean isCredentialsNonExpired()
	{
		return this.credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(final boolean credentialsNonExpired)
	{
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public void setEnabled(final boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public Collection<Role> getRoles()
	{
		return this.roles;
	}
	
	public void setRoles(final Collection<Role> roles)
	{
		this.roles = roles;
	}
	
}
