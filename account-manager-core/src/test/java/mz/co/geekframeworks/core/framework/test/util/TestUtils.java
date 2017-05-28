package mz.co.geekframeworks.core.framework.test.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import mz.co.mozview.frameworks.core.model.GenericEntity;

/**
 * Utilitário para testes de unidade
 * 
 * @author Eudson Bambo
 */
public class TestUtils
{
	
	public static <T extends GenericEntity> void assertCreation(final T entity)
	{
		assertNotNull(entity.getId());
		assertNotNull(entity.getCreatedAt());
		assertNotNull(entity.getCreatedBy());
		assertNull(entity.getUpdatedAt());
		assertNull(entity.getUpdatedBy());
	}
	
	public static <T extends GenericEntity> void assertUpdate(final T entity)
	{
		assertNotNull(entity.getId());
		assertNotNull(entity.getCreatedAt());
		assertNotNull(entity.getCreatedBy());
		assertNotNull(entity.getUpdatedAt());
		assertNotNull(entity.getUpdatedBy());
	}
}
