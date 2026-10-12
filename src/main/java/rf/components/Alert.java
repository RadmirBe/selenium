package rf.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Alert extends BaseComponent{
  private static final By ROOT_LOCATOR = By.cssSelector(".alert");
  private static final By CLOSE_LOCATOR = By.tagName("button");

  public Alert(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public String getText() {
    return root().getText();
  }

  public void close() {
    root().findElement(CLOSE_LOCATOR).click();
  }

  public boolean isVisible() {
    return !driver.findElements(getRootLocator()).isEmpty();
  }
}
