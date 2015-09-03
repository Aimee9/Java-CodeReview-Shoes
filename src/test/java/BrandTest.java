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
}
