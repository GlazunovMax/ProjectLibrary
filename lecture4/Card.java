package by.homework.lecture4;

import java.util.Scanner;


public class Card {
    private String owner;
    private int balance;
    private int card;


    public Card(String owner, int card) {
        this.owner = owner;
        this.card = card;

    }

    int getBalance() {
        return balance;

    }

    public String getOwner() {
        return "Карта принадлежит " + owner;
    }

    public int getCard() {
        return card;
    }


   void checkBalance(){
        System.out.println("Текущий баланс счета " + balance);
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

