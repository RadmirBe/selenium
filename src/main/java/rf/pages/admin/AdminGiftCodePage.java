package rf.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rf.components.admin.GiftCodeCreationModal;
import rf.pages.BasePage;

import java.util.regex.Pattern;

public class AdminGiftCodePage extends BasePage {
  private static final By TABLE_LOCATOR = By.tagName("table");
  private static final By BUTTON_CREATE_LOCATOR = By.xpath("//button[contains(text(),'Создать')]");

  private static final Pattern CODE_PATTERN = Pattern.compile("Подарочный код '([^']+)' добавлен\\.");

  private static final String CODE_IN_TABLE_XPATH = "//td[normalize-space()='%s']";

  public AdminGiftCodePage(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/admin/promos";
  }

  public boolean isTableVisible() {
    return getVisibleElement(TABLE_LOCATOR).isDisplayed();
  }

  public boolean isButtonCreateVisible() {
    return getVisibleElement(BUTTON_CREATE_LOCATOR).isDisplayed();
  }

  public GiftCodeCreationModal openCreationModal() {
    getVisibleElement(BUTTON_CREATE_LOCATOR).click();
    return new GiftCodeCreationModal(driver);
  }

  public String parseGeneratedCodeFromAlert(final String text) {
    final var match = CODE_PATTERN.matcher(text);
    if (match.find()) {
      return match.group(1);
    }

    return null;
  }

  public boolean hasCodeInTable(final String code) {
    return driver.findElement(By.xpath(CODE_IN_TABLE_XPATH.formatted(code))).isDisplayed();
  }
}
