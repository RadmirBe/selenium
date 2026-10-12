package rf.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rf.BasePageObject;

import java.util.List;

public abstract class BaseMultiComponent extends BasePageObject {
  public BaseMultiComponent(final WebDriver driver) {
    super(driver);
  }

  public List<WebElement> root() {
    return getVisibleElements(getRootLocator());
  }

  public WebElement findElement(final int index) {
    return root().get(index);
  }

  protected abstract By getRootLocator();
}
