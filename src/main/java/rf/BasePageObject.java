package rf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePageObject {
  protected final WebDriver driver;

  protected final WebDriverWait wait;

  public BasePageObject(final WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(getWaitSeconds()));
  }

  protected WebElement getVisibleElement(final By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected List<WebElement> getVisibleElements(final By locator) {
    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  protected WebElement getElement(final By locator) {
    return driver.findElement(locator);
  }

  protected List<WebElement> getElements(final By locator) {
    return driver.findElements(locator);
  }

  protected void waitStalenessOf(final WebElement element) {
    wait.until(ExpectedConditions.stalenessOf(element));
  }

  protected long getWaitSeconds() {
    return 15;
  }
}
