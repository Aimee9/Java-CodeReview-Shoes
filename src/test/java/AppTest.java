import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.junit.Assert.*;
import spark.template.velocity.VelocityTemplateEngine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;


public class AppTest extends FluentTest {

  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
}

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Shoe Shopping!");
  }

  @Test
  public void addsStores(){
    goTo("http://localhost:4567/stores");
    fill("#newStore").with("Shoepie");
    submit(".btn btn-info");
    assertThat(pageSource()).contains("Shoepie");
  }
  @Test
  public void individualStoreLinksWork() {
    Store myStore = new Store("Shoepie");
    myStore.save();
    String storePath = String.format("http://localhost:4567/store/%d", myStore.getId());
    goTo(storePath);
    assertThat(pageSource()).contains("Shoepie");
    }

  @Test
  public void updateStoreNameWorks() {
    Store myStore = new Store("Shoepie");
    myStore.save();
    String storePath = String.format("http://localhost:4567/store/%d", myStore.getId());
    goTo(storePath);
    fill("#updateName").with("Monkey Feet");
    submit("btn btn-warning");
    assertThat(pageSource()).contains("Monkey Feet");
    }

    // @Test
    // public void deleteIndividualStore() {
    //   Store myStore = new Store("Shoepie");
    //   myStore.save();
    //   String storePath = String.format("http://localhost:4567/store/%d", myStore.getId());
    //   goTo(storePath);
    //   click("/delete/store/$store.getId()");
    //   goTo("http://localhost:4567/stores");
    //   assertThat(pageSource()).doesNotContain("Shoepie");
    //   }

    @Test
    public void addsBrands(){
      goTo("http://localhost:4567/brands");
      fill("#newBrand").with("Adidas");
      submit(".btn btn-info");
      assertThat(pageSource()).contains("Adidas");
    }
    @Test
    public void individualBrandLinksWork() {
      Brand myBrand = new Brand("Adidas");
      myBrand.save();
      String brandPath = String.format("http://localhost:4567/brand/%d", myBrand.getId());
      goTo(brandPath);
      assertThat(pageSource()).contains("Adidas");
      }
}
