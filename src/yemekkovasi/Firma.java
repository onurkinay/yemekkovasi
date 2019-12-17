package yemekkovasi;

import java.util.ArrayList;
import java.util.Scanner;

public class Firma extends Kullanici{
    // int kimlik;

    String ad;
    String adres;
    ArrayList<Yemek> menuler;// yemek adi - fiyat
 

    Firma(/*int kimlik*/String ad, String adres, String telefon, ArrayList<Yemek> menuler, String kadi, String sifre) {
        // this.kimlik = kimlik;
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

  
    
    
    /////STATIC FUNCS.
    public static void SiparisDetay(int siparis_kod) {

        Siparis siparis = Test.siparisler.stream().filter( p-> p.kimlik == siparis_kod).findFirst().get();
        System.out.println("Siparis detay:"
                + "\nMüşteri adı:" + Test.musteriler.get(siparis.musteri_kimlik).ad
                + "\nAdresi: " + Test.musteriler.get(siparis.musteri_kimlik).adres
                + "\nTelefonu:" + Test.musteriler.get(siparis.musteri_kimlik).telefon);

        for (int i = 0; i < siparis.siparis.length; i++) {
            int porsiyon = Integer.parseInt(siparis.siparis[i][1]);
            System.out.println(siparis.siparis[i][0] + " Porsiyon: " + porsiyon);

        }

        System.out.println("Sipariş durumu: " + siparis.getDurum());
        
    }

    public static void SiparisDurumu(int siparis_kod) {
        Scanner sc = new Scanner(System.in);
        String yeniDurum = "";
        
        Siparis siparis = Test.siparisler.stream().filter( p-> p.kimlik == siparis_kod).findFirst().get();
        if (siparis.durum == 0) {
            System.out.println("Sipariş yola çıktı mı? (Evet için E - Hayır için H)");
            yeniDurum = sc.next();
            if (yeniDurum.equals("E")) {
                siparis.durum = 1;
            }
        } else if (siparis.durum == 1) {
            System.out.println("Sipariş teslim edildi mi? (Evet için E - Hayır için H)");
            yeniDurum = sc.next();
            if (yeniDurum.equals("E")) {
                siparis.durum = 2;
            }
        }
    }
     public static void SiparisListele(int firma_index){
         
         for (int i = 0; i < Test.siparisler.size(); i++) {
             if(firma_index == Test.siparisler.get(i).firma_kimlik){
                            System.out.println((Test.siparisler.get(i).kimlik) + ". " + Test.musteriler.get(Test.siparisler.get(i).musteri_kimlik).ad
                                    + " (toplam Tutar:" + Test.siparisler.get(i).toplamTutar(Test.firmalar.get(firma_index)) + ")");// i. müsterinin siparişi (toplam TL)
                   
             }
    }}
     
      
     ////STATIC FUNCS.
}
