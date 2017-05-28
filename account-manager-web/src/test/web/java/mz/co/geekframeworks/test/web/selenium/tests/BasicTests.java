package mz.co.geekframeworks.test.web.selenium.tests;

import javax.inject.Inject;

import mz.co.geekframeworks.test.web.authentication.LoginPage;
import mz.co.geekframeworks.test.web.selenium.AbstractSeleniumTests;
import mz.co.geekframeworks.test.web.selenium.SeleniumManager;

import org.junit.Test;
import org.openqa.selenium.By;

public class BasicTests extends AbstractSeleniumTests
{
	@Inject
	private SeleniumManager seleniumManager;
	
	@Inject
	private LoginPage loginPage;
	
	@Test
	public void shouldFailLoginWithWrongCredentials()
	{
		this.seleniumManager.index();
		this.seleniumManager.getWebDriver()
		.findElement(By.id("j_idt7:username")).clear();
		this.seleniumManager.getWebDriver()
		.findElement(By.id("j_idt7:username")).sendKeys("eudson");
		this.seleniumManager.getWebDriver()
		.findElement(By.id("j_idt7:password")).clear();
		this.seleniumManager.getWebDriver()
		.findElement(By.id("j_idt7:password")).sendKeys("12345");
		this.seleniumManager.getWebDriver()
		.findElement(By.id("j_idt7:loginBtn")).click();
		this.seleniumManager.getWebDriver()
		.findElement(By.id("chooseApplication:applicationsList_label"))
		.click();
		this.seleniumManager
		.getWebDriver()
		.findElement(
				By.xpath("(//label[@id='chooseApplication:j_idt14'])[2]"))
				.click();
		this.seleniumManager.getWebDriver()
		.findElement(By.id("chooseApplication:j_idt17")).click();
		this.seleniumManager.getWebDriver().findElement(By.id("j_idt15"))
		.click();
		this.seleniumManager.getWebDriver().close();
	}
	
	@Test
	public void test()
	{
		this.loginPage.loginAs("uknown", "nopass");
	}
}
