package com.bank.pro;

import com.bank.exception.InvalidLoginCredentials;
import com.bank.main.Customer;
import com.bank.model.DBConfig;
import java.sql.SQLException;

public class A {

    DBConfig db;

    public A() {
        this.db = new DBConfig();
    }

    public boolean processLogin(String Username, String Password) throws InvalidLoginCredentials {
        if (Username.equals("admin")) {
            if (Password.equals("1234")) {
                return true;
            }
        }
        throw new InvalidLoginCredentials();
    }

    public void createAccount(Customer c) throws ClassNotFoundException, SQLException {
        //Access DBConfig and pass customer to createAccount()

        db.createAccount(c);
    }

    public int stat(int text) throws ClassNotFoundException, SQLException {
        return(db.selBal(text));
    }

}
