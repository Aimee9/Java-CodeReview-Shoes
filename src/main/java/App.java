import java.util.List;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  get("/stores", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Store> stores = Store.all();
    model.put("stores", stores);
    model.put("template", "templates/stores.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  post("/stores", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String newStore = request.queryParams("newStore");
    Store store = new Store(newStore);
    store.save();
    response.redirect("/stores");
    return null;
  });

  get("/store/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Integer storeId = Integer.parseInt(request.params(":id"));
     Store store = Store.find(storeId);
     model.put("store", store);
     model.put("brands", Brand.all());
     //model.put("storesBrands", store.getBrands());
     model.put("template", "templates/store.vtl");
     return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  get("/delete/store/:id", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       int storeId = Integer.parseInt(request.params(":id"));
       Store deleteStore = Store.find(storeId);
       deleteStore.delete();
       response.redirect("/stores");
       return null;
     });

   post("/update/store/:id", (request, response) -> {
        int storeId = Integer.parseInt(request.params(":id"));
        Store store = Store.find(storeId);
        //String updateName = request.queryParams("updateName");
        store.update(request.queryParams("updateName"));
        response.redirect("/store/" + storeId);
        return null;
      });

   post("/addbrand/store/:id", (request, response) -> {
        //HashMap<String, Object> model = new HashMap<String, Object>();
        int brandId = Integer.parseInt(request.queryParams("brand"));
        Brand addedBrand = Brand.find(brandId);
        Integer storeId = Integer.parseInt(request.params(":id"));
        Store store = Store.find(storeId);
        store.addBrand(addedBrand);
        response.redirect("/store/" + storeId);
        return null;
      });

    get("/removebrand/brand/:brandid/store/:id", (request, response) -> {
        //HashMap<String, Object> model = new HashMap<String, Object>();
        Integer brandId = Integer.parseInt(request.params(":brandid"));
        Integer storeId = Integer.parseInt(request.params(":id"));
        Store store = Store.find(storeId);
        store.removeBrand(brandId);
        response.redirect("/store/" + storeId);
        return null;
      });

    get("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Brand> brands = Brand.all();
      model.put("brands", brands);
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newBrand = request.queryParams("newBrand");
      Brand brand = new Brand(newBrand);
      brand.save();
      response.redirect("/brands");
      return null;
    });

    get("/brand/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
       Integer brandId = Integer.parseInt(request.params(":id"));
       Brand brand = Brand.find(brandId);
       model.put("brand", brand);
       model.put("stores", Store.all());
       //model.put("brandStores", brand.getStores());
       model.put("template", "templates/brand.vtl");
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brand/:id/delete", (request, response) -> {
         HashMap<String, Object> model = new HashMap<String, Object>();
         int brandId = Integer.parseInt(request.params(":id"));
         Brand deleteBrand = Brand.find(brandId);
         deleteBrand.delete();
         response.redirect("/brands");
         return null;
       });

    post("/brand/:id/update", (request, response) -> {
      int storeId = Integer.parseInt(request.queryParams("store"));
      Store addStore = Store.find(storeId);
      Integer brandId = Integer.parseInt(request.params(":id"));
      Brand brand = Brand.find(brandId);
      brand.addStore(addStore);
      response.redirect("/brand/" + brandId);
      return null;
    });
  }
}
