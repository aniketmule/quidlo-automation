package pages;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import testsuite.BaseClass;


public class HomePage extends BaseClass{	

  @Test(priority = 1, description = "Homepage Test")
  public void assertHomepage(){
	  String pageTitleExpected = "Time tracking software for productive teams";
	  String pageTitleRetrieved = driver.getTitle();
	  System.out.println("Title: "+pageTitleRetrieved);
	  Assert.assertEquals(pageTitleRetrieved, pageTitleExpected);
  }
}
