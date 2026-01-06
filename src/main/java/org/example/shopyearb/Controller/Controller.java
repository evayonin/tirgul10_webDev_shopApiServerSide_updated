package org.example.shopyearb.Controller;

import org.example.shopyearb.DataBase.DBManager;
import org.example.shopyearb.Entity.Category;
import org.example.shopyearb.Entity.Product;
import jakarta.annotation.PostConstruct;
import org.example.shopyearb.Entity.User;
import org.example.shopyearb.Response.BasicResponse;
import org.example.shopyearb.Utils.Construct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {



    @Autowired
    private DBManager dbManager;

    @GetMapping("/get-categories")
    public List<Category> getCategories(){
        return this.dbManager.getAllCategories();
    }

    @GetMapping("/get-products-by-category-id")
    public List<Product> getProductByCategory(int categoryId){
      return this.dbManager.getProductsByCategoryId(categoryId);
    }


























    @PostConstruct
    //הפונקציה תקרא אוטומטית שהתוכנית עולה בלי שנזמן אותה
    public void init(){

    }

    @RequestMapping("/say-hello")
    public String  sayHello(){
       return "Hello";
    }



    @PostMapping ("/register-user")
    public BasicResponse register(@RequestBody User user){
        boolean successes =  false;
        Integer errorCode = Construct.ERROR_REGISTER;
     if (user!=null){
         User dbUser = this.dbManager.getUserByUsername(user.getName());
         if (dbUser == null){
             this.dbManager.insertUser(user);
             successes = true;
             errorCode = null;
         }
     }
      return new BasicResponse(successes,errorCode);
    }




}
