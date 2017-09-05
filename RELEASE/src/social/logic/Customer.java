/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.logic;

import java.util.*;

/**
 *
 * @author home
 */
//Customer class who is isDiscoverable and isSubscribed

public class Customer {
    
     private String name;
     private int customerID;
   
    public Customer(String name, int id){
        this.name = name;
        customerID = id;
     }
    
     public String getName()
     {
         return name;
     }
     
     public int getId()
     {
         return customerID;
     }
    
    

    
}
