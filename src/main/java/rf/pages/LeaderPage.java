package rf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LeaderPage extends BasePage {
  private static final By MEMBERS_TITLE_LOCATOR = By.cssSelector(".leader-panel .row > .w-100 > h3");

  private static final By TABLE_LOCATOR = By.cssSelector(".leader-panel table.table");
  private static final By TABLE_HEADERS_LOCATOR = By.cssSelector(".leader-panel table.table thead th");

  private static final By TABLE_ROWS_LOCATOR = By.cssSelector(".leader-panel table.table tbody tr");

  private static final By INPUT_REASON_LOCATOR = By.cssSelector(".leader-panel table.table tfoot input");
  private static final By SELECT_ACTION_LOCATOR = By.cssSelector(".leader-panel table.table tfoot select");
  private static final By BUTTON_EXECUTE_LOCATOR = By.cssSelector(".leader-panel table.table tfoot button");

  public LeaderPage(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/s1/leader";
  }

  public String getMembersTitle() {
    return getVisibleElement(MEMBERS_TITLE_LOCATOR).getText();
  }

  public boolean isTableVisible() {
    return getVisibleElement(TABLE_LOCATOR).isDisplayed();
  }

  public List<String> getTableHeaders() {
    return getVisibleElement(TABLE_LOCATOR).findElements(TABLE_HEADERS_LOCATOR).stream()
        .map(WebElement::getText)
        .toList();
  }

  public boolean isInputReasonVisible() {
    return driver.findElement(INPUT_REASON_LOCATOR).isDisplayed();
  }

  public boolean isSelectActionVisible() {
    return driver.findElement(SELECT_ACTION_LOCATOR).isDisplayed();
  }

  public boolean isButtonExecuteVisible() {
    return driver.findElement(BUTTON_EXECUTE_LOCATOR).isDisplayed();
  }

  public String getSelectedOption() {
    return getActionSelect().getFirstSelectedOption().getText();
  }

  public void selectAction(final String optionText) {
    getActionSelect().selectByVisibleText(optionText);
  }

  private Select getActionSelect() {
    return new Select(getVisibleElement(SELECT_ACTION_LOCATOR));
  }

  public void submitAction() {
    driver.findElement(BUTTON_EXECUTE_LOCATOR).click();
  }

  public PlayerRow getPlayerRow(final String playerName) {
    final var rows = getVisibleElements(TABLE_ROWS_LOCATOR);

    for (int i = 0, maxI = rows.size(); i < maxI; i++) {
      final var row = rows.get(i);
      final var name = row.findElement(PlayerRow.NAME_LOCATOR).getText().trim();
      if (name.equals(playerName)) {
        return new PlayerRow(i, row);
      }
    }

    throw new NoSuchElementException("Player not found: " + playerName);
  }

  public record PlayerRow(int position, WebElement row) {
    private static final By NAME_LOCATOR = By.cssSelector("td:nth-child(2)");
    private static final By RANK_LOCATOR = By.cssSelector("td:nth-child(4)");
    private static final By CHECKBOX_LOCATOR = By.cssSelector("input[type='checkbox']");

    public String getRank() {
      return row.findElement(RANK_LOCATOR).getText().trim();
    }

    public void check() {
      row.findElement(CHECKBOX_LOCATOR).click();
    }

    public boolean isChecked() {
      return row.findElement(CHECKBOX_LOCATOR).isSelected();
    }
  }
}
