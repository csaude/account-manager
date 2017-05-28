package mz.co.geekframeworks.test.web.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Classe onde estão as configurações gerais para os testes web(funcionais)
 * automatizados.
 * 
 * @author Eudson Bambo
 */
@Component
public class SeleniumManager implements InitializingBean
{
	private WebDriver webDriver;
	private String baseUrl = "http://localhost:8080/account-manager-web";
	private Window window;
	
	@Override
	public void afterPropertiesSet() throws Exception
	{
		this.webDriver = new FirefoxDriver();
		this.webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.window = this.webDriver.manage().window();
	}
	
	public void index()
	{
		this.webDriver.get(this.baseUrl);
		this.window.maximize();
	}
	
	public WebDriver getWebDriver()
	{
		return this.webDriver;
	}
	
	public void setWebDriver(final WebDriver baseDriver)
	{
		this.webDriver = baseDriver;
	}
	
	public String getBaseUrl()
	{
		return this.baseUrl;
	}
	
	public void setBaseUrl(final String baseUrl)
	{
		this.baseUrl = baseUrl;
	}
}
