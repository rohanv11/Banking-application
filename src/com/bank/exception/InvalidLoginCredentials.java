package com.bank.exception;

public class InvalidLoginCredentials extends Exception {
    public String getmessage(){
        return "Invalid credentials";
    }
}
