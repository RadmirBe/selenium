package rf.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import rf.pages.MainPage;

public abstract class BaseAuthTest extends BaseTest {
  @BeforeEach
  private void authBefore() {
    startChromeDriver();
    login("Miroslav_Korda", "1234567");
  }

  protected void login(final String username, final String password) {
    final var page = new MainPage(driver);
    page.goTo();
    var header = page.getHeader();
    final var loginModal = header.openLoginModal();
    loginModal.loginAsValid(username, password);
    header = page.getHeader();
    Assertions.assertEquals(username.toUpperCase().replace("_", " "), header.getAuthTitle());
  }
}
