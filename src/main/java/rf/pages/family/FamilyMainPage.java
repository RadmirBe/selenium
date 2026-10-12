package rf.pages.family;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rf.components.family.FamilyInfo;
import rf.components.family.FamilyMembers;

public class FamilyMainPage extends FamilyPage {
  private static final By INFO_TITLE_LOCATOR = By.cssSelector(".gta-title:nth-of-type(1)");
  private static final By MEMBERS_TITLE_LOCATOR = By.cssSelector(".gta-title:nth-of-type(2)");

  public FamilyMainPage(final WebDriver driver) {
    super(driver, "/family");
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/family";
  }

  public String getInfoTitle() {
    return getVisibleElement(INFO_TITLE_LOCATOR).getText();
  }

  public String getMembersTitle() {
    return getVisibleElement(MEMBERS_TITLE_LOCATOR).getText();
  }

  public FamilyInfo getFamilyInfo() {
    return new FamilyInfo(driver);
  }

  public FamilyMembers getMembers() {
    return new FamilyMembers(driver);
  }
}
