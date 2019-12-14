package yemekkovasi;


import java.util.ArrayList;
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
    
    
    public static int KullaniciKontrol(ArrayList<Firma> firmalar) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı giriniz: ");
        String ka = sc.next();
        System.out.println("Şifrenizi giriniz");
        String sifre = sc.next();
        for (int i = 0; i < firmalar.size(); i++) {
            if (ka.equals(firmalar.get(i).kadi) && sifre.equals(firmalar.get(i).sifre)) {
                return i;

            }
        }
        return -1;
    }

    public static int KullaniciKontrol(ArrayList<Musteri> musteriler, int k) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı giriniz: ");
        String ka = sc.next();
        System.out.println("Şifrenizi giriniz");
        String sifre = sc.next();
        for (int i = 0; i < musteriler.size(); i++) {
            if (ka.equals(musteriler.get(i).kadi) && sifre.equals(musteriler.get(i).sifre)) {
                return i;

            }
        }
        return -1;
    }
}
