package rf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import rf.components.shop.ShopActivateCodeModal;

import java.util.regex.Pattern;

public class ShopPage extends BasePage {
  private static final By ACTIVATE_CODE_BUTTON_LOCATOR = By.className("code_activate");

  private static final By BALANCE_LOCATOR = By.cssSelector(".donate_count > span");

  private static final Pattern BALANCE_PATTERN = Pattern.compile("([0-9]+) .*");

  public ShopPage(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/donate";
  }

  public ShopActivateCodeModal openActivateCodeModal() {
    driver.findElement(ACTIVATE_CODE_BUTTON_LOCATOR).click();
    return new ShopActivateCodeModal(driver);
  }

  public int getBalance() {
    final var balanceText = driver.findElement(BALANCE_LOCATOR).getText();
    final var matcher = BALANCE_PATTERN.matcher(balanceText);
    if (matcher.find()) {
      return Integer.parseInt(matcher.group(1));
    }

    throw new IllegalStateException(
        String.format("Не удалось извлечь баланс с помощью паттерна '%s' из текста: '%s'", BALANCE_PATTERN, balanceText)
    );
  }
}
