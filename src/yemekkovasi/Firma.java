package yemekkovasi;

import java.util.ArrayList;
public class Firma extends Kullanici{
    ArrayList<Yemek> menuler;
 

    Firma(String ad, String adres, String telefon, ArrayList<Yemek> menuler, String kadi, String sifre) {
    
        this.ad = ad;
        this.adres = adres;
        this.telefon = telefon;
        this.menuler = menuler;
        this.kadi = kadi;
        this.sifre = sifre;
        this.k_tip = 2;
    }

    public Firma() {
    }
    

    public void BilgiDegistir(Firma degisen){
      
        // this.kimlik = kimlik;
        this.ad = degisen.ad;
        this.adres = degisen.adres;
        this.telefon = degisen.telefon;
        this.menuler = degisen.menuler;
        this.kadi = degisen.kadi;
        this.sifre = degisen.sifre;
    }

    @Override
    public String toString() {
        return "Ad: " + ad + "\nAdres=" + adres + "\nTelefon Numarası=" + telefon;
    }
 
}
