package com.ksnote;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        boolean success = account.withdraw(10);
        System.out.println(success);
    }
}
