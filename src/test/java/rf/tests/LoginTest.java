package rf.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import rf.components.Header;
import rf.components.LoginModal;
import rf.components.Toast;
import rf.pages.MainPage;

import java.util.stream.Stream;

public class LoginTest extends BaseTest {
  private MainPage page;
  private Header header;
  private Toast toast;
  private LoginModal loginModal;

  @BeforeEach
  public void setup() {
    startChromeDriver();

    page = new MainPage(driver);
    page.goTo();
    header = page.getHeader();
    Assertions.assertEquals("АВТОРИЗАЦИЯ", header.getAuthTitle());
    loginModal = header.openLoginModal();
    Assertions.assertTrue(loginModal.isOpened());
    toast = new Toast(driver);
  }

  @Test
  public void TC_LOGIN_01() {
    loginModal.loginAsValid("Miroslav_Korda", "1234567");
    header = page.getHeader();
    Assertions.assertEquals("MIROSLAV KORDA", header.getAuthTitle());
    Assertions.assertTrue(loginModal.isClosed());
  }

  @ParameterizedTest(name = "{0} -> {2}")
  @MethodSource("invalidLoginData")
  public void TC_LOGIN_02_05(final String login, final String password, final String expectedResultText) {
    System.out.println(expectedResultText);
    loginModal.loginAsInvalid(login, password);
    Assertions.assertEquals(expectedResultText, toast.getText());
    header = page.getHeader();
    Assertions.assertEquals("АВТОРИЗАЦИЯ", header.getAuthTitle());
    Assertions.assertTrue(loginModal.isOpened());
  }

  private static Stream<Arguments> invalidLoginData() {
    return Stream.of(
        Arguments.of("Miroslav_Korda", "12345678", "Не правильный логин или пароль"),
        Arguments.of("Miroslav_Korda11", "1234567", "Игрок не найден!"),
        Arguments.of("", "1234567", "Поле name обязательно для заполнения."),
        Arguments.of("Miroslav_Korda", "", "Поле password обязательно для заполнения.")
    );
  }

  @Test
  public void TC_LOGIN_06() {
    loginModal.close();
    Assertions.assertTrue(loginModal.isClosed());
  }
}
