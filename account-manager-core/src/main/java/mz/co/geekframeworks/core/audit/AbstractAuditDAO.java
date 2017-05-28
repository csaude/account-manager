package mz.co.geekframeworks.core.audit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mz.co.geekframeworks.core.util.ObjectUtils;
import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Eudson Bambo
 * @param <T>
 * @param <PrimaryKeyType>
 *
 */
@Repository(AuditDAO.NAME)
public abstract class AbstractAuditDAO<T extends GenericEntity, PrimaryKeyType extends Serializable>
		implements AuditDAO<T, PrimaryKeyType>
{
	@PersistenceContext
	private EntityManager entityManager;
	
	private final Class<T> entityClass;
	
	public AbstractAuditDAO()
	{
		this.entityClass = ObjectUtils.getClassByGenericParameterizedType(this
				.getClass());
	}
	
	@Override
	public Collection<T> findRevisions(final PrimaryKeyType primaryKey)
	{
		List<T> result = new ArrayList<T>();
		
		AuditReader reader = AuditReaderFactory.get(this.entityManager);
		
		List<Number> revisions = reader.getRevisions(this.entityClass,
				primaryKey);
		
		for (Number number : revisions)
		{
			T revision = reader.find(this.entityClass, primaryKey, number);
			
			result.add(revision);
		}
		
		return result;
	}
	
	@Override
	public Collection<T> findRevisions(final List<PrimaryKeyType> primaryKeys)
	{
		List<T> results = new ArrayList<T>();
		
		AuditReader reader = AuditReaderFactory.get(this.entityManager);
		
		for (PrimaryKeyType id : primaryKeys)
		{
			List<Number> revisions = reader.getRevisions(this.entityClass, id);
			
			for (Number number : revisions)
			{
				T revision = reader.find(this.entityClass, id, number);
				
				results.add(revision);
			}
		}
		
		return results;
	}
}
