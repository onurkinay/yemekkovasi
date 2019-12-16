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
    int k_tip;

    static Scanner sc = new Scanner(System.in);

    public static int KullaniciKontrol() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı giriniz(Çıkış için 0): ");
        String ka = sc.next();
        if(ka.equals("0")) return -100;
        System.out.println("Şifrenizi giriniz");
        String sifre = sc.next();
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
        String ka = sc.next();

        System.out.println("Şifre: ");
        String sifre = sc.next();
        if (Tip(ka_index) == 1) {
            Test.musteriler.get(Kimlik(ka_index)).kadi = ka;
            Test.musteriler.get(Kimlik(ka_index)).sifre = sifre;
        } else {

            Test.firmalar.get(Kimlik(ka_index)).kadi = ka;
            Test.firmalar.get(Kimlik(ka_index)).sifre = sifre;
        }
        System.out.println("Giriş bilgileriniz değiştirdiniz.");
    }

    public static void BilgiDegistirme(int ka_index) {
        System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
        System.out.println("Ad Soyad: ");
        String yeni_ad = sc.next();

        System.out.println("Telefon: ");
        String yeni_telefon = sc.next();
        
        System.out.println("Adres: ");
        String yeni_Adres = sc.next();
        
        if (Tip(ka_index) == 1) {
            Musteri degisen_musteri = new Musteri();

            degisen_musteri.ad = yeni_ad.equals("") ? Test.musteriler.get(Kimlik(ka_index)).ad : yeni_ad;
            degisen_musteri.telefon = yeni_telefon.equals("") ? Test.musteriler.get(Kimlik(ka_index)).telefon : yeni_telefon;
            degisen_musteri.adres = yeni_Adres.equals("") ? Test.musteriler.get(Kimlik(ka_index)).adres : yeni_Adres;
            degisen_musteri.kadi = Test.musteriler.get(Kimlik(ka_index)).kadi;
            degisen_musteri.sifre = Test.musteriler.get(Kimlik(ka_index)).sifre;

            Test.musteriler.set(Kimlik(ka_index), degisen_musteri);
        } else {
            Firma degisen_firma = new Firma();
            degisen_firma.ad = yeni_ad.equals("") ? Test.firmalar.get(Kimlik(ka_index)).ad : yeni_ad;
            degisen_firma.telefon = yeni_telefon.equals("") ? Test.firmalar.get(Kimlik(ka_index)).telefon : yeni_telefon;
            degisen_firma.adres = yeni_Adres.equals("") ? Test.firmalar.get(Kimlik(ka_index)).adres : yeni_Adres;
            degisen_firma.kadi = Test.firmalar.get(Kimlik(ka_index)).kadi;
            degisen_firma.sifre = Test.firmalar.get(Kimlik(ka_index)).sifre;
            Test.firmalar.set(Kimlik(ka_index), degisen_firma);
        }
    }

    public static int Tip(int id) {
        return id / 1000;
    }

    public static int Kimlik(int id) {
        return id % 1000;
    }

}
