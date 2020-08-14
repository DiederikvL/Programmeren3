// =====================\\
// Class Keypad.java    \\
// Diederik van Linden  \\
// TI1A                 \\
// 08/03/2019           \\
//======================\\

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Keypad extends HardwareElement implements InputDevice{

    private BufferedReader br;

    Keypad(String name){

        super(name);
        InputStreamReader isr = new InputStreamReader(System.in);
         br = new BufferedReader(isr);
    }



   /*de getInput zorgt ervoor dat er 1 teken per keer wordt ingelezen door het keypad.*/
    @Override
    public String getInput() {
        try {
            if (br.ready()) {
                Scanner s = new Scanner(System.in);
                String str = s.nextLine();
                return str;
            }else{
                return null;
            }
        }catch(IOException e){
            return null;
        }

    }
}
