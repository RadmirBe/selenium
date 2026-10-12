package rf.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rf.BasePageObject;

import java.util.List;

public abstract class BaseComponent extends BasePageObject {
  public BaseComponent(final WebDriver driver) {
    super(driver);
  }

  public WebElement root() {
    return getVisibleElement(getRootLocator());
  }

  public WebElement findElement(final By locator) {
    return root().findElement(locator);
  }

  public List<WebElement> findElements(final By locator) {
    return root().findElements(locator);
  }

  protected abstract By getRootLocator();
}
