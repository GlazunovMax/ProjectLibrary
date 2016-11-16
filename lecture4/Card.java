package by.homework.lecture4;

import java.util.Scanner;


public class Card {
    private String owner;
    private int balance;


    public Card(String owner) {
        this.owner = owner;
    }

    int getBalance() {
        return balance;

    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return "Карта принадлежит " + owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

   void checkBalance(){
        System.out.println("Текущий баланс счет " + balance);
    }

    void toReffilBalance(int sum){
        balance += sum;
        System.out.println("На счет поступили средства "+ sum);
    }

    void toWithdrawMoney(){
        System.out.println("Введите сумму для снятия ");
        Scanner sc = new Scanner(System.in);
        int sum = sc.nextInt();
        balance -= sum;
        System.out.println("Со счета были сняты средства " + sum);
    }
}

