package rf.components.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfileTabVehicles extends ProfileTabWithMultiTable {
  private static final By IMAGE_LOCATOR = By.cssSelector(".car_icon > img");

  public ProfileTabVehicles(final List<WebElement> tables) {
    super(tables);
  }

  @Override
  public ProfileTabWithTable.WithImage getTable(int index) {
    return new ProfileTabWithTable.WithImage(tables.get(index), IMAGE_LOCATOR);
  }
}
