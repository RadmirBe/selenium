package rf.components.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rf.components.BaseComponent;

public class AdminToast extends BaseComponent {
  private static final By ROOT_LOCATOR = By.xpath("//div[@role='alert']");

  public AdminToast(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public String getText() {
    return root().getText();
  }
}
