package yemekkovasi;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author onur
 */
public class Kullanici {

    String kadi;
    String sifre;
    String telefon;
    String ad;
    String adres;
    int hesap;
    int k_tip; // 2001 -> Firma // 1001 -> Müşteri

    static Scanner sc = new Scanner(System.in);

    public static int KullaniciKontrol() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı giriniz(Çıkış için 0): ");
        String ka = sc.nextLine();
        if (ka.equals("0")) {
            return -100;
        }
        System.out.println("Şifrenizi giriniz");
        String sifre = sc.nextLine();
        return KullaniciKontrol(ka, sifre);
    }
    
    public static int KullaniciKontrol(String ka, String sifre){
          for (int i = 0; i < Test.firmalar.size(); i++) {
            if (ka.equals(Test.firmalar.get(i).kadi) && sifre.equals(Test.firmalar.get(i).sifre)) {
                return i + (Test.firmalar.get(i).k_tip * 1000);

            }
        }
        for (int i = 0; i < Test.musteriler.size(); i++) {
            if (ka.equals(Test.musteriler.get(i).kadi) && sifre.equals(Test.musteriler.get(i).sifre)) {
                return i + (Test.musteriler.get(i).k_tip * 1000);

            }
        }
        return -1;
    }
    public static void GirisDegistirme(int ka_index) {
        System.out.println("Giriş bilgilerinizi düzenliyorsunuz.");
        System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
        System.out.println("-----------------------------");
        System.out.println("Kullanıcı adınız: ");
        String ka = sc.nextLine().trim();

        System.out.println("Şifre: ");
        String sifre = sc.nextLine().trim();
        GirisDegistirme(ka_index, ka, sifre);
    }
    
    public static void GirisDegistirme(int ka_index, String ka, String sifre){
         if (Tip(ka_index) == 1) {
            if (ka.length() != 0) {
                Test.musteriler.get(Kimlik(ka_index)).kadi = ka;
            }
            if (sifre.length() != 0) {
                Test.musteriler.get(Kimlik(ka_index)).sifre = sifre;
            }
        } else {
            if (ka.length() != 0) {
                Test.firmalar.get(Kimlik(ka_index)).kadi = ka;
            }
            if (sifre.length() != 0) {
                Test.firmalar.get(Kimlik(ka_index)).sifre = sifre;
            }
        }
      
    }

    public static void BilgiDegistirme(int ka_index) {
        System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
        System.out.println("Ad Soyad: ");
        String yeni_ad = sc.nextLine().trim();

        System.out.println("Telefon: ");
        String yeni_telefon = sc.nextLine().trim();

        System.out.println("Adres: ");
        String yeni_Adres = sc.nextLine().trim();
        BilgiDegistirme(ka_index, yeni_ad, yeni_telefon, yeni_Adres);
      
    }
    
    public static void BilgiDegistirme(int ka_index, String yeni_ad, String yeni_telefon, String yeni_Adres){
          if (Tip(ka_index) == 1) {
            Musteri degisen_musteri = Test.musteriler.get(Kimlik(ka_index));

            degisen_musteri.ad = yeni_ad.length()==0 ? Test.musteriler.get(Kimlik(ka_index)).ad : yeni_ad;
            degisen_musteri.telefon = yeni_telefon.length()==0  ? Test.musteriler.get(Kimlik(ka_index)).telefon : yeni_telefon;
            degisen_musteri.adres = yeni_Adres.length()==0  ? Test.musteriler.get(Kimlik(ka_index)).adres : yeni_Adres; 

            Test.musteriler.set(Kimlik(ka_index), degisen_musteri);
        } else {
            Firma degisen_firma = Test.firmalar.get(Kimlik(ka_index));
            degisen_firma.ad = yeni_ad.length()==0  ? Test.firmalar.get(Kimlik(ka_index)).ad : yeni_ad;
            degisen_firma.telefon = yeni_telefon.length()==0  ? Test.firmalar.get(Kimlik(ka_index)).telefon : yeni_telefon;
            degisen_firma.adres = yeni_Adres.length()==0  ? Test.firmalar.get(Kimlik(ka_index)).adres : yeni_Adres; 
            Test.firmalar.set(Kimlik(ka_index), degisen_firma);
        }
    }

    public static int Tip(int id) {// Kullanıcı firma mı kullanıcı mı
        return id / 1000;
    }

    public static int Kimlik(int id) {// kullanıcı kimlik nosu alır
        return id % 1000;
    }

}
