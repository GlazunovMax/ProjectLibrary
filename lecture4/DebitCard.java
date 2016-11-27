package by.homework.lecture4;


public class DebitCard extends Card{
    public DebitCard(String owner, int card) {
        super(owner, card);
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
