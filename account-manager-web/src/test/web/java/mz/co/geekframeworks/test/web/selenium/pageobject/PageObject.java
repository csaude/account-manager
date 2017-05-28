package mz.co.geekframeworks.test.web.selenium.pageobject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import mz.co.geekframeworks.test.web.selenium.SeleniumManager;

import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Classe mãe de todas classes que represenção uma pagina.
 * 
 * @author Eudson Bambo
 */
public abstract class PageObject implements InitializingBean
{
	private final Map<String, String> values = new HashMap<String, String>();
	
	@Inject
	private SeleniumManager seleniumManager;
	
	protected String getValue(final String key)
	{
		return this.values.get(key);
	}
	
	protected PageObject setValue(final String key, final String value)
	{
		this.values.put(key, value);
		
		return this;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception
	{
		PageFactory.initElements(this.seleniumManager.getWebDriver(), this);
	}
}
