package rf.components.profile;

import org.openqa.selenium.By;

public enum ProfileTab {
  STATISTICS("Статистика", By.id("tab_stats")),
  ACHIEVEMENTS("Достижения", By.id("tab_achievements")),
  HOUSES("Жилье", By.id("tab_house")),
  GARAGE("Гараж", By.id("tab_garage")),
  BUSINESS("Бизнес", By.id("tab_business")),
  VEHICLES("Транспорт", By.id("tab_cars")),
  ADMIN("Админ", By.id("tab_admin"));

  public final String title;

  public final By contentSelector;

  ProfileTab(final String title, final By contentSelector) {
    this.title = title;
    this.contentSelector = contentSelector;
  }
}
