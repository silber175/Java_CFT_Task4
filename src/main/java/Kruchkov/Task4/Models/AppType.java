package Kruchkov.Task4.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class AppType {

    private String name;

    public AppType()    {

    }

    public String getName() {
        return  this.name;
    }
   public AppType(String name)    {
       this.name = name;
   }
    public static AppType WEB = new AppType("web");
    public static AppType MOBILE = new AppType("mobile");


}
