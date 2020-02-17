package Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	public String Url ="https://expense-tracker-demo-1.firebaseapp.com/login";
	public WebDriver driver;
	/* Launching the Browser*/
@BeforeTest                            
    public void launchBrowser() {
       driver = new ChromeDriver();
       driver.manage().window().maximize();	 
       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
       driver.get(Url);
      }

	/* Automated test for Login (TC01)*/
 @Test
     public void f() throws Exception {
	    String handle= driver.getWindowHandle();
	    System.out.println("oldhandle"+handle);
	    driver.findElement(By.xpath("/html/body/div/main/div/a")).click();
	    Set handles=driver.getWindowHandles();
	    for (String handle1 : driver.getWindowHandles())
	      {
		    System.out.println("new handle"+handle1);
		    driver.switchTo().window(handle1);
	      }
	    driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("testdemoemail12@gmail.com");
	    driver.findElement(By.xpath("//div[@id='identifierNext']")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@type='password']")).sendKeys("arn123456789");
	    driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
	    Thread.sleep(6000);
	    driver.switchTo().window(handle);
	    System.out.println("Logged in Successfully");	
      }
 
 /* Automated test for Profile Settings (TC04)*/
 @Test
	 public void g() throws Exception {
	    driver.get("https://expense-tracker-demo-1.firebaseapp.com/settings");
	    Select drpdown= new Select(driver.findElement(By.id("grid-language")));
        drpdown.selectByVisibleText("English");
        Select drpdown1= new Select(driver.findElement(By.id("grid-account")));
        drpdown1.selectByVisibleText("EUR - Euro");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@class='circle_nav__toggle']")).click();
	System.out.println("Profile has been successfully updated");	 
        Thread.sleep(3000);
	 }
 
 /* Automated test for Account Creation (TC06)*/
@Test
	 public void h() throws Exception {
        Actions actions = new Actions(driver);
        WebElement Home=driver.findElement(By.xpath("//a[@title='Home']"));
        actions.moveToElement(Home).click().perform();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        WebElement Add=driver.findElement(By.xpath("//a[@title='Add']"));
        actions.moveToElement(Add).click().perform();
        driver.findElement(By.id("grid-name")).sendKeys("Test");   
        Select drpdown2= new Select(driver.findElement(By.id("grid-type")));
        Thread.sleep(3000);
        drpdown2.selectByVisibleText("Debit Card");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Actions actions1 = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        WebElement Account=driver.findElement(By.xpath("//div[@class='flex-1 flex flex-col justify-center items-start']"));
        actions1.moveToElement(Account).click().perform();
        System.out.println("Account Created");	 
        Thread.sleep(3000);
	 }

/* Automated test for creating entry (TC08)*/
@Test
	 public void i() throws Exception {
        driver.findElement(By.xpath("//a[@class='flex justify-between items-center bg-expense p-4 mt-8 text-white no-underline']")).click();
        driver.findElement(By.id("grid-amount")).sendKeys("100");
        driver.findElement(By.id("grid-category")).sendKeys("Demo");
        Actions actions2 = new Actions(driver);
        WebElement Category=driver.findElement(By.xpath("/html/body/div/main/div/div[2]/form/div[2]/div/div/div[3]/ul/li[1]/span"));
        actions2.moveToElement(Category).click().perform();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
	System.out.println("Added an entry");	 
	 }

/* Automated test for deleting entry (TC13)*/
@Test
	 public void j() throws Exception{
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='flex flex-col items-center']")).click();
        Actions actions3 = new Actions(driver);
        Thread.sleep(3000);
        WebElement Delete=driver.findElement(By.xpath("//button[@class='w-6 h-6 flex']"));
        actions3.moveToElement(Delete).click().perform();
	System.out.println("Deleted the entry");	 
     }

/* Automated test for Deleting Account (TC14)*/
@Test
	 public void k() throws Exception{
        Actions actions4 = new Actions(driver);
        WebElement DltAcct=driver.findElement(By.xpath("//a[@class='block w-6 h-6 cursor-pointer mr-2']"));
        actions4.moveToElement(DltAcct).click().perform();
        System.out.println("Deleted the existing Account");	 
        Thread.sleep(3000);
  }
	 
  /* Logout from the account and closing the browser*/
  @AfterTest
  public void quitBrowser() throws Exception {
	  Actions actions4 = new Actions(driver);
	  WebElement prfile=driver.findElement(By.xpath("//a[@class='block cursor-pointer flex items-center']"));
	  actions4.moveToElement(prfile).click().perform();
	  Thread.sleep(3000);
	  WebElement logout=driver.findElement(By.xpath("//a[@title='Logout']"));
	  actions4.moveToElement(logout).click().perform();
	  driver.quit();
	  
  }
}
