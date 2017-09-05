package common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Priyan & Surya
 */
public class Customer {

    private static final Customer current = new Customer();
    public static boolean reverted;

    private int id;

    private String name;
    private String surname;

    private String username = null;
    private String password = null;

    private String address;
    private String postCode = null;
    private String number = null;
    private String email = null;

    private boolean isSubscribed = false;
    private int paymentID = -1;

    private boolean isDiscoverable = false;
    private Date lastReplaceDate = new Date(0);
    private Long date;
    private Long deadLine;

    private Customer() {
        address = null;
        id = -1;
        name = null;
        surname = null;
        isSubscribed = true;
    }

    public void login(int id, String name, boolean isSubscribed) {
        this.id = id;
        this.name = name;
        this.isSubscribed = isSubscribed;
    }

    public void login(int id) {
        Database db = Database.getInstance();
        try {
            Statement sm = db.getConnection().createStatement();

            ResultSet rs = sm.executeQuery("SELECT * FROM Customer WHERE CustomerID = '" + id + "'");
            this.id = id;
            name = rs.getString("name");
            surname = rs.getString("surname");
            username = rs.getString("username");
            password = rs.getString("password");
            address = rs.getString("addressLine1");
            postCode = rs.getString("postCode");
            number = rs.getString("number");
            email = rs.getString("email");
            isSubscribed = rs.getInt("isSubscribed")>0;
            isDiscoverable = rs.getInt("isDiscoverable") > 0;
            lastReplaceDate = new Date(rs.getLong("lastReplaceDate"));
            deadLine = rs.getLong("deadLine");
            if ((new Date().getTime() - deadLine) > 0) {
                reverted = isSubscribed;
                isSubscribed = false;
                PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Customer SET isSubscribed = 0 WHERE CustomerID = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } else {
                PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Customer SET isSubscribed = 1 WHERE CustomerID = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            date = rs.getLong("lastReplaceDate");

        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        id = -1;
        name = null;
        isSubscribed = false;
    }

    public static Customer getCurrentCustomer() {
        return current;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void toggleSubscribed() {
        isSubscribed = !isSubscribed;
    }

    public void nowSubscribed() {
        isSubscribed = true;
    }

    public void notSubscribed() {
        isSubscribed = false;
    }

    public boolean isLoggedIn() {
        return id == -1;
    }

    public Date getReplacedDate() {
        return lastReplaceDate;
    }

    public long getReplacedTime() {
        return date;
    }

    public void setReplacedDate() {
        lastReplaceDate = new Date();
        date = lastReplaceDate.getTime();
    }
}
