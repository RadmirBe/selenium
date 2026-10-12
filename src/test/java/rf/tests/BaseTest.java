package rf.tests;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
  protected WebDriver driver;

  public WebElement getLocatedElement(WebDriver driver, By by) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(d -> driver.findElement(by));
  }

  public WebElement getLocatedElement(WebElement element, By by) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(d -> element.findElement(by));
  }

  public WebElement getLocatedElementNotEmpty(WebElement element, By by) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(d -> {
      var el = element.findElement(by);
      return el.getText().isBlank() ? null : el;
    });
  }

  protected FirefoxDriver startFirefoxDriver() {
    return startFirefoxDriver(new FirefoxOptions());
  }

  protected FirefoxDriver startFirefoxDriver(FirefoxOptions options) {
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    driver = new FirefoxDriver(options);
    return (FirefoxDriver) driver;
  }


  protected ChromeDriver startChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    options.addArguments("--headless=new");
    options.addArguments("--window-size=1600,900");
    return startChromeDriver(options);
  }

  protected ChromeDriver startChromeDriver(ChromeOptions options) {
    driver = new ChromeDriver(options);
    return (ChromeDriver) driver;
  }

  protected static ChromeOptions getDefaultChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    return options;
  }

  @AfterEach
  public void quit() {
    if (driver != null) {
      driver.quit();
    }
  }
}
