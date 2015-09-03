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
}
