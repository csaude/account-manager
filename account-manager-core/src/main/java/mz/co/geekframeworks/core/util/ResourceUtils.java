package mz.co.geekframeworks.core.util;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Utilitário para tratar de localização de resources.
 * 
 * Baseado em @see org.springframework.util.ResourceUtils
 * 
 * @author Eudson Bambo
 * 
 */
public abstract class ResourceUtils
{
	public static final String CLASSPATH_URL_PREFIX = "classpath:";
	
	public static final String FILE_URL_PREFIX = "file:";
	
	public static URL getResource(final String resourceName)
			throws FileNotFoundException
	{
		URL url = null;
		
		if (resourceName == null)
		{
			throw new IllegalArgumentException("Resource must not be null");
		}
		
		if (resourceName.startsWith(CLASSPATH_URL_PREFIX))
		{
			String path = resourceName.substring(CLASSPATH_URL_PREFIX.length());
			url = ResourceUtils.getResourceURL(path);
			
			if (url == null)
			{
				throw new FileNotFoundException("Resource file [" + path
						+ "] does not exists");
			}
		}
		
		return url;
	}
	
	/**
	 * @return the resource file from classpath as URL.
	 */
	private static URL getResourceURL(final String resourceName)
	{
		return ResourceUtils.class.getResource(resourceName);
	}
}