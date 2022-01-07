package profilosistema;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class TestLogin {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "test/profilosistema/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testLoginFallitoEntrambi() {
    // Test name: TestLoginFallitoEntrambi
    // Step # | name | target | value | comment
    // 1 | open | http://localhost:8080/RAAF-GAMING/servletloginfirst |  | 
    driver.get("http://localhost:8080/RAAF-GAMING/servletloginfirst");
    // 2 | click | name=email |  | 
    driver.findElement(By.name("email")).click();
    // 3 | type | name=email | abc@gmail.com | 
    driver.findElement(By.name("email")).sendKeys("abc@gmail.com");
    // 4 | click | name=password |  | 
    driver.findElement(By.name("password")).click();
    // 5 | type | name=password | abcdesfg123 | 
    driver.findElement(By.name("password")).sendKeys("abcdesfg123");
    // 6 | click | css=.invio |  | 
    driver.findElement(By.cssSelector(".invio")).click();
    // 7 | assertText | name=messaggioerrore | Email/Password errata! | 
    assertThat(driver.findElement(By.name("messaggioerrore")).getText(), is("Email/Password errata!"));
  }
  @Test
  public void testLoginFallitoPassword() {
    // Test name: TestLoginFallitoPassword
    // Step # | name | target | value | comment
    // 1 | open | http://localhost:8080/RAAF-GAMING/servletloginfirst |  | 
    driver.get("http://localhost:8080/RAAF-GAMING/servletloginfirst");
    // 2 | click | name=email |  | 
    driver.findElement(By.name("email")).click();
    // 3 | type | name=email | f.peluso25@gmail.com | 
    driver.findElement(By.name("email")).sendKeys("f.peluso25@gmail.com");
    // 4 | click | name=password |  | 
    driver.findElement(By.name("password")).click();
    // 5 | type | name=password | veloce1234 | 
    driver.findElement(By.name("password")).sendKeys("veloce1234");
    // 6 | click | css=.invio |  | 
    driver.findElement(By.cssSelector(".invio")).click();
    // 7 | assertText | name=messaggioerrore | Email/Password errata! | 
    assertThat(driver.findElement(By.name("messaggioerrore")).getText(), is("Email/Password errata!"));
  }
}
