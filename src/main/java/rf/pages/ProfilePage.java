package rf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rf.components.profile.ProfileTab;
import rf.components.profile.ProfileTabAchievements;
import rf.components.profile.ProfileTabVehicles;
import rf.components.profile.ProfileTabWithTable;
import rf.components.profile.ProfileTabs;

public class ProfilePage extends BasePage {
  private static final By TITLE_LOCATOR = By.cssSelector(".profile_block .header_block > .title");
  private static final By CONTENT_IMG_LOCATOR = By.cssSelector(".map_house > img");

  private static final By VEHICLES_LOCATOR = By.className("car_block");

  public ProfilePage(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/s1/u1";
  }

  public String getTitle() {
    return getVisibleElement(TITLE_LOCATOR).getText();
  }

  public ProfileTabs getProfileTabs() {
    return new ProfileTabs(driver);
  }

  public ProfileTabWithTable getStatisticsContent() {
    return new ProfileTabWithTable(getVisibleElement(ProfileTab.STATISTICS.contentSelector));
  }

  public ProfileTabAchievements getAchievementsContent() {
    return new ProfileTabAchievements(getVisibleElement(ProfileTab.ACHIEVEMENTS.contentSelector));
  }

  public ProfileTabWithTable.WithImage getHousesContent() {
    return new ProfileTabWithTable.WithImage(getVisibleElement(ProfileTab.HOUSES.contentSelector), CONTENT_IMG_LOCATOR);
  }

  public ProfileTabWithTable.WithImage getGarageContent() {
    return new ProfileTabWithTable.WithImage(getVisibleElement(ProfileTab.GARAGE.contentSelector), CONTENT_IMG_LOCATOR);
  }

  public ProfileTabWithTable.WithImage getBusinessContent() {
    return new ProfileTabWithTable.WithImage(getVisibleElement(ProfileTab.BUSINESS.contentSelector), CONTENT_IMG_LOCATOR);
  }

  public ProfileTabVehicles getVehiclesContent() {
    return new ProfileTabVehicles(getVisibleElement(ProfileTab.VEHICLES.contentSelector).findElements(VEHICLES_LOCATOR));
  }

  public ProfileTabWithTable getAdminContent() {
    return new ProfileTabWithTable(getVisibleElement(ProfileTab.ADMIN.contentSelector));
  }
}
