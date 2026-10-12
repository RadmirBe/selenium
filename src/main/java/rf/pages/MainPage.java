package rf.pages;

import rf.components.Header;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
  public MainPage(final WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getPageUrl() {
    return "http://rf2.loc/";
  }

  public Header getHeader() {
    return new Header(driver);
  }
}
