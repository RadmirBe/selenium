package rf.components.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProfileTabWithTable extends ProfileTabContent {
  private static final By ROW_LOCATOR = By.cssSelector(".stats_list > li");
  private static final By COLUMN_LOCATOR = By.className("column");
  private static final By VALUE_LOCATOR = By.className("value");

  public ProfileTabWithTable(final WebElement content) {
    super(content);
  }

  public int getRowsCount() {
    return content.findElements(ROW_LOCATOR).size();
  }

  public String getRowTitle(final int rowNumber) {
    return content.findElements(ROW_LOCATOR).get(rowNumber).findElement(COLUMN_LOCATOR).getText();
  }

  public String getRowValue(final int rowNumber) {
    return content.findElements(ROW_LOCATOR).get(rowNumber).findElement(VALUE_LOCATOR).getText();
  }

  public boolean isRowValueDisplayed(final int rowNumber) {
    return content.findElements(ROW_LOCATOR).get(rowNumber).findElement(VALUE_LOCATOR).isDisplayed();
  }

  public static class WithImage extends ProfileTabWithTable {
    private final By imageLocator;

    public WithImage(final WebElement content, final By imageLocator) {
      super(content);
      this.imageLocator = imageLocator;
    }

    public boolean isImageDisplayed() {
      return content.findElement(imageLocator).isDisplayed();
    }
  }
}
