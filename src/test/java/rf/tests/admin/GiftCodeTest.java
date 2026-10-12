package rf.tests.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rf.components.Toast;
import rf.components.admin.AdminToast;
import rf.pages.ShopPage;
import rf.pages.admin.AdminGiftCodePage;
import rf.tests.BaseAuthTest;

public class GiftCodeTest extends BaseAuthTest {
  @Test
  public void TC_GIFT_CODE_01() {
    final var page = new AdminGiftCodePage(driver);
    page.goTo();
    Assertions.assertTrue(page.isTableVisible());
    Assertions.assertTrue(page.isButtonCreateVisible());
  }

  @Test
  public void TC_GIFT_CODE_02() {
    final var page = new AdminGiftCodePage(driver);
    page.goTo();

    final var toast = new AdminToast(driver);

    final var modal = page.openCreationModal();
    Assertions.assertTrue(modal.isVisible());

    modal.submit();
    final var code = page.parseGeneratedCodeFromAlert(toast.getText());
    Assertions.assertNotNull(code);
    Assertions.assertTrue(page.hasCodeInTable(code));

    final var shopPage = new ShopPage(driver);
    shopPage.goTo();

    final int oldBalance = shopPage.getBalance();

    final var activationModal = shopPage.openActivateCodeModal();
    Assertions.assertTrue(activationModal.isVisible());
    activationModal.fillInput(code);
    activationModal.submit();

    final var shopToast = new Toast(driver);
    Assertions.assertTrue(activationModal.isSuccessResult(shopToast.getText()));
    Assertions.assertTrue(activationModal.isVisible());
    Assertions.assertEquals(code, activationModal.getInputValue());

    activationModal.submit();
    shopToast.waitForRefresh();
    Assertions.assertEquals("Некорректный подарочный код", shopToast.getText());

    shopPage.refresh();
    Assertions.assertTrue(shopPage.getBalance() > oldBalance);
  }

  @Test
  public void TC_GIFT_CODE_03() {
    final var shopPage = new ShopPage(driver);
    shopPage.goTo();

    final int oldBalance = shopPage.getBalance();

    final var code = "---";
    final var shopToast = new Toast(driver);
    final var activationModal = shopPage.openActivateCodeModal();
    Assertions.assertTrue(activationModal.isVisible());
    activationModal.fillInput(code);
    activationModal.submit();
    Assertions.assertEquals("Некорректный подарочный код", shopToast.getText());

    Assertions.assertTrue(activationModal.isVisible());
    Assertions.assertEquals(code, activationModal.getInputValue());

    shopPage.refresh();
    Assertions.assertEquals(oldBalance, shopPage.getBalance());
  }
}
