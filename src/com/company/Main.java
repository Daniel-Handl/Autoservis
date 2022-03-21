package com.company;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Autoservis a = new Autoservis(1000,"Praha");

    }
}

class Autoservis{

    public Autoservis(int balance, String lokace){
        Balance = balance;
        Lokace = lokace;
        Jmeno = "Autoservis v " + lokace;
        Pocet_Transakci = 0;
    }
    public boolean Vlastni(Auto a){
        return Evidence.contains(a);
    }
    public void Oprav(Auto a,String zakaznik, Date datum){
        Balance += 5000;
        String[] eet = {UnikatniKod(datum),zakaznik,Jmeno,datum.toString()};
        EET.add(eet);
    }
    public void Prodej(Auto a,String nakupujici, Date datum){
        Balance += a.Cena;
        Evidence.remove(a);
        String[] eet = {UnikatniKod(datum),nakupujici,Jmeno,datum.toString()};
        EET.add(eet);
    }
    public void Nakup(Auto a,String prodavajici, Date datum){

        Balance -= a.Cena;
        Evidence.add(a);
        String[] eet = {UnikatniKod(datum),Jmeno,prodavajici,datum.toString()};
        EET.add(eet);

    }
    public String UnikatniKod(Date datum){
        Pocet_Transakci++;
        return Pocet_Transakci + datum.toString();
    }
    public int Pocet_Transakci;
    public String Lokace;
    public HashSet<Auto> Evidence;
    public List<String[]> EET;
    public double Balance;
    public String Jmeno;
}
class Auto{
    public Auto(){

    }

    public String SPZ;
    public int Vaha;
    public int Delka;
    public int Cena;
    public String pohona_hmota;

}
