/**
 * 
 */
package mz.co.geekframeworks.core.audit;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import mz.co.mozview.frameworks.core.model.GenericEntity;

/**
 * @author Eudson Bambo
 * 
 */
public interface AuditDAO<T extends GenericEntity, V extends Serializable>
{
	public static final String NAME = "mz.co.geekframeworks.core.audit.AuditDAO";
	
	public Collection<T> findRevisions(final V primaryKey);
	
	public Collection<T> findRevisions(final List<V> primaryKeys);
}
