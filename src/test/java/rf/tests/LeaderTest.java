package rf.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rf.components.Alert;
import rf.pages.LeaderPage;

import java.util.List;

public class LeaderTest extends BaseAuthTest {
  private LeaderPage page;

  @BeforeEach
  public void setup() {
    page = new LeaderPage(driver);
    page.goTo();
    Assertions.assertTrue(driver.getTitle() != null && driver.getTitle().startsWith("Панель лидера организации"));
  }

  @Test
  public void TC_LEADER_01() {
    final String membersTitle = page.getMembersTitle();
    Assertions.assertTrue(membersTitle != null && membersTitle.matches("Состав \\(всего \\d+ человек\\)"));

    Assertions.assertTrue(page.isTableVisible());
    Assertions.assertEquals(List.of(
        "#",
        "Имя",
        "Уровень",
        "Ранк",
        "Статус",
        "Принят",
        "Действие"
    ), page.getTableHeaders());

    Assertions.assertTrue(page.isInputReasonVisible());
    Assertions.assertTrue(page.isSelectActionVisible());
    Assertions.assertTrue(page.isButtonExecuteVisible());
  }

  @Test
  public void TC_LEADER_02() {
    Assertions.assertEquals("Уволить", page.getSelectedOption());
    Assertions.assertTrue(page.isInputReasonVisible());

    page.selectAction("Повысить на 1 ранг");
    Assertions.assertFalse(page.isInputReasonVisible());

    page.selectAction("Понизить на 1 ранг");
    Assertions.assertFalse(page.isInputReasonVisible());

    page.selectAction("Уволить");
    Assertions.assertTrue(page.isInputReasonVisible());
  }

  @Test
  public void TC_LEADER_03() {
    final Alert alert = new Alert(driver);

    page.submitAction();
    acceptConfirm();
    Assertions.assertTrue(alert.getText().startsWith("Выберите хотя бы 1 человека!"));

    page.selectAction("Повысить на 1 ранг");
    page.submitAction();
    acceptConfirm();
    Assertions.assertTrue(alert.getText().startsWith("Выберите хотя бы 1 человека!"));

    page.selectAction("Понизить на 1 ранг");
    page.submitAction();
    acceptConfirm();
    Assertions.assertTrue(alert.getText().startsWith("Выберите хотя бы 1 человека!"));
  }

  @Test
  public void TC_LEADER_04() {
    final Alert alert = new Alert(driver);

    page.submitAction();
    acceptConfirm();
    Assertions.assertTrue(alert.getText().startsWith("Выберите хотя бы 1 человека!"));

    alert.close();
    Assertions.assertFalse(alert.isVisible());
  }

  @Test
  public void TC_LEADER_05() {
    final Alert alert = new Alert(driver);
    final String playerName = "test";

    final var oldPlayerRow = page.getPlayerRow(playerName);
    oldPlayerRow.check();
    Assertions.assertTrue(oldPlayerRow.isChecked());

    page.selectAction("Повысить на 1 ранг");
    page.submitAction();
    acceptConfirm();
    Assertions.assertTrue(alert.getText().startsWith("Вы успешно повысили ранг выбранным игрокам"));

    final var newPlayerRow = page.getPlayerRow(playerName);
    Assertions.assertTrue(newPlayerRow.position() < oldPlayerRow.position());
    Assertions.assertTrue(newPlayerRow.getRank().endsWith("(2)"));
  }

  @Test
  public void TC_LEADER_06() {
    final Alert alert = new Alert(driver);
    final String playerName = "test";

    final var oldPlayerRow = page.getPlayerRow(playerName);
    oldPlayerRow.check();
    Assertions.assertTrue(oldPlayerRow.isChecked());

    page.selectAction("Понизить на 1 ранг");
    page.submitAction();
    acceptConfirm();
    Assertions.assertTrue(alert.getText().startsWith("Вы успешно понизили ранг выбранным игрокам"));

    final var newPlayerRow = page.getPlayerRow(playerName);
    Assertions.assertTrue(newPlayerRow.position() > oldPlayerRow.position());
    Assertions.assertTrue(newPlayerRow.getRank().endsWith("(1)"));
  }

  private void acceptConfirm() {
    driver.switchTo().alert().accept();
  }
}
