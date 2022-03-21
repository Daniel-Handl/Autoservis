package com.company;

import java.sql.Date;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Company skoda = new Company();
        skoda.postav(new Autoservis(1000,"Praha", "veselá kola"));
        skoda.postav(new Autoservis(1000,"Praha","u karla"));
        int i = 1;
        for (Autoservis a:skoda.pobocky.values()) {
            a.Nakup(10);
            a.Nakup(new Auto("ZZZ32"+i,1,1,1,"benzin"),"Honza",new Date(System.currentTimeMillis()));
            i++;
        }
        System.out.println(skoda.najdiSPZ("ZZZ321"));
        System.out.println(skoda.najdiSPZ("ZZZ322"));
        skoda.zrus("veselá kola");
        System.out.println(skoda.najdiSPZ("ZZZ321"));
    }
}

class Company{
        public Company(){
            pobocky = new HashMap<>();
        }

        public void postav(Autoservis a){
            pobocky.put(a.Jmeno,a);
        }
        public void zrus(String a){
            pobocky.remove(a);
        }
        public String najdiSPZ(String SPZ){
            for (Autoservis a:pobocky.values()) {
                    if (a.Evidence.containsKey(SPZ)) return a.Jmeno;
            }
            return "Auto s SPZ:" + SPZ + "se nenachází na žádné pobočce";
        }
        HashMap<String,Autoservis> pobocky;
}

class Autoservis{

    public Autoservis(int balance, String lokace, String jmeno){
        Balance = balance;
        Lokace = lokace;
        Jmeno = jmeno;
        Pocet_Transakci = 0;
        Evidence = new HashMap<>();
        EET = new ArrayList<>();
    }
    public void Oprav(Auto a,String zakaznik, Date datum){
        Balance += 5000;
        String[] eet = {UnikatniKod(datum),zakaznik,Jmeno,datum.toString(),  Integer.toString(a.Cena), Double.toString(a.Cena*0.15)};
        EET.add(eet);
    }
    public void Prodej(Auto a,String nakupujici, Date datum){
        Balance += a.Cena;
        Evidence.remove(a.SPZ);
        String[] eet = {UnikatniKod(datum),nakupujici,Jmeno,datum.toString(), Integer.toString(a.Cena), Double.toString(a.Cena*0.15)};
        EET.add(eet);
    }
    public void Nakup(Auto a,String prodavajici, Date datum){

        Balance -= a.Cena;
        Evidence.put(a.SPZ,a);
        String[] eet = {UnikatniKod(datum),Jmeno,prodavajici,datum.toString(), Integer.toString(a.Cena), Double.toString(a.Cena*0.15)};
        EET.add(eet);

    }
    public void Nakup(int a){
        Random rand = new Random();
        for (int i = 0; i <= a; i++){
            Nakup( new Auto("ABC" + rand.nextInt(100,1000),rand.nextInt(100,1000),rand.nextInt(3,6),rand.nextInt(10000,1000000),"ropa"),"Honza",new Date(rand.nextInt(100,1000)));
        }
    }
    public String UnikatniKod(Date datum){
        Pocet_Transakci++;
        return Pocet_Transakci + datum.toString();
    }
    public int Pocet_Transakci;
    public String Lokace;
    public HashMap<String,Auto> Evidence;
    public List<String[]> EET;
    public double Balance;
    public String Jmeno;
}
class Auto{
    public Auto(String SPZ, int Vaha, int Delka, int Cena,String Pohona_Hmota){
        this.Cena = Cena;
        this.SPZ = SPZ;
        this.Vaha = Vaha;
        this.Delka = Delka;
        this.Pohona_hmota = Pohona_Hmota;
    }

    public String SPZ;
    public int Vaha;
    public int Delka;
    public int Cena;
    public String Pohona_hmota;

}
