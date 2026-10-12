package rf.components.family;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import rf.components.BaseMultiComponent;

import java.util.List;

public class FamilyInfo extends BaseMultiComponent {
  private static final By ROOT_LOCATOR = By.cssSelector(".family-grid > .family-grid__block");

  private static final By BLOCK_PART_LOCATOR = By.cssSelector(":scope > div");
  private static final By TITLE_LOCATOR = By.tagName("h6");
  private static final By VALUE_LOCATOR = By.tagName("h1");

  public FamilyInfo(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public int getCountBlocks() {
    return root().size();
  }

  public String getBlockValue(final String title) {
    return root()
        .stream()
        .map(e -> e.findElements(BLOCK_PART_LOCATOR))
        .flatMap(List::stream)
        .filter(e -> e.findElement(TITLE_LOCATOR).getText().startsWith(title))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("Элемент '" + title + "' не найден"))
        .findElement(VALUE_LOCATOR)
        .getText();
  }
}
