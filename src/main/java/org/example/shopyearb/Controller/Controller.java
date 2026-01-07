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
             // אין יוזר כזה אז ייתן לו ליצור יוזר חדש עם השם משתמש הזה
             this.dbManager.insertUser(user);
             successes = true;
             errorCode = null;
         }
     }
      return new BasicResponse(successes,errorCode);
    }
    // לא צריך פה בדיקה מצד לקוח (isEmpty!) כי זה לא כמו ששי עשה בתנאים מקוננים בדיקה עבור כל פרמטר שם פרטי ואז שם משפחה לא נאל (בשביל ה error codes) כך ניתן לשים ככה פרמטר ריק עבור כל אחד בדפדפן וזה כן ייצור אובייקט אבל עם שדות נאל (ופה בודק אם בכלל היוזר נאל ז"א שבכלל לא קיים אובייקט).
    // אז גם אם מישהו ינסה להכניס ככה יוזר ריק מהדפדפן הוא לא יצליח להוסיף אותו בגלל שלא יעבור את התנאי הפנימי - כי היוזר לא הוכנס ל db, למרות שהיה עובר את התנאי החיצוני.



}
