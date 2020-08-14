// =====================\\
// Class ATM.java       \\
// Diederik van Linden  \\
// TI1A                 \\
// 08/03/2019           \\
//======================\\

/*In deze class wordt de naam van de bank opgeslagen */

import java.awt.*;
import java.util.ArrayList;
import java.lang.InterruptedException;


public class ATM{

    private Bank bank;
    private ATMScreen as;
    private Point pos;
    private DisplayText display1;
    private DisplayText display2;
    private DisplayText InputX;


    private boolean login = false;
    private String ShowX = "";
    public int balance;
    private boolean gekozen = false;
    public String amount;
    public String input;

    private ReceiptPrinter receiptPrinter;


    private ScreenButton knop1;
    private ScreenButton knop2;
    private ScreenButton knop3;
    private ScreenButton knop4;
    private ScreenButton knop5;
    private ScreenButton knop6;
    private ScreenButton knop7;
    private ScreenButton knop8;
    private ScreenButton knop9;
    private ScreenButton knop0;


    public ATM(Bank bank){


        this.bank = bank;       // Slaat bank op.

        as = new ATMScreen();
        Frame f = new Frame("My ATM");
        f.setBounds(200, 200, 400, 300);
        f.setBackground(Color.BLUE);
        f.addWindowListener(new MyWindowAdapter(f));
        f.add(as);
        f.setVisible(true);

        receiptPrinter = new ReceiptPrinter("receiptprinter");



        while(true) {           // na elke transactie wordt deze weer opnieuw aangeroepen zodat er transacties achter elkaar kunnen gedaan worden
            doTransaction();
        }

    }

