import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Brand {
  private int id;
  private String label;

  public Brand(String label) {
    this.label = label;

  }

  public int getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public static List<Brand> all() {
    String sql = "SELECT id, label FROM brands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Brand.class);
    }
  }


    @Override
    public boolean equals(Object otherBrand){
      if (!(otherBrand instanceof Brand)) {
        return false;
      } else {
        Brand newBrand = (Brand) otherBrand;
        return this.getLabel().equals(newBrand.getLabel());
      }
    }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands(label) VALUES (:label)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("label", this.label)
        .executeUpdate()
        .getKey();
    }
  }

  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM brands WHERE id = :id";
      Brand Brand = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
      return Brand;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM brands WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();

        String joinDeleteQuery = "DELETE FROM brands_stores WHERE brand_id = :brand_id";
         con.createQuery(joinDeleteQuery)
           .addParameter("brand_id", this.getId())
           .executeUpdate();
    }
  }

  public void addStore(Store store) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands_stores (brand_id, store_id) VALUES (:brand_id, :store_id)";
      con.createQuery(sql)
      .addParameter("brand_id", this.getId())
      .addParameter("store_id", store.getId())
      .executeUpdate();
    }
  }

 public List<Store> getStores() {
  try (Connection con = DB.sql2o.open()) {
    String sql = "SELECT stores.* FROM brands JOIN brands_stores ON (brands_stores.brand_id = brands.id) JOIN stores ON (brands_stores.store_id = stores.id) WHERE brand_id=:id";
    return con.createQuery(sql)
    .addParameter("id", id)
    .executeAndFetch(Store.class);
  }
 }

 public void removeStore(int store_id) {
  try (Connection con = DB.sql2o.open()) {
    String removeStoreQuery = "DELETE FROM brands_stores WHERE store_id = :store_id AND brand_id = :id";
    con.createQuery(removeStoreQuery)
      .addParameter("store_id", store_id)
      .addParameter("id", this.getId())
      .executeUpdate();
    }
   }
}
