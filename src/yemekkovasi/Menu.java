/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yemekkovasi;

import java.util.Scanner;

/**
 *
 * @author onur
 */
public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static void Getir(int firma_index) {
        System.out.println("--Menü Listesi--");
        for (int i = 0; i < Test.firmalar.get(firma_index).menuler.size(); i++) {
            Yemek menu = Test.firmalar.get(firma_index).menuler.get(i);

            System.out.println((i + 1) + ". " + menu.getAd() + " Ücret: " + menu.fiyat);
        }

        System.out.println("--Menü Listesi--");
    }

    public static void YemekEkle(String yemek_ad, int yemek_fiyat, int firma_index) {
        Test.firmalar.get(firma_index).menuler.add(new Yemek(yemek_ad, yemek_fiyat));
        System.out.println("Yemek menüye eklenmiştir");
    }

    public static void YemekEkle(int firma_index) {
        System.out.println("Menüye Yemek Ekleme");
        System.out.println("Yemek adı: ");
        String yemek_ad = sc.next();
        System.out.println("Porsiyon ücreti: ");
        int yemek_fiyat = sc.nextInt();
        Menu.YemekEkle(yemek_ad, yemek_fiyat, firma_index);
        System.out.println("Yemek menüye eklenmiştir");
    }

    public static void YemekSil(int yemek_index, int firma_index) {
        if (yemek_index >= 0) {
            Test.firmalar.get(firma_index).menuler.remove(yemek_index);
        } else {
            System.out.println("Yemek numarası negatif olamaz");
        }
    }

    public static void YemekSil(int firma_index) {
        System.out.println("Menüden yemek Silme");
        System.out.println("Silmek istediğiniz yemek numarasını yazınız");
        int yemek_index = sc.nextInt() - 1;

        Menu.YemekSil(yemek_index, firma_index);

    }

    public static void YemekDuzenle(String yeni_yemek, int yeni_fiyat, int firma_index, int yemekd_index) {
        if (yemekd_index >= 0) {
            Test.firmalar.get(firma_index).menuler.set(yemekd_index, new Yemek(yeni_yemek, yeni_fiyat));
        } else {
            System.out.println("Yemek numarası negatif olamaz");
        }
    }

    public static void YemekDuzenle(int firma_index) {
        System.out.println("Menüden Yemek Düzenleme");
        System.out.println("Düzenlemek istediğiniz yemek numarasını yazınız");
        int yemekd_index = sc.nextInt() - 1;
        if (yemekd_index >= 0) {
            System.out.println("Değiştirmek istemediğiniz özelliği boş bırakarak devam ediniz");
            System.out.println("Yemek adı: ");
            String yeni_yemek = sc.next();
            System.out.println("Fiyatı: ");
            int yeni_fiyat = sc.nextInt();

            YemekDuzenle(yeni_yemek, yeni_fiyat, firma_index, yemekd_index);

        } else {
            System.out.println("Yemek numarası negatif olamaz");
        }
    }
}
