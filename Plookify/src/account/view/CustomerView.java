/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.view;

import account.accountManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Surya
 */
public class CustomerView {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> userName;
    @FXML
    private TableColumn<Customer, String> passWord;
    @FXML
    private TableColumn<Customer, String> realName;
    @FXML
    private TableColumn<Customer, String> address;
    @FXML
    private TableColumn<Customer, String> postCode;
    
    private accountManager accountManager;

    private static class Customer {

        public Customer() {
        }
    }
}
