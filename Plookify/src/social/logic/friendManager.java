
package social.logic;
import common.Database;
import common.Customer;
import java.sql.*;
import java.time.LocalDate;

import java.util.*;
import javafx.collections.*;


public class friendManager {
     
     private  Customer user; // User from common package
     private  String accountName;
     private  int accountCustomerID;
     private  Database db;
     private ObservableList<social.logic.Customer> Names = FXCollections.observableArrayList();
     
     
     public friendManager(){
         
        db = Database.getInstance();
        user = Customer.getCurrentCustomer();
        accountName = user.getName();
        accountCustomerID = user.getId();
   
        updateFriend();
     }
     
     public ObservableList getNames()
     {
         updateFriend();
         return Names;
     }
          // update friend table
        private void updateFriend()
        {
           
            if(Names.size()>0)
                Names.removeAll(Names);

            String com = "select customer.name, customer.CustomerID  from customer , friend where  friend.customerIDfriend = customer.CustomerID and friend.hasAccepted =1 and friend.customerID ="+ accountCustomerID ;

            PreparedStatement st;
                try {
                    st =  db.getConnection().prepareStatement(com);
                     ResultSet rs = st.executeQuery();
                     while(rs.next()){
                         Names.add(new social.logic.Customer(rs.getString("name"), rs.getInt("customerID")));
                        
                     }  
                   
                } catch (Exception e) {
                   System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }

        }

     public int getAccountCustomerID()
     {
         return accountCustomerID;
     }
      public String getAccountCustomerName()
     {
         return accountName;
     }
      
       public String getStatus()
    {
        String com =  "Select isDiscoverable from customer where CustomerID = "  + accountCustomerID;
         int status=0;
        PreparedStatement st;
         try {
                st = db.getConnection().prepareStatement(com);
                 ResultSet rs = st.executeQuery();
                 while(rs.next()){
                      status = rs.getInt("isDiscoverable");
                 }
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            }
            if(status == 1)
             return "Public";
            else
             return "Private";
    }
       
