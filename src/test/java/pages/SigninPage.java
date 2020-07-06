package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import testsuite.BaseClass;
import utilities.Utilities;

public class SigninPage extends BaseClass{
	 Utilities config = new Utilities();
	 WebElement signinButton;

	@Test(priority = 5, description = "Sign in with Valid email ID")
	public void signinWithValidEmail() {
		
		signinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Signin-style__center___YfGiy']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("input")));
		WebElement emailTextBox = driver.findElements(By.name("input")).get(0);
		WebElement passwordTextBox = driver.findElements(By.name("input")).get(1);
		emailTextBox.sendKeys(Keys.CONTROL,"a");
		emailTextBox.sendKeys(Keys.DELETE);
		passwordTextBox.sendKeys(Keys.CONTROL,"a");
		passwordTextBox.sendKeys(Keys.DELETE);
		emailTextBox.sendKeys(config.getEmail());
		passwordTextBox.sendKeys("Testing123");
		wait.until(ExpectedConditions.elementToBeClickable(signinButton));
		signinButton.click();
		WebElement avatarText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Upload your avatar')]")));
		Assert.assertNotNull(avatarText);
		
	}
	@Test(priority = 4, description = "Sign in with Valid email ID")
	public void signinWithInvalidEmail() {
		driver.get("https://auth.quidlo.com/signin");
		WebElement emailTextBox = driver.findElements(By.name("input")).get(0);
		WebElement passwordTextBox = driver.findElements(By.name("input")).get(1);
		emailTextBox.sendKeys("rrdgrdgrdg@adddsds.com");
		passwordTextBox.sendKeys("Testing123");
		signinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Signin-style__center___YfGiy']")));
		wait.until(ExpectedConditions.elementToBeClickable(signinButton));
		signinButton.click();
		WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Sign in not successful')]")));
		Assert.assertNotNull(errorText);
	}
	
}
