package by.homework.lecture4;


public class CreditCard extends Card {

    public CreditCard(String owner, int card) {
        super(owner, card);

    }
@Override
public void checkBalance() {

        if (getBalance() < 0) {
            System.out.println("Задолженность на счету составляет " + getBalance());
        } else {
            super.checkBalance();
        }
    }
}