     public void changeStatus(String status)
    {
        String com;
        if(status.equals("Private"))
            com = "update Customer set isDiscoverable = 0 where CustomerID = "  + accountCustomerID;
        else
            com = "update Customer set isDiscoverable = 1 where CustomerID = "  + accountCustomerID;
            
         PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                  st.executeUpdate(); 
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
           
            }
    }
     
        public void removeFriend(int id)
    {
     
        String com = "delete from Friend where CustomerID = "+accountCustomerID+" and CustomerIDFriend = " + id;
            
         PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                  st.executeUpdate(); 
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
           
            }
             removeFriendAuto(id);
    }
     private void removeFriendAuto(int id) {

        String com = "delete from Friend where CustomerID = " + id + " and CustomerIDFriend = " + accountCustomerID;

        PreparedStatement st;
        try {
            st = db.getConnection().prepareStatement(com);
            st.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

   
     public void addFriend(int CustomerID)
    {
        //Decision stored as -1
        int hasAccept = -1;
     
      String com = "insert into Friend (CustomerID,CustomerIDFriend,hasAccepted) VALUES ("+accountCustomerID +"," +CustomerID +","+hasAccept+")";
     
      PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                  st.executeUpdate(); 
                
            } catch (Exception e) {
                //System.out.println("rr");
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
           
            }
       
           
    }
     
       // search by given a name
    public ObservableList searchCustomer(String name)
    {
         ObservableList<social.logic.Customer> customerNames = FXCollections.observableArrayList();
          name = name.trim().toLowerCase();
         String com = " select C.name, C.customerid from Customer C "
                        + " where C.isSubscribed = 1 and C.isDiscoverable = 1 and C.customerid !="+ accountCustomerID +" and LOWER(C.name) LIKE '"+name+"%'";
        PreparedStatement st;

        
            try {
                st = db.getConnection().prepareStatement(com);
                 ResultSet rs = st.executeQuery();
                 while(rs.next()){
                     customerNames.add(new social.logic.Customer(rs.getString("name"), rs.getInt("customerID")));
                 }
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            }
            return customerNames;
    }
     
     // check if selected friend already in the friendtable
     public boolean checkFriend(int id)
     {
        String com = "select count(CustomerIDFriend)  from friend where customerIDfriend = "+ id +" and customerID = "+ accountCustomerID ;
        int count=0;
        PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                 ResultSet rs = st.executeQuery();
                 while(rs.next()){
                   count= rs.getInt("count(CustomerIDFriend)");
                     
                 }  
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            }
             return count==0;
     }
   
    public void friendRequest(int id,String mess)
    {
        mess = mess.replaceAll("'", "''");
        //current date
        LocalDate date = LocalDate.now();
        String stringDate = Date.format(date);
        
         String com  = "insert into Inbox values("+ accountCustomerID + "," + id + ", '"+ mess + "' , '"+ stringDate+ "')";
         PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                st.executeUpdate(); 
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
           
            }
        
    }
 
     //update messages from inbox table
    public ObservableList updateInbox()
    {
        ObservableList<Request> inboxNames = FXCollections.observableArrayList();
       
        String com  = " select c.customerid, I.Message, C.name, I.date"
                + "  from Inbox I "
                + "inner join Customer C  "
                + "on (c.customerid = I.SentfromID) where SentToID = "+ accountCustomerID ; 

        PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                 ResultSet rs = st.executeQuery();
                 while(rs.next()){
                     inboxNames.add(new Request(rs.getInt("customerid"), rs.getString("Message"), rs.getString("name"),rs.getString("date"),accountCustomerID));
                    
                 }
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            }
            return inboxNames; 
    }
    
   
    
 
        //To accept or decline friend request from inbox
      public void makeDecision(int decision,Request req)
    {   
        deleteMessage(req);
         String com;
        if(decision==1){
            addFriendAuto(req);
            com = "Update Friend set hasAccepted = "+ decision +" Where CustomerID = "+ req.getsentFromID()+ " and CustomerIDFriend = "+req.getsentToID();   
        } else{
           com = "delete from Friend where CustomerID = "+req.getsentFromID()+" and CustomerIDFriend = " + req.getsentToID();
        }
         PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                  st.executeUpdate(); 
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
           
            }
    }
       private void addFriendAuto( Request req){
      
        String com = "insert into Friend (CustomerID,CustomerIDFriend,hasAccepted) VALUES (" + req.getsentToID() + "," + req.getsentFromID() + ",1)";
       
        PreparedStatement st;
        try {
            st = db.getConnection().prepareStatement(com);
            st.executeUpdate();

        } catch (Exception e) {
          
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        
    }
      
      // Delete message in inbox table
      private void deleteMessage(Request req)
      {
          String com = "delete from Inbox where SentToID = "+req.getsentToID() +" and SentfromID = " + req.getsentFromID();
        
         PreparedStatement st;
            try {
                st = db.getConnection().prepareStatement(com);
                  st.executeUpdate(); 
                
            } catch (Exception e) {
               System.err.println(e.getClass().getName() + ": " + e.getMessage());
           
            }
          
      }
       // Search friend from friendlist
      public ObservableList<social.logic.Customer> searchFriend(String name) {
        
        ObservableList<social.logic.Customer> friend = FXCollections.observableArrayList();
        friend.removeAll(friend);
        
        if(!name.equals("")) {
            PreparedStatement st;
            try {
                // getting data
                String com = " select C.name, C.customerid from Customer C "
                        + "inner join friend f on (C.customerid = f.customeridfriend)"
                        + " where f.customerid = " + accountCustomerID + " and f.hasAccepted =1 and C.name LIKE '" + name + "%'";
               st = db.getConnection().prepareStatement(com);
               ResultSet rs = st.executeQuery();
                while(rs.next()) {
                    friend.add(new social.logic.Customer(rs.getString("name"),rs.getInt("customerid")));
                }
            } catch (SQLException ex) {
                System.out.println("SQL statement error");
                System.exit(0);
            }
        } 
        return friend;
           
    }    

    
}
