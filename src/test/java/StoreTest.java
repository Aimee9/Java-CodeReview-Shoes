import java.util.Arrays;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Store firstStore = new Store("DSW");
    Store secondStore = new Store("DSW");
    assertTrue(firstStore.equals(secondStore));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Store myStore = new Store("DSW");
    myStore.save();
    assertTrue(Store.all().get(0).equals(myStore));
  }

  @Test
  public void find_findStoreInDatabase_true() {
    Store myStore = new Store("DSW");
    myStore.save();
    Store savedStore = Store.find(myStore.getId());
    assertTrue(myStore.equals(savedStore));
  }

  @Test
  public void update_updateNameOfStore() {
    Store myStore = new Store("DSW");
    myStore.save();
    myStore.update("Bundy Shoes");
    assertEquals("Bundy Shoes", Store.all().get(0).getName());
  }

  @Test
  public void delete_deleteStoreFromDatabase() {
    Store myStore = new Store("DSW");
    myStore.save();
    myStore.delete();
    assertEquals(0, Store.all().size());
  }

@Test
 public void addBrand_addsBrandToStore() {
   Brand myBrand = new Brand("Mizuno");
   myBrand.save();
   Store myStore = new Store("Bundy Shoes");
   myStore.save();
   myStore.addBrand(myBrand);
   Brand savedBrand = myStore.getBrands().get(0);
   assertTrue(myBrand.equals(savedBrand));
 }

 @Test
 public void getBrands_returnAllBrands_ArrayList() {
   Brand myBrandOne = new Brand("Mizuno");
   myBrandOne.save();
   Brand myBrandTwo = new Brand("Adidas");
   myBrandTwo.save();
   Store myStore = new Store("Bundy Shoes");
   myStore.save();
   myStore.addBrand(myBrandOne);
   myStore.addBrand(myBrandTwo);
   List savedBrands = myStore.getBrands();
   assertEquals(savedBrands.size(), 2);
 }
}
