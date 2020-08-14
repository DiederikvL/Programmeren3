// ==========================\\
// Class ReceiptPrinter.java \\
// Diederik van Linden       \\
// TI1A                      \\
// 08/03/2019                \\
//===========================\\


public class ReceiptPrinter extends HardwareElement implements OutputDevice{
    ReceiptPrinter(String name){

        super(name);
    }

    @Override                               // alles wat meegegeven is, wordt uitgeprint
    public void giveOutput(String name) {
        System.out.println(name);

    }
}
