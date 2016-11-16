package by.homework.lecture4;


public class DebitCard extends Card{
    public DebitCard(String owner) {
        super(owner);
    }
@Override
public void checkBalance(){

        if(getBalance()<0){
            System.out.println("На счету недостаточно средств, операция невозможна!!!");
        }else{
            super.checkBalance();
        }

    }
}
