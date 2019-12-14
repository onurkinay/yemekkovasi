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
    
    
    public static int KullaniciKontrol(Firma[] firmalar) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı giriniz: ");
        String ka = sc.next();
        System.out.println("Şifrenizi giriniz");
        String sifre = sc.next();
        for (int i = 0; i < firmalar.length; i++) {
            if (ka.equals(firmalar[i].kadi) && sifre.equals(firmalar[i].sifre)) {
                return i;

            }
        }
        return -1;
    }

    public static int KullaniciKontrol(Musteri[] musteriler) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı giriniz: ");
        String ka = sc.next();
        System.out.println("Şifrenizi giriniz");
        String sifre = sc.next();
        for (int i = 0; i < musteriler.length; i++) {
            if (ka.equals(musteriler[i].kadi) && sifre.equals(musteriler[i].sifre)) {
                return i;

            }
        }
        return -1;
    }
}
