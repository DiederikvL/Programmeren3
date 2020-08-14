// =====================\\
// Class Bank.java      \\
// Diederik van Linden  \\
// TI1A                 \\
// 08/03/2019           \\
//======================\\
import java.util.HashMap;
import java.util.Map;

public class Bank{

    Map<String,Client> accounts = new HashMap<String,Client>();

    Bank(){
        Client client1 = new Client("Jaapje", "1234", 100 );
        Client client2 = new Client("Berend", "1234", 500 );
        Client client3 = new Client("Bregje", "1234", 500 );

        accounts.put("1", client1);
        accounts.put("2", client2);
        accounts.put("3", client3);
    }

    public Client get(String rekeningnummer){

        return accounts.get(rekeningnummer);
    }
}