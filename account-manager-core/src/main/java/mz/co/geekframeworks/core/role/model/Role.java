/**
 * 
 */
package mz.co.geekframeworks.core.role.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.co.geekframeworks.core.applicationrole.model.ApplicationRole;
import mz.co.geekframeworks.core.model.DescriptionedEntity;
import mz.co.geekframeworks.core.role.dao.RoleDAO;

/**
 * @author Stélio Moiane
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = RoleDAO.QUERY_NAME.findRoles, query = RoleDAO.QUERY.findRoles) })
@Table(name = "ROLE")
public class Role extends DescriptionedEntity
{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NAME", nullable = false, length = 100)
	private String name;
	
	@OneToMany(mappedBy = "role")
	private Collection<ApplicationRole> applicationRoles;
	
	public Role()
	{
	}
	
	public Role(final Long id)
	{
		this.setId(id);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	@Override
	public int hashCode()
	{
		return this.getId().intValue() * this.getName().hashCode();
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		
		if (!(obj instanceof Role))
		{
			return false;
		}
		
		if (this.getId() != ((Role) obj).getId())
		{
			return false;
		}
		
		if (!(this.getName().equals(((Role) obj).getName())))
		{
			return false;
		}
		
		return true;
	}
}
