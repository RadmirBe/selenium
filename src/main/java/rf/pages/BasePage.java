package rf.pages;

import org.openqa.selenium.WebDriver;
import rf.BasePageObject;

public abstract class BasePage extends BasePageObject {
  protected BasePage(final WebDriver driver) {
    super(driver);
  }

  protected abstract String getPageUrl();

  public void goTo() {
    driver.get(getPageUrl());
    if (!getPageUrl().equals(driver.getCurrentUrl())) {
      throw new IllegalStateException("Страница " + driver.getCurrentUrl() + " != " + getPageUrl() + ")");
    }
  }

  public void refresh() {
    driver.navigate().refresh();
  }
}
