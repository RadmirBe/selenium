package rf.pages.family;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FamilyEstatePage extends FamilyPage {
  private static final By INFO_TITLE_LOCATOR = By.cssSelector(".gta-title:nth-of-type(1)");

  private static final By TOTAL_PRICE_TITLE_LOCATOR = By.cssSelector(".family-estate__total > span");
  private static final By TOTAL_PRICE_VALUE_LOCATOR = By.cssSelector(".family-estate__total > span > b");

  private static final By TABLE_LOCATOR = By.cssSelector(".family-block table.table");
  private static final By TABLE_HEADERS_LOCATOR = By.cssSelector(".family-block table.table thead th");

  private static final By TABLE_FIRST_OWNER_LOCATOR = By.cssSelector("tbody > tr:first-child > td:first-child");

  public FamilyEstatePage(final WebDriver driver) {
    super(driver, "/family/estate");
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/family/estate";
  }

  public String getInfoTitle() {
    return getVisibleElement(INFO_TITLE_LOCATOR).getText();
  }

  public String getTotalPriceTitle() {
    return getVisibleElement(TOTAL_PRICE_TITLE_LOCATOR).getText();
  }

  public String getTotalPriceValue() {
    return getVisibleElement(TOTAL_PRICE_VALUE_LOCATOR).getText();
  }

  public boolean isTableVisible() {
    return getVisibleElement(TABLE_LOCATOR).isDisplayed();
  }

  public String[] getTableHeaders() {
    final var tableHeaders = getVisibleElements(TABLE_HEADERS_LOCATOR);
    final String[] result = new String[tableHeaders.size()];
    for (int i = 0; i < tableHeaders.size(); i++) {
      result[i] = tableHeaders.get(i).getText();
    }

    return result;
  }

  public String getTableFirstOwner() {
    return getVisibleElement(TABLE_FIRST_OWNER_LOCATOR).getText();
  }
}
