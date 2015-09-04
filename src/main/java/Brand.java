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
    String sql = "SELECT label FROM brands";
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
    }
  }

  public ArrayList<Store> getStores() {
   try (Connection con = DB.sql2o.open()) {
     String sql = "SELECT store_id FROM brands_stores WHERE brand_id = :brand_id";
     List<Integer> storeIds = con.createQuery(sql)
       .addParameter("brand_id", this.getId())
       .executeAndFetch(Integer.class);
     ArrayList<Store> stores = new ArrayList<Store>();
     for (Integer storeId : storeIds) {
       String storeQuery = "SELECT * FROM stores WHERE id = :storeId";
       Store store = con.createQuery(storeQuery)
         .addParameter("storeId", storeId)
         .executeAndFetchFirst(Store.class);
       stores.add(store);
     }
     return stores;
   }
 }
}
