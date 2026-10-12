package rf.components.family;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rf.components.BaseComponent;

import java.net.URI;

public class FamilyNav extends BaseComponent {
  private static final By ROOT_LOCATOR = By.cssSelector(".family-block__nav");
  private static final By LINK_LOCATOR = By.cssSelector(":scope > a");

  private final String currentLink;

  public FamilyNav(final WebDriver driver, final String currentLink) {
    super(driver);
    this.currentLink = currentLink;
  }

  @Override
  public By getRootLocator() {
    return ROOT_LOCATOR;
  }

  public boolean isActiveCurrentLink() {
    final var link = getCurrentLink();
    if (link != null) {
      final String className = link.getDomAttribute("class");
      return className != null && className.contains("active");
    }

    return false;
  }

  public String getCurrentLinkText() {
    final var link = getCurrentLink();
    if (link != null) {
      return link.getText();
    }

    return null;
  }

  private WebElement getCurrentLink() {
    final var links = root().findElements(LINK_LOCATOR);
    for (final var link : links) {
      final var href = link.getAttribute("href");
      if (href != null && currentLink.equals(URI.create(href).getPath())) {
        return link;
      }
    }

    return null;
  }
}
