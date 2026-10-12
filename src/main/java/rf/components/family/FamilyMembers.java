package rf.components.family;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rf.components.BaseComponent;

import java.util.List;

public class FamilyMembers extends BaseComponent {
  private static final By ROOT_LOCATOR = By.cssSelector(".gta-title:nth-of-type(2) + .card table.table");

  private static final By HEADERS_LOCATOR = By.cssSelector("thead > tr > th");
  private static final By ROWS_LOCATOR = By.cssSelector("tbody > tr");
  private static final By VALUES_LOCATOR = By.cssSelector("tbody > tr > td");

  public FamilyMembers(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public int getCount() {
    return root()
        .findElements(ROWS_LOCATOR)
        .size();
  }

  public List<String> getTableHeaders() {
    return root().findElements(HEADERS_LOCATOR)
        .stream()
        .map(WebElement::getText)
        .toList();
  }

  public boolean hasPlayer(final String name) {
    return root().findElements(VALUES_LOCATOR)
        .stream()
        .anyMatch(e -> e.getText().equals(name));
  }
}
