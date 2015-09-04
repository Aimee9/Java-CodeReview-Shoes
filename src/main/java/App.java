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
     model.put("storeBrands", store.getBrands());
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
        HashMap<String, Object> model = new HashMap<String, Object>();
        int storeId = Integer.parseInt(request.params(":id"));
        Store store = Store.find(storeId);
        //String updateName = request.queryParams("updateName");
        store.update(request.queryParams("updateName"));
        response.redirect("/store/" + storeId);
        return null;
      });
  }
}
