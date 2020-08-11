
package com.bank.pro;

import com.bank.exception.InsufficentFundsException;
import com.bank.model.DBConfig;
import java.sql.SQLException;

public class B {
    DBConfig db=new DBConfig();
    public void depo(int accNum,int amount) throws ClassNotFoundException, SQLException {
        db.depo(accNum,amount);
    }

    public void withdraw(int accNum, int amount) throws ClassNotFoundException, SQLException, InsufficentFundsException {
        int balance=db.selBal(accNum);
        if(balance-amount<0){
            throw new InsufficentFundsException();
        }
        db.withdraw(accNum,amount);
    }
    
}
