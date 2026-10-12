package rf.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rf.components.profile.ProfileTab;
import rf.components.profile.ProfileTabWithTable;
import rf.components.profile.ProfileTabs;
import rf.pages.ProfilePage;

import java.util.function.Function;

public class ProfileTest extends BaseAuthTest {
  @Test()
  public void TC_PROFILE_01() {
    final ProfilePage page = new ProfilePage(driver);
    page.goTo();
    Assertions.assertTrue(driver.getTitle() != null && driver.getTitle().startsWith("Miroslav Korda"));
    final String title = page.getTitle();
    Assertions.assertNotNull(title);
    Assertions.assertTrue(title.contains("Miroslav Korda"));
  }

  @Test()
  public void TC_PROFILE_02() {
    final ProfilePage page = new ProfilePage(driver);
    page.goTo();

    final ProfileTabs tabs = page.getProfileTabs();

    // Шаг №1
    Assertions.assertEquals(ProfileTab.values().length, tabs.getCount());
    Assertions.assertTrue(tabs.isActiveTab(ProfileTab.STATISTICS));
    testStatisticsTab(page, tabs);

    // Шаг №2
    testAchievementsTab(page, tabs);

    // Шаг №3
    testHousesTab(page, tabs);
    // Шаг №4
    testGarageTab(page, tabs);
    // Шаг №5
    testBusinessTab(page, tabs);
    // Шаг №6
    testVehiclesTab(page, tabs);
  }

  private void testStatisticsTab(final ProfilePage page, final ProfileTabs tabs) {
    final var content = testSelectTab(page, tabs, ProfileTab.STATISTICS, ProfilePage::getStatisticsContent, 14);
    Assertions.assertEquals("Статус", content.getRowTitle(0));
  }

  private void testAchievementsTab(final ProfilePage page, final ProfileTabs tabs) {
    tabs.select(ProfileTab.ACHIEVEMENTS);
    final var content = page.getAchievementsContent();

    Assertions.assertEquals("Всего:", content.getTitle());
    Assertions.assertEquals("31 из 98", content.getStats());
    Assertions.assertEquals(98, content.getTotalAchievements());
  }

  private void testHousesTab(final ProfilePage page, final ProfileTabs tabs) {
    final var content = testSelectTab(page, tabs, ProfileTab.HOUSES, ProfilePage::getHousesContent, 6);

    testContentRow(content, 0, "ID дома", "753");
  }

  private void testGarageTab(final ProfilePage page, final ProfileTabs tabs) {
    final var content = testSelectTab(page, tabs, ProfileTab.GARAGE, ProfilePage::getGarageContent, 8);

    testContentRow(content, 0, "ID гаража", "1");
  }

  private void testBusinessTab(final ProfilePage page, final ProfileTabs tabs) {
    final var content = testSelectTab(page, tabs, ProfileTab.BUSINESS, ProfilePage::getBusinessContent, 7);

    testContentRow(content, 0, "ID Бизнеса", "0");
  }

  private void testVehiclesTab(final ProfilePage page, final ProfileTabs tabs) {
    tabs.select(ProfileTab.VEHICLES);
    Assertions.assertTrue(tabs.isActiveTab(ProfileTab.VEHICLES));

    final var content = page.getVehiclesContent();
    Assertions.assertEquals(1, content.getTablesCount());

    final var table = content.getTable(0);
    Assertions.assertTrue(table.isImageDisplayed());
    Assertions.assertEquals(8, table.getRowsCount());

    testContentRow(table, 0, "Марка", "BMW X6M");
  }

  private <C extends ProfileTabWithTable> C testSelectTab(
      final ProfilePage page,
      final ProfileTabs tabs,
      final ProfileTab tab,
      final Function<ProfilePage, C> contentCallback,
      final int rowsCount
  ) {
    tabs.select(tab);
    Assertions.assertTrue(tabs.isActiveTab(tab));
    final C content = contentCallback.apply(page);
    if (content instanceof ProfileTabWithTable.WithImage contentWithImage) {
      Assertions.assertTrue(contentWithImage.isImageDisplayed());
    }
    Assertions.assertEquals(rowsCount, content.getRowsCount());
    return content;
  }

  private void testContentRow(final ProfileTabWithTable content, final int row, final String title, final String value) {
    Assertions.assertEquals(title, content.getRowTitle(row));
    Assertions.assertEquals(value, content.getRowValue(row));
  }
}
