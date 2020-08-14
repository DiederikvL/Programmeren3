// ======================\\
// Class Client.java     \\
// Diederik van Linden   \\
// TI1A                  \\
// 08/03/2019            \\
//=======================\\

public class Client {
   private String name;
   private String   pin;
   private int      balance;
   private int      amount;




   Client(String name, String pin, int balance){
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    /* getName geeft de name terug die bij het rekeningnummer hoort*/
    public String getName() {

         return name;
    }

    /*checkPin vergelijkt het ingevulde pin met de pin van de client.*/
    boolean checkPin(String Pin){
        return (this.pin.equals(Pin));

    }

    /* getBalance geeft balance terug wanneer goede pin in is gevoerd.*/

    public int getBalance(String Pin) {
        if(checkPin(Pin)){
            return balance;
        }
        else{
            return Integer.MIN_VALUE;
        }
    }

    /*Geld op rekening zetten.
     * verhoogd balance met het bedrag met deposit */

    public int deposit(int charged){
        balance += charged;
        return balance;
    }

    /*
    Geld opnemen.
    Kan alleen wanneer pincode goed is en
    wanneer er voldoende geld op de rekening staat.
    */

    public boolean withdraw(int amount, String pin){
        if(checkPin(pin) && balance >= amount){
            balance -= amount;
            return true;
        }
        else{
            return false;
        }
    }
}
