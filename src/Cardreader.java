// ======================\\
// Class Cardreader.java \\
// Diederik van Linden   \\
// TI1A                  \\
// 08/03/2019            \\
//=======================\\


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cardreader extends HardwareElement implements InputDevice  {


    Cardreader(String name){
        super(name);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
    } // Super wordt aangevraagd en naam wordt meegegeven.

    @Override
    public String getInput(){
        System.out.println("To simulate inserting card, enter card number");
        try{
            BufferedReader cardReader1 = new BufferedReader(new InputStreamReader(System.in));
            String s = cardReader1.readLine();

            System.out.println(s);
            return s;
        }
        catch(Exception e){
            return null;
        }
    }
}
