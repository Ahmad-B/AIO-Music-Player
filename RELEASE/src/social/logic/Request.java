
package social.logic;
import common.Database;

public class Request {
    
    
    private String mess;
    private  int sentToID; 
    private  int sentFromID; 
    private String nameFrom;
    private String date;
   

    public Request(int fromID, int toID, String mes, String name,String date ) {
      
        mess = mes;
        sentToID = toID;
        sentFromID = fromID;
        nameFrom = name;    
        this.date = date;
    }
    // constructor for updateInbox in friendmanager class
    public Request(int fromID, String mes, String name,String date, int toID ) {
        mess = mes;
        sentFromID = fromID;
        nameFrom = name;    
        this.date = date;
        sentToID = toID;
    }

    
    public String getMessage()
    {
        return mess;
    }
    
    public int getsentToID()
    {
        return sentToID;
    }
    
    public int getsentFromID()
    {
        return sentFromID;
    }
    public String getName()
    {
        return nameFrom;
    }
    public String getDate()
    {
        return date;
    }
    
    
    
}
