package rf.components.profile;

import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfileTabWithMultiTable {
  protected final List<WebElement> tables;

  public ProfileTabWithMultiTable(final List<WebElement> tables) {
    this.tables = tables;
  }

  public int getTablesCount() {
    return tables.size();
  }

  public ProfileTabWithTable getTable(final int index) {
    return new ProfileTabWithTable(tables.get(index));
  }
}
