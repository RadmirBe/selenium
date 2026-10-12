package rf.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Toast extends BaseComponent{
  private static final By ROOT_LOCATOR = By.id("swal2-content");

  public Toast(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public String getText() {
    return root().getText();
  }

  public void waitForRefresh() {
    waitStalenessOf(driver.findElement(getRootLocator()));
  }
}
