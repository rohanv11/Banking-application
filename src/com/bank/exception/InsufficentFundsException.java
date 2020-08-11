
package com.bank.exception;

public class InsufficentFundsException extends Exception{
    public String getMessage(){
        return "Amount entered is greater than balance";
    }
}
