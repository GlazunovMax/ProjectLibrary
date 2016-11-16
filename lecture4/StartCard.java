package by.homework.lecture4;
/*1.
Определить класс Card имеющий атрибуты – владелец, баланс счета а также необходимые методы для проверки
баланса счета и изменения баланса (снятие/пополнение).

Определить два подкласса CreditCard и DebitCard которые реализуют соотв. поведение:
- дебитная карта не допускает снятие денег (уменьшение баланса) если счет не положительный
- кредитная карта допускает снятие со счета, даже если баланс не положительный. Т.о., у владельца
карты накапливается долг.

Реализовать класс Банкомат (ATM) который содержит методы для снятия денег с карты.

В методе main создать 2 карты – одна кредитная вторая дебитная, на разные имена.
Создать класс ATM и продемонстрировать способы использование карт и банкомата.
*/

public class StartCard {
    public static void main(String[] args) {
        DebitCard d = new DebitCard("Иванов Иван Иванович");

        d.getOwner();
        d.toReffilBalance(34);
        d.toWithdrawMoney();
        d.checkBalance();

        System.out.println();

        CreditCard c = new CreditCard("Петров Иван Николаевич");


        c.toReffilBalance(34);
        c.toWithdrawMoney();
        c.checkBalance();

        ATM atm = new ATM("Петров Смит Николаевич");
        atm.toWithdrawMoney();
        atm.checkBalance();






    }
}