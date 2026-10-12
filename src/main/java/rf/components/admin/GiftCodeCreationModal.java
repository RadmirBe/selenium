package rf.components.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rf.components.BaseComponent;

public class GiftCodeCreationModal extends BaseComponent {
  private static final By ROOT_LOCATOR = By.id("modal-create");

  private static final By SUBMIT_LOCATOR = By.xpath("//footer[@class='modal-footer']//button[contains(text(),'Создать')]");
  private static final By CLOSE_LOCATOR = By.xpath("//button[@aria-label='Close']");

  public GiftCodeCreationModal(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public boolean isVisible() {
    return !driver.findElements(getRootLocator()).isEmpty();
  }

  public void submit() {
    getVisibleElement(SUBMIT_LOCATOR).click();
  }

  public void close() {
    getVisibleElement(CLOSE_LOCATOR).click();
  }
}
