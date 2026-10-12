package rf.components.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rf.components.BaseComponent;

public class ProfileTabs extends BaseComponent {
  private static final By ROOT_LOCATOR = By.cssSelector(".stats_links");
  private static final By LINKS_LOCATOR = By.cssSelector(":scope > a");
  private static final By LINK_BLOCK_LOCATOR = By.cssSelector(":scope > li");

  public ProfileTabs(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public int getCount() {
    return findElements(LINKS_LOCATOR).size();
  }

  public boolean isActiveTab(final ProfileTab tab) {
    final var link = wait.until(__ -> findLinkBlock(tab));
    final String classes = link.getAttribute("class");
    return classes != null && classes.contains("active");
  }

  public void select(final ProfileTab tab) {
    final var link = wait.until(__ -> findLinkBlock(tab));
    link.click();
  }

  private WebElement findLinkBlock(final ProfileTab tab) {
    return findElements(LINKS_LOCATOR)
        .stream()
        .map(e -> e.findElement(LINK_BLOCK_LOCATOR))
        .filter(e -> tab.title.equals(e.getText().trim()))
        .findFirst()
        .orElse(null);
  }
}
