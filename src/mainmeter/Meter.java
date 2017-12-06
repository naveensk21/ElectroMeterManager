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
public abstract class Meter implements OnOffMeter  {
    int status;

// Behavioral Design Patterns : template design patter , basically here we define the template for
// implementation by the subclass
abstract void resetMeter();
abstract void monthEnd();
abstract void addMoney(double money);
abstract double getBalance();
public void turnOff()
{
        this.status=0;
}

public void turnOn()
{
        this.status=1;
}
abstract void useElectricity();
abstract double getElec();
abstract double getBill();
abstract void changeType(int type);


}

//Strutural pattern : composite behaviour , the meter abstract class implemets the interface as well as defines additional templates
class IndividualMeters extends Meter
{
int type;
double balance;
double rate;
int status;
double elec;

IndividualMeters(int type,int pay)
{
        this.type=type; // 1 is for prepaid, 0 is for postpaid
        this.balance=200.00 + (pay*1.0)/50.0;
        this.rate=50.00;
        this.status=1;// 0 is off , 1 is on
        this.elec=0.0;
}
void resetMeter()
{
        this.balance=200.00;
        this.rate=50.00;
        this.status=1;// 0 is off , 1 is on
        this.elec=0.0;
}

void monthEnd()
{
        this.balance=this.balance+200;
}

void addMoney(double money)
{
        if (this.type==1)
        {
                this.balance=this.balance+(money*1.0)/50.0;
        }
        else
        {
                System.out.println("This meter is PostPaid, please change to Prepaid to add balance");
        }
}

double getBalance()
{
        this.useElectricity();
        return this.balance;
}



void useElectricity()
{
        int elec=0;
        Random rand = new Random();
        elec = rand.nextInt(150)+1;
        if (this.status==1)
        {
                if (this.elec+elec>1000)
                {
                        System.out.println("Electricity Usage exceeded");
                        this.balance=this.balance - (1000-this.elec);
                        this.elec=1000;
                        this.turnOff();
                }
                else
                {
                        this.elec=this.elec+elec;
                        this.balance=this.balance-elec;
                }

        }
        else
                System.out.println("The Meter has been turned off");

}

double getElec()
{
        this.useElectricity();
        return this.elec;

}

double getBill()
{
        this.getBalance();
        double bill=0;
        if (this.balance<0)
        {
                bill = Math.abs(balance)*rate;
        }
        return bill;
}

void changeType(int type)
{
        this.type=type;
}

}
