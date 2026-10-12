package rf.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginModal extends BaseComponent {
  private static final By ROOT_LOCATOR = By.cssSelector(".modal_wrapper.active .modal_block.active");
  private static final By INPUT_NAME_LOCATOR = By.cssSelector("input[name=\"name\"]");
  private static final By INPUT_PASSWORD_LOCATOR = By.cssSelector("input[name=\"password\"]");
  private static final By SUBMIT_LOCATOR = By.tagName("button");
  private static final By CLOSE_LOCATOR = By.className("login_close");

  public LoginModal(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public void fillName(final String name) {
    findElement(INPUT_NAME_LOCATOR).sendKeys(name);
  }

  public void fillPassword(final String password) {
    findElement(INPUT_PASSWORD_LOCATOR).sendKeys(password);
  }

  public void submit(final boolean isValid) {
    final var root = root();
    root.findElement(SUBMIT_LOCATOR).click();

    if (isValid) {
      waitStalenessOf(root);
    }
  }

  public void loginAsValid(final String username, final String password) {
    fillName(username);
    fillPassword(password);
    submit(true);
  }

  public void loginAsInvalid(final String username, final String password) {
    fillName(username);
    fillPassword(password);
    submit(false);
  }

  public void close() {
    findElement(CLOSE_LOCATOR).click();
  }

  public boolean isOpened() {
    return root().isDisplayed();
  }

  public boolean isClosed() {
    return driver.findElements(getRootLocator()).isEmpty();
  }
}
