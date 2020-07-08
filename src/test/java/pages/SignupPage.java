package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testsuite.BaseClass;
import utilities.Utilities;

public class SignupPage extends BaseClass{
	 Utilities config = new Utilities();
	 WebElement emailId;


	@Test(priority = 2, description = "Checking sign-up page")
	public void testSignupPage() throws InterruptedException{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		driver.get("https://auth.quidlo.com/signup");
		String signupPageTitleExpected = "Timesheets";
		String signupPageTitleRetrieved = driver.getTitle();
		Assert.assertEquals(signupPageTitleRetrieved, signupPageTitleExpected);

		js.executeScript("window.open(arguments[0])", "https://10minutemail.com/");
		System.out.print("New window opened");
		Set<String> windows = driver.getWindowHandles();
		int windowSize = windows.size();
		List<String> windowItems = new ArrayList<>(windowSize);
		for (String x: windows) {
			windowItems.add(x);
		}
		driver.switchTo().window(windowItems.get(1));
		System.out.println("CurrentUrl: "+driver.getCurrentUrl());
		String EmailIdText = "";
		try {
			while (EmailIdText.length()<1) {
				emailId = driver.findElement(By.id("mail_address"));
				EmailIdText = emailId.getAttribute("value");
//			Thread.sleep(3000);
			}
			System.out.println("Email: "+EmailIdText);
			config.setEmail(EmailIdText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().window(windowItems.get(0));
		
		WebElement inputs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("input")));
		
		WebElement emailTextBox = driver.findElements(By.name("input")).get(0);		
		WebElement passwordTextBox = driver.findElements(By.name("input")).get(1);
		
		
		WebElement termsAndConditionsBox = driver.findElement(By.className("Checkbox-style__check___e8GGP"));
		WebElement signUpButton = driver.findElement(By.className("Signup-style__center___3WLhk"));
		String successXpath = "//div[contains(text(),'Thank you for signing up!')]";
		
		emailTextBox.sendKeys(EmailIdText);
		passwordTextBox.sendKeys("Testing123");
		termsAndConditionsBox.click();
		wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
		signUpButton.click();			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(successXpath)));
		System.out.println("Signup page has been tested");
		driver.close();
		driver.switchTo().window(windowItems.get(1));
	
	}
	@Test(priority = 3, description = "Testing Activation Email")
	public void activationEmailChecker() throws InterruptedException {
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,Keys.END);
		String emailSubjectXpath = "//div[@class='small_subject']//span";
		WebElement emailSubject = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailSubjectXpath)));
		emailSubject.click();
		WebElement activationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("ACTIVATE ACCOU")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", activationButton);
		activationButton.click();		
		WebElement singupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Signin-style__center___YfGiy']")));
		Assert.assertNotNull(singupButton);
	}
}
