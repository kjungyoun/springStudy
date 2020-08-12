package com.example.springStudy2.proxy;

public class CashPayment implements Payment{
    @Override
    public void pay(int amount){
        System.out.println("현금결제: " + amount);
    }
}
