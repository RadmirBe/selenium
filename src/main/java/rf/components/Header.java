package rf.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BaseComponent {
  private static final By HEADER_LOCATOR = By.cssSelector("body > .wrapper > header");
  private static final By AUTH_BUTTON_LOCATOR = By.cssSelector(".auth_block > a");

  public Header(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return HEADER_LOCATOR;
  }

  public String getAuthTitle() {
    return findElement(AUTH_BUTTON_LOCATOR).getText();
  }

  public LoginModal openLoginModal() {
    root().findElement(AUTH_BUTTON_LOCATOR).click();
    return new LoginModal(driver);
  }
}
