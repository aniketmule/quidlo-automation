package testsuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import utilities.Utilities;
 



public class BaseClass{
	 public static WebDriver driver;
	 public static WebDriverWait wait;
	 public String projectPath,chromeDriverPath, firefoxDriverPath;
	 Utilities config = new Utilities();
	 
	 
	 @BeforeSuite
	 public void configDriver() {
		projectPath = System.getProperty("user.dir");
		chromeDriverPath = projectPath+"/src/test/resources/drivers/chromedriver.exe";
		firefoxDriverPath = projectPath+"/src/test/resources/drivers/geckodriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		config.getConfigProperties();
		driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(Long.parseLong(config.defaultImplicitTime), TimeUnit.SECONDS);
    	driver.get("http://www.quidlo.com");
    	driver.manage().window().maximize();
    	wait = new WebDriverWait(driver,20);
	 }
	 	 
	 @AfterSuite
	 public void tearDownDriver() {
		 System.out.println("Quitting Driver....");
		 driver.quit();
	 }
}