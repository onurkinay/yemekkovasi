package yemekkovasi;

import java.util.ArrayList;
import java.util.Scanner;

public class Musteri extends Kullanici {
    //  int kimlik;

    Musteri(String ad, String telefon, String adres, String kadi, String sifre) {
        this.ad = ad;
        this.telefon = telefon;
        this.adres = adres;
        this.kadi = kadi;
        this.sifre = sifre;
        this.k_tip = 1;
    }

    public Musteri() {
    }

    public void BilgiDegistir(Musteri degisim) {
        this.ad = degisim.ad;
        this.telefon = degisim.telefon;
        this.adres = degisim.adres;
        this.kadi = degisim.kadi;
        this.sifre = degisim.sifre;
    }

    @Override
    public String toString() {
        return "Ad Soyad: " + ad + "\nTelefon: " + telefon + "\nAdres: " + adres;
    }

    ////STATIC FUNCS.
    public static void addSiparis(int musteri_index) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sipariş vermek istediğiniz firmayi seçiniz:");
        for (int i = 0; i < Test.firmalar.size(); i++) {
            System.out.println((i + 1) + ". " + Test.firmalar.get(i).ad);
        }
        int firma = sc.nextInt() - 1;
        int urun = -1;
        ArrayList<String[]> siparis = new ArrayList<>();
        do {
            System.out.println("Menü: ");
            for (int i = 0; i < Test.firmalar.get(i).menuler.size(); i++) {
                System.out.println((i + 1) + " " + Test.firmalar.get(firma).menuler.get(i).ad + " Ücret:" + Test.firmalar.get(firma).menuler.get(i).fiyat);
            }
            System.out.println("Ürün seçin(Devam etmek için -1 yazınız): ");
            urun = sc.nextInt() - 1;
            if (urun == -2) {
                break;
            }
            System.out.println("Porsiyon: ");
            int porsiyon = sc.nextInt();
            siparis.add(new String[]{Test.firmalar.get(firma).menuler.get(urun).ad, Integer.toString(porsiyon)});
        } while (true);

        System.out.println("Siparişiniz: ");

        for (int i = 0; i < siparis.size(); i++) {
            System.out.println("Ürün adı: " + siparis.get(i)[0] + " Porsiyon: " + siparis.get(i)[1]);
        }
        System.out.println("Toplam tutar: " + Siparis.toplamTutar(Test.firmalar.get(firma), siparis));
        System.out.println("Siparişi onaylıyor musunuz?");
        String yeniDurum = sc.next();
        if (yeniDurum.equals("E")) {
            Test.siparisler.add(new Siparis(firma, musteri_index, siparis.toArray(new String[0][0])));
            System.out.println("Siparişiniz alındı");
        } else {
            System.out.println("---Siparişiniz onaylamadınız---");
        }

    }

    public static void SiparisDetay(int siparis_kod) {
        Siparis siparis = Test.siparisler.stream().filter(p -> p.kimlik == siparis_kod).findFirst().get();
        System.out.println("Siparis detay:"
                + "\nFirma adı:" + Test.firmalar.get(siparis.firma_kimlik).ad
                + "\nAdresi: " + Test.firmalar.get(siparis.firma_kimlik).adres
                + "\nTelefonu:" + Test.firmalar.get(siparis.firma_kimlik).telefon);

        for (int i = 0; i < siparis.siparis.length; i++) {
            int porsiyon = Integer.parseInt(siparis.siparis[i][1]);
            System.out.println(siparis.siparis[i][0] + " Porsiyon: " + porsiyon);
        }

        System.out.println("Sipariş durumu: " + siparis.getDurum());
    }

    public static void Iade(int siparis_kod) {
        Scanner sc = new Scanner(System.in);

        Siparis siparis = Test.siparisler.stream().filter(p -> p.kimlik == siparis_kod).findFirst().get();
        if (siparis.durum == 0) {
            System.out.println("Siparişi iade etmek istiyor musunuz? (Evet için E - Hayır için H)");
            String iade = sc.next();
            if (iade.equals("E")) {
                Test.siparisler.remove(siparis.kimlik);
                System.out.println("İade basarili");
            }
        } else {
            System.out.println("Bu aşamada sipariş iptal edilemez");
            sc.next();
        }
    }

    public static void SiparisListele(int musteri_index) {
        for (int i = 0; i < Test.siparisler.size(); i++) {

            if (musteri_index == Test.siparisler.get(i).musteri_kimlik) {
                System.out.println((Test.siparisler.get(i).kimlik) + ". " + Test.firmalar.get(Test.siparisler.get(i).firma_kimlik).ad + " (toplam Tutar:" + Test.siparisler.get(i).toplamTutar(Test.firmalar.get(Test.siparisler.get(i).firma_kimlik)) + ")");// i. müsterinin siparişi (toplam TL)
             
            }
        
        }
    }

    //////STATIC FUNCS.
}
