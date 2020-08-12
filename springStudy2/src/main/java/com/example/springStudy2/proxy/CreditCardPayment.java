package com.example.springStudy2.proxy;

public class CreditCardPayment implements Payment{
    Payment payment;

    public CreditCardPayment(Payment payment){
        this. payment = payment;
    }

    @Override
    public void pay(int amount){
        //앞에서 하고싶은 코드
        payment.pay(amount);
        //뒤에서 하고싶은 코드
    }
}
