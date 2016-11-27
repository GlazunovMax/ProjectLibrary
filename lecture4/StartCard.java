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

        ATM atm1 = new ATM();

        Card a = new Card("Прошкин В.А.", atm1.FORCARD);
        a.checkBalance();
        a.toReffilBalance(55);
        a.toWithdrawMoney();
        a.checkBalance();


        Card c = new CreditCard("Иванов Иван Иванович", atm1.FORDEBITCARD);
        c.toReffilBalance(34);
        c.toWithdrawMoney();
        c.checkBalance();


        Card d = new DebitCard("Петров Иван Николаевич", atm1.FORDEBITCARD);
        d.getOwner();
        d.toReffilBalance(34);
        d.toWithdrawMoney();
        d.checkBalance();










    }
}
