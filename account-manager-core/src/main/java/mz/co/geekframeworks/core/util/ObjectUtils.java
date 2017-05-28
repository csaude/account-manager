/**
 * 
 */
package mz.co.geekframeworks.core.util;

import java.lang.reflect.ParameterizedType;

/**
 * Utilitário para auxiliar na manipulação de objectos
 * 
 * @author Eudson Bambo
 *
 */
public class ObjectUtils
{
	@SuppressWarnings("unchecked")
	public static <E, T> Class<E> getClassByGenericParameterizedType(
			final Class<T> theClass)
	{
		ParameterizedType genericSuperClass = (ParameterizedType) theClass
				.getGenericSuperclass();
		
		return (Class<E>) genericSuperClass.getActualTypeArguments()[0];
	}
}