    public void doTransaction(){

        String input;
        String pin = "";        // pin leegmaken
        String consoleInput;
        String amount = "";     // amount leegmaken
        int bedragint = 0;      // bedrag op 0 zetten
        login = false;
        gekozen = false;




        pos = new Point(10, 30);
        as.clear();
        display1 = new DisplayText("text", pos);
        pos.y += 50;
        display2 = new DisplayText("text2", pos);
        pos.x += 140;
        InputX = new DisplayText("X", pos);
        pos.y -= 50;
        as.add(InputX);
        as.add(display1);
        display1.giveOutput("Insert your card");
        Cardreader cardreader1 =  new Cardreader("cardreader1");
        Keypad keypad1 = new Keypad("keypad1");


        Bank bank1 = new Bank();

        Client user = bank1.get(cardreader1.getInput());


        if(user == null){                                   // wanneer rekeningnummer niet bekend is zal het programma opnieuw opstarten
            System.out.println("Client not found");
            display1.giveOutput("Client not found");

            try {
                Thread.sleep(2000);  // wacht 2 secondes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        else{
            System.out.println(user.getName());             // als user bekend is zal er om een bijbehorende pin gevraagd worden
            System.out.println("Enter pin");
            display1.giveOutput("Enter pin.");

            // alle knoppen voor de pin worden aangemaakt
            pos.y += 100;
            knop1 = new ScreenButton("1", pos);

            pos.x += 30;
            knop2 = new ScreenButton("2", pos);

            pos.x += 30;
            knop3 = new ScreenButton("3", pos);

            pos.x = 150;
            pos.y += 30;
            knop4 = new ScreenButton("4", pos);

            pos.x += 30;
            knop5 = new ScreenButton("5", pos);

            pos.x += 30;
            knop6 = new ScreenButton("6", pos);

            pos.x = 150;
            pos.y += 30;
            knop7 = new ScreenButton("7", pos);

            pos.x += 30;
            knop8 = new ScreenButton("8", pos);

            pos.x += 30;
            knop9 = new ScreenButton("9", pos);

            pos.y += 30;
            pos.x = 180;
            knop0 = new ScreenButton("0", pos);

            // knoppen worden toegevoegd aan het scherm
            as.add(knop1);
            as.add(knop2);
            as.add(knop3);
            as.add(knop4);
            as.add(knop5);
            as.add(knop6);
            as.add(knop7);
            as.add(knop8);
            as.add(knop9);
            as.add(knop0);

            // Knoppen worden toegevoegd aan een Arraylist om de knoppen te kunnen checken
            ArrayList<ScreenButton> knoplijst = new ArrayList<ScreenButton>();
            knoplijst.add(knop1);
            knoplijst.add(knop2);
            knoplijst.add(knop3);
            knoplijst.add(knop4);
            knoplijst.add(knop5);
            knoplijst.add(knop6);
            knoplijst.add(knop7);
            knoplijst.add(knop8);
            knoplijst.add(knop9);
            knoplijst.add(knop0);


            // pin wordt door keypad en knoppen ingelezen.
            while(!login){
                for (ScreenButton button :knoplijst){       // Input wordt via knoppen ingelezen
                    input = button.getInput();
                    consoleInput = keypad1.getInput();
                    InputX.giveOutput(ShowX);
                    if(input != null) {
                        System.out.println(input);
                        pin += input;
                        ShowX += "X";

                    }
                    else if(consoleInput != null){          // Input via console wordt ingelezen
                        System.out.println(consoleInput);
                        pin += consoleInput;
                        ShowX += "X";

                    }

                    /* Als er 4 cijfers zijn ingevoerd zal deze vergeleken worden met de pin van de gebruiker
                        Als deze gelijk zijn zal er ingelogd worden.
                    */


                    if(pin.length() == 4){

                        if(user.checkPin(pin)){
                            login = true;

                            ShowX = "";
                            System.out.println("Pin correct");

                        }else{
                            pin = "";
                            ShowX += "X";
                            display1.giveOutput("Wrong password");
                            System.out.println("Wrong password");
                            ShowX = "";


                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            return;

                        }
                        as.clear();

                    }

                }
            }

            /*Er wordt een bedrag gevraagd wat je wilt pinnen.*/
            if(login){
                display1.giveOutput("Choose amount");
                as.add(display1);
                pos.y = 70;
                pos.x = 10;
                ScreenButton twintig = new ScreenButton("20", pos);
                pos.y += 30;

                ScreenButton vijftig = new ScreenButton("50", pos);
                pos.y += 30;

                ScreenButton honderd = new ScreenButton("100", pos);
                pos.y += 30;

                ScreenButton tweehonderd = new ScreenButton("200", pos);

                // knoppen worden toegevoegd aan het scherm
                as.add(twintig);
                as.add(vijftig);
                as.add(honderd);
                as.add(tweehonderd);

                // Knoppen worden toegevoegd aan Arraylist om te checken welke er ingedrukt wordt.
                knoplijst.add(twintig);
                knoplijst.add(vijftig);
                knoplijst.add(honderd);
                knoplijst.add(tweehonderd);

                // De input is het bedrag wat gepint wordt
                while(!gekozen) {
                    for (ScreenButton button : knoplijst) {
                        input = button.getInput();

                        if (input != null) {
                            amount = input;
                            gekozen = true;
                        }


                    }
                }

                gekozen = false;

                System.out.println("Choosen amount: " + amount);  //Bedrag printen op terminal

                bedragint = Integer.parseInt(amount);  //Bedrag omzetten naar integer

                as.clear();

                // Als het gewilde bedrag groter is dan de balance kan de gebruiker niet pinnen
                if(bedragint > user.getBalance(pin)){
                    display1.giveOutput("Not enough credit.");
                    as.add(display1);
                    System.out.println(user.getBalance(pin));

                    try {
                        Thread.sleep(1000);    //wacht 1 seconde
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    as.clear();

                }

                /* Er is voldoende balance en er zal dus gepint worden.
                * Er wordt dan gevraagd of de gebruiker een pinbon wilt hebben*/
                else{
                    user.withdraw(bedragint, pin);
                    display1.giveOutput("Do you want a receipt?");
                    as.add(display1);
                    pos.y = 100;
                    pos.x = 30;
                    ScreenButton JaKnop = new ScreenButton("YES", pos);
                    pos.x += 60;
                    ScreenButton NeeKnop = new ScreenButton(" NO", pos);
                    as.add(JaKnop);
                    as.add(NeeKnop);
                    knoplijst.add(JaKnop);
                    knoplijst.add(NeeKnop);

                    while(!gekozen){
                        for (ScreenButton button : knoplijst) {  //loopen langs alle knoppen om te checken of er een wordt ingedrukt

                           input = button.getInput();

                           // wanneer YES wordt aangeklikt zal er een bon worden geprint
                           if(input != null && input.equals("YES")) {


                                  receiptPrinter.giveOutput("=========Receipt=========");
                                  receiptPrinter.giveOutput("Client:" + user.getName());
                                  receiptPrinter.giveOutput("Withdrawn:" + bedragint);
                                  receiptPrinter.giveOutput("Balance:" + user.getBalance(pin));
                                  receiptPrinter.giveOutput("=========================");

                                    gekozen = true;
                            }

                           // wanneer NO wordt aangeklikt zal er geen bon worden geprint
                           else if(input != null && input.equals(" NO")){
                                System.out.println("No receipt");
                                gekozen = true;
                            }

                        }
                    }

                    as.clear();

                    display1.giveOutput("Now dispensing €" + bedragint);
                    as.add(display1);

                    try {                           // wacht 3 seconden
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    as.clear();

                    display1.giveOutput("Please take your");
                    display2.giveOutput("card and cash");

                    as.add(display1);
                    as.add(display2);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    as.clear();

                }



        }
    }



    }


}