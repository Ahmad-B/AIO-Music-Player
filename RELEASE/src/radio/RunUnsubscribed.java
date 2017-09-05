/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio;

/**
 *
 * @author Priyan
 */
public class RunUnsubscribed {

    public static void main(String[] args) {
        common.Customer.getCurrentCustomer().login(2, "user", false);
        fxTest.main(args);
    }
}
