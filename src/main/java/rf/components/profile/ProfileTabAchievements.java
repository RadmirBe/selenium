package rf.components.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProfileTabAchievements extends ProfileTabContent {
  private static final By TITLE_LOCATOR = By.cssSelector(".achievements__info .achievements__info-total:nth-child(1)");
  private static final By STATS_LOCATOR = By.cssSelector(".achievements__info .achievements__info-total:nth-child(2)");
  private static final By ACHIEVEMENTS_LOCATOR = By.cssSelector(".achievements__list .achievements__item");

  public ProfileTabAchievements(final WebElement content) {
    super(content);
  }

  public String getTitle() {
    return content.findElement(TITLE_LOCATOR).getText();
  }

  public String getStats() {
    return content.findElement(STATS_LOCATOR).getText();
  }

  public int getTotalAchievements() {
    return content.findElements(ACHIEVEMENTS_LOCATOR).size();
  }
}
