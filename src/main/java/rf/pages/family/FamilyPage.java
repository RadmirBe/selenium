package rf.pages.family;

import org.openqa.selenium.WebDriver;
import rf.components.family.FamilyNav;
import rf.pages.BasePage;

public abstract class FamilyPage extends BasePage {
  private final FamilyNav nav;

  public FamilyPage(final WebDriver driver, final String currentLink) {
    super(driver);
    this.nav = new FamilyNav(driver, currentLink);
  }

  public FamilyNav getNav() {
    return nav;
  }
}
