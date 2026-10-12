package rf.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rf.pages.family.FamilyEstatePage;
import rf.pages.family.FamilyMainPage;
import rf.pages.family.FamilyVehiclesPage;

import java.util.List;

public class FamilyTest extends BaseAuthTest {
  @Test
  public void TC_FAMILY_01() {
    final var page = new FamilyMainPage(driver);
    page.goTo();
    Assertions.assertTrue(driver.getTitle() != null && driver.getTitle().startsWith("Меню семьи"));
    final var nav = page.getNav();
    Assertions.assertTrue(nav.isActiveCurrentLink());
    Assertions.assertEquals("ИНФОРМАЦИЯ", nav.getCurrentLinkText());

    Assertions.assertEquals("информация о семье", page.getInfoTitle());
    final var info = page.getFamilyInfo();
    Assertions.assertEquals(6, info.getCountBlocks());

    Assertions.assertEquals("Gonzalez Family", info.getBlockValue("Название семьи"));
    Assertions.assertEquals("11/12/2022", info.getBlockValue("Дата основания"));
    Assertions.assertEquals("Kevin_Gonzalez", info.getBlockValue("Основатель семьи"));
    Assertions.assertEquals("123", info.getBlockValue("Репутация"));
    Assertions.assertEquals("8", info.getBlockValue("Место в рейтинге"));
    Assertions.assertEquals("$12.345", info.getBlockValue("Банк"));
    Assertions.assertEquals("99", info.getBlockValue("Участники"));
    Assertions.assertEquals("2", info.getBlockValue("В сети"));

    Assertions.assertEquals("члены семьи", page.getMembersTitle());
    final var members = page.getMembers();
    Assertions.assertEquals(2, members.getCount());
    Assertions.assertEquals(List.of(
        "Игрок",
        "Уровень",
        "Ранг",
        "Репутация",
        "Статус"
    ), members.getTableHeaders());
    Assertions.assertTrue(members.hasPlayer("Miroslav Korda"));
  }

  @Test
  public void TC_FAMILY_02() {
    final var page = new FamilyEstatePage(driver);
    page.goTo();
    Assertions.assertTrue(driver.getTitle() != null && driver.getTitle().startsWith("Меню семьи"));
    final var nav = page.getNav();
    Assertions.assertTrue(nav.isActiveCurrentLink());
    Assertions.assertEquals("НЕДВИЖИМОСТЬ", nav.getCurrentLinkText());

    Assertions.assertTrue(page.getInfoTitle().startsWith("Недвижимость семьи"));

    Assertions.assertTrue(page.getTotalPriceTitle().contains("Общая стоимость:"));
    Assertions.assertTrue(page.getTotalPriceValue().startsWith("$"));

    Assertions.assertTrue(page.isTableVisible());
    Assertions.assertArrayEquals(new String[]{
        "Игрок",
        "Недвижимость",
        "Номер",
        "Стоимость",
        ""
    }, page.getTableHeaders());

    Assertions.assertEquals("Miroslav Korda", page.getTableFirstOwner());
  }

  @Test
  public void TC_FAMILY_03() {
    final var page = new FamilyVehiclesPage(driver);
    page.goTo();
    Assertions.assertTrue(driver.getTitle() != null && driver.getTitle().startsWith("Меню семьи"));
    final var nav = page.getNav();
    Assertions.assertTrue(nav.isActiveCurrentLink());
    Assertions.assertEquals("ТРАНСПОРТ", nav.getCurrentLinkText());

    Assertions.assertTrue(page.getInfoTitle().contains("транспорт семьи"));

    Assertions.assertTrue(page.getTotalPriceText().contains("Общая стоимость:"));
    Assertions.assertTrue(page.getTotalPriceText().contains("$"));

    Assertions.assertTrue(page.isTableVisible());
    Assertions.assertArrayEquals(new String[]{
        "Игрок",
        "Модель",
        "Номер",
        "Стоимость"
    }, page.getTableHeaders());

    Assertions.assertEquals("Miroslav Korda", page.getTableFirstOwner());
  }
}
