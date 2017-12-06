
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmeter;
import java.util.*;
import java.io.*;
/**
 *
 * @author Naveen
 */
public class MainMeter {

static IndividualMeters allMeters[] = new IndividualMeters[10];
static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
static List<String> logs = new ArrayList<String>();

static int installed_meters=0;

public static void installMeter() throws IOException
{
        int money=0;
        System.out.println("Enter Type of Meter : Enter (0) for PostPaid and (1) for PrePaid ");
        int type = Integer.parseInt(in.readLine());
        if (type == 1)
        {
                System.out.println("Enter the amount for topup");
                money = Integer.parseInt(in.readLine());
        }
        // Creational Design Patterns : factory pattern
        // basically this deals with the creation of objects , let subclasses decide which class to instantiate
        allMeters[installed_meters]=new IndividualMeters(type,money);
        installed_meters++;
        System.out.println(" New Meter has been installed");
}

public static void selectMeter(int meter) throws IOException
{
        IndividualMeters M = allMeters[meter];
        System.out.println("1: Check Dues");
        System.out.println("2: Get Balance");
        System.out.println("3: Turn this Meter Off");
        System.out.println("4: Turn this Meter On");
        System.out.println("5: Pay Dues");
        System.out.println("6: Reset Meter");
        System.out.println("7: Change Type ");
        System.out.println("8: TopUp Balance ");

        int ch = Integer.parseInt(in.readLine());

        if (ch==1)
        {
                double bill = M.getBill();
                System.out.println("The Bill is "+ bill);

        }
        else if (ch==2)
        {
                double balance = M.getBalance();
                if (balance<0)
                        System.out.println("The Balance is "+balance+ " : Excess units  "+Math.abs(balance));
                else
                        System.out.println("The Balance is "+balance);

        }
        else if (ch==3)
        {
                M.turnOff();
                System.out.println("the Meter has been turned off");
                logs.add("Meter "+meter+ " has been turned off");

        }
        else if (ch==4)
        {
                M.turnOn();
                System.out.println("this Meter has been turned on");
                logs.add("Meter "+meter+ " has been turned on");

        }
        else if (ch==5)
        {
                double bill = M.getBill();
                M.resetMeter();
                M.monthEnd();
                System.out.println("the bill of "+bill+" sens has been cleared");
                logs.add("The bill of "+bill+" has been cleared for meter "+meter);
        }
        else if (ch==6)
        {
                M.resetMeter();
                System.out.println("This Meter has been reset");
                logs.add("Meter Reset successful for meter "+meter);

        }
        else if (ch==7)
        {
                System.out.println("Enter the type to change to (0) PostPaid (1) PrePaid");
                int type = Integer.parseInt(in.readLine());
                M.changeType(type);
                if (type==0)
                {
                        System.out.println("The type has been changed to PostPaid");
                        logs.add("Type changed to PostPaid for meter "+meter);
                }
                else
                {
                        System.out.println("The type has been changed to PrePaid");
                        logs.add("Type changed to PrePaid for meter "+meter);
                }

        }
        else if (ch==8)
        {      
                System.out.println("Enter the amount for topup");
                double money = Double.parseDouble(in.readLine());
                M.addMoney(money);
                System.out.println("Top up successful");
                logs.add("Top up successful for meter "+meter);
        }

}


public static void main(String args[]) throws IOException
{
    String Username;
    String Password;

    Password = "admin";
    Username = "admin";

    Scanner input1 = new Scanner(System.in);
    System.out.println("Enter Username: ");
    String username = input1.next();

    Scanner input2 = new Scanner(System.in);
    System.out.println("Enter Password: ");
    String password = input2.next();

    if (username.equals(Username) && password.equals(Password)) {

        System.out.println("Access Granted! Welcome to the Electricity Meter System!");
        System.out.println("########################################################");

    
        
       while(true)
        {
                System.out.println("Hello User! Please Enter your Choice");
                System.out.println("####################################");
                System.out.println("1: Install a new Meter");
                System.out.println("2: Select a Meter ");
                System.out.println("3: Show Logs");
                System.out.println("4: Exit");
                int ch = Integer.parseInt(in.readLine());

                if (ch==1)
                {
                        if (installed_meters<10)
                        {
                                installMeter();
                        }
                        else
                        {
                                System.out.println("You have reached maximum no of installed Meters");
                        }
                }
                else if (ch==2)
                {
                        System.out.println("Enter the meter to select");
                        if (installed_meters==0)
                        {
                                System.out.println("There are no installed meters , Please install one first");
                        }
                        else
                        {
                                System.out.println("Enter a number between 0 and "+ (installed_meters-1));
                                int sel =Integer.parseInt(in.readLine());
                                if (sel<installed_meters && sel>=0)
                                        selectMeter(sel);
                                else
                                        System.out.println("This Meter does not exist");
                        }
                }
                else if (ch==3)
                {
                        if (logs.size()==0)
                        {
                                System.out.println("There are no logs");
                        }
                        else
                        {
                                int i;
                                System.out.println();
                                System.out.println("*** Showing Logs ***");
                                for(i=0; i<logs.size(); i++)
                                {
                                        System.out.println(logs.get(i));
                                }

                        }
                }
                else if (ch==4)
                {
                        System.out.println("Exiting ...");
                        System.exit(0);
                }
        }
    }
    

 else if (username.equals(Username)) {
        System.out.println("Invalid Password!");
    } else if (password.equals(Password)) {
        System.out.println("Invalid Username!");
    } else {
        System.out.println("Invalid Username & Password!");
    }

}

}
    
