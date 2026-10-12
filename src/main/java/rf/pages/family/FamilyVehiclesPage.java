package rf.pages.family;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FamilyVehiclesPage extends FamilyPage {
  private static final By INFO_TITLE_LOCATOR = By.cssSelector(".family-block > .row > .col-lg-9 > .gta-title");

  private static final By TOTAL_PRICE_LOCATOR = By.cssSelector(".family-vehicles__total");

  private static final By TABLE_LOCATOR = By.cssSelector(".family-block table.table");
  private static final By TABLE_HEADERS_LOCATOR = By.cssSelector(".family-block table.table thead th");

  private static final By TABLE_FIRST_OWNER_LOCATOR = By.cssSelector("tbody > tr:first-child > td:first-child");

  public FamilyVehiclesPage(final WebDriver driver) {
    super(driver, "/family/vehicles");
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/family/vehicles";
  }

  public String getInfoTitle() {
    return getVisibleElement(INFO_TITLE_LOCATOR).getText();
  }

  public String getTotalPriceText() {
    return getVisibleElement(TOTAL_PRICE_LOCATOR).getText();
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
