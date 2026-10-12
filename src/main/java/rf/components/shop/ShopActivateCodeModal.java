package rf.components.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rf.components.BaseComponent;

import java.util.regex.Pattern;

public class ShopActivateCodeModal extends BaseComponent {
  private static final By ROOT_LOCATOR = By.cssSelector(".modal_block.active");

  private static final By INPUT_LOCATOR = By.xpath("//input[@placeholder='Введите код']");
  private static final By BUTTON_SUBMIT_LOCATOR = By.id("send-code");

  private static final Pattern SUCCESS_PATTERN = Pattern.compile("Вам добавлено [0-9]+ донат поинтов");

  public ShopActivateCodeModal(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public boolean isVisible() {
    return root().isDisplayed();
  }

  public void fillInput(final String input) {
    driver.findElement(INPUT_LOCATOR).sendKeys(input);
  }

  public String getInputValue() {
    return driver.findElement(INPUT_LOCATOR).getAttribute("value");
  }

  public void submit() {
    driver.findElement(BUTTON_SUBMIT_LOCATOR).click();
  }

  public boolean isSuccessResult(final String result) {
    return SUCCESS_PATTERN.matcher(result).matches();
  }
}
