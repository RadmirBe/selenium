package rf.components.profile;

import org.openqa.selenium.WebElement;

public abstract class ProfileTabContent {
  protected final WebElement content;

  protected ProfileTabContent(final WebElement content) {
    this.content = content;
  }
}
