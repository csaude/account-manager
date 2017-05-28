package mz.co.geekframeworks.test.web.authentication;

import javax.inject.Inject;

import mz.co.geekframeworks.test.web.selenium.SeleniumManager;
import mz.co.geekframeworks.test.web.selenium.pageobject.PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * PageObject que representa a tela de Login o sistema.
 * 
 * @author Eudson Bambo
 */
@Component
public class LoginPage extends PageObject
{
	@FindBy(id = "loginMessage")
	private WebElement messagePanel;
	
	@FindBy(id = "username")
	private WebElement userNameBox;
	
	@FindBy(id = "password")
	private WebElement passwordBox;
	
	@FindBy(id = "loginBtn")
	private WebElement loginBtn;
	
	@Inject
	private SeleniumManager seleniumManager;
	
	public ChooseApplicationPage loginAs(final String userName,
			final String password)
	{
		this.seleniumManager.index();
		
		this.userNameBox.sendKeys(userName);
		this.passwordBox.sendKeys(password);
		
		this.loginBtn.click();
		
		return null;
	}
	
	public WebElement getMessagePanel()
	{
		return this.messagePanel;
	}
}
