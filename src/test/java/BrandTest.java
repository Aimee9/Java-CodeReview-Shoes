import java.util.Arrays;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfLabelsAretheSame() {
    Brand firstBrand = new Brand("Nike");
    Brand secondBrand = new Brand("Nike");
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    assertTrue(Brand.all().get(0).equals(myBrand));
  }

  @Test
  public void find_findOneLabelInBrand() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    Brand savedBrand = Brand.find(myBrand.getId());
    assertTrue(myBrand.equals(savedBrand));
  }

  @Test
  public void delete_deleteLabelFromBrand() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    myBrand.delete();
    assertEquals(Brand.all().size(), 0);
  }

  @Test
 public void addStore_addsStoreToBrand() {
   Brand myBrand = new Brand("Mizuno");
   myBrand.save();
   Store myStore = new Store("Bundy Shoes");
   myStore.save();
   myBrand.addStore(myStore);
   Brand savedBrand = myStore.getBrands().get(0);
   assertTrue(myBrand.equals(savedBrand));
 }

 @Test
 public void getStores_returnAllStores_ArrayList() {
   Store myStoreOne = new Store("Bundy Shoes");
   myStoreOne.save();
   Store myStoreTwo = new Store("Big Foot");
   myStoreTwo.save();
   Brand myBrand = new Brand("Converse");
   myBrand.save();
   myBrand.addStore(myStoreOne);
   myBrand.addStore(myStoreTwo);
   List savedStores = myBrand.getStores();
   assertEquals(savedStores.size(), 2);
 }

 @Test
  public void removeStore_removesStoreFromBrand() {
    Store myStore = new Store("Bundy Shoes");
    myStore.save();
    Brand myBrand = new Brand("Converse");
    myBrand.save();
    myBrand.addStore(myStore);
    myBrand.removeStore(myStore.getId());
    assertFalse(myBrand.getStores().contains("Bundy Shoes"));
  }
}
