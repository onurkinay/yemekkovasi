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
public class Sistem {

    static int ka_index;

    public static void Baslat() {
        Scanner sc = new Scanner(System.in);

        boolean don = true;
        do {
            System.out.println("YemekKovası'na hoşgeldiniz");

            do {
                ka_index = Kullanici.KullaniciKontrol();
                if (ka_index == -1) {
                    System.out.println("Böyle bir kullanıcı yok. Lütfen tekrar deneyiniz.");

                } else if (ka_index == -100) {
                    System.exit(0);
                }
            } while (ka_index == -1);
            if (Kullanici.Tip(ka_index) == 2) {
                int firma_index = Kullanici.Kimlik(ka_index);
                Firma firma = Test.firmalar.get(firma_index);
                int komut = -1;
                do {
                    System.out.println("YemekKovası Firma Sayfasına hoşgeldiniz " + firma.ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Yemek menüsü işlemleri");
                    System.out.println("3. Kullanıcı bilgilerimi değiştir");
                    System.out.println("4. Giriş bilgilerimi değiştir");
                    System.out.println("5. Hesaptan para çek");
                    System.out.println("0. Çıkış");
                    komut = sc.nextInt();
                    switch (komut) {
                        case 1:
                            if (!Siparis.Listele(firma_index, true)) {
                                System.out.println("Herhangi bir siparişiniz bulunmuyor");

                                break;
                            }
                            System.out.println("Görüntülemek istediğiniz siparisi yazınız(Çıkmak için -1 yazınız): ");
                            int gelen_kod = sc.nextInt();
                            if (gelen_kod == -1) {
                                break;
                            } else if (gelen_kod > Test.siparisler.size() || gelen_kod < -1) {
                                System.out.println("Geçersiz sipariş kodu");
                                break;
                            }
                            Siparis siparis = Siparis.Getir(gelen_kod);
                            if (siparis.firma_kimlik == firma_index) {
                                System.out.println(siparis);
                                Siparis.Durum(siparis.kimlik);
                            } else {
                                System.out.println("Yanlış sipariş");
                            }
                            break;
                        case 2:
                            int alt_komut = -2;
                            do {
                                Menu.Getir(firma_index);
                                System.out.println("1. Menüye yemek ekle");
                                System.out.println("2. Menüden yemek sil");
                                System.out.println("3. Menüden yemek düzenle");
                                System.out.println("Çıkmak için -1 yazın");
                                alt_komut = sc.nextInt();
                                switch (alt_komut) {
                                    case 1:
                                        Menu.YemekEkle(firma_index);
                                        break;
                                    case 2:
                                        Menu.YemekSil(firma_index);
                                        break;
                                    case 3:
                                        Menu.YemekDuzenle(firma_index);

                                    default:
                                        break;
                                }

                            } while (alt_komut != -1);
                            break;
                        case 3:
                            System.out.println(firma);
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");
                            String komut_alt = sc.next();
                            if ("E".equals(komut_alt)) {
                                Kullanici.BilgiDegistirme(ka_index);
                            }
                            break;
                        case 4:
                            Kullanici.GirisDegistirme(ka_index);
                            break;
                        case 5:
                            System.out.println("Hesabınızda bulunan para miktari:" + firma.hesap);
                            System.out.println("Ne kadar para çekeceksiniz?: ");
                            int miktar = sc.nextInt();
                            if (miktar > 0) {
                                firma.ParaCek(miktar);
                            } else {
                                System.out.println("Girilen sayı 0 veya 0'dan küçük olamaz");
                            }
                            break;
                        default:
                            break;
                    }
                    Bekle();
                } while (komut != 0);
            } else {
                int musteri_index = Kullanici.Kimlik(ka_index);
                Musteri musteri = Test.musteriler.get(musteri_index);
                int komut = -1;
                do {

                    System.out.println("YemekKovası Müşteri Sayfasına hoşgeldiniz " + musteri.ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Sipariş Ver");
                    System.out.println("3. Kullanıcı bilgilerimi görüntüle veya değiştir");
                    System.out.println("4. Giriş bilgilerimi değiştir");
                    System.out.println("5. Hesaba para ekle");

                    System.out.println("0. Çıkış");
                    komut = sc.nextInt();

                    switch (komut) {
                        case 1:
                            if (!Siparis.Listele(musteri_index)) {
                                System.out.println("Herhangi bir siparişiniz bulunmuyor");

                                break;
                            }

                            System.out.println("Görüntülemek istediğiniz siparisi yazınız(Çıkış için -1 yazınız): ");
                            int gelen_siparis = sc.nextInt();
                            if (gelen_siparis == -1) {
                                break;
                            } else if (gelen_siparis > Test.siparisler.size() || gelen_siparis < -1) {
                                System.out.println("Geçersiz sipariş kodu");
                                break;
                            }
                            Siparis siparis = Siparis.Getir(gelen_siparis);
                            if (siparis.musteri_kimlik == musteri_index) {
                                System.out.println(siparis);
                                Siparis.Iade(siparis.kimlik);
                            } else {
                                System.out.println("Yanlış sipariş kodu");

                            }
                            break;
                        case 2:
                            Siparis.Ekle(musteri_index);
                            break;
                        case 3:
                            System.out.println(musteri);
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");
                            String komut_alt = sc.next();
                            if ("E".equals(komut_alt)) {
                                Kullanici.BilgiDegistirme(ka_index);
                            }
                            break;
                        case 4:
                            Kullanici.GirisDegistirme(ka_index);
                            break;
                        case 5:
                            System.out.println("Hesabınızda bulunan para miktari:" + musteri.hesap);
                            System.out.println("Ne kadar para yükleyeceksiniz?: ");
                            int miktar = sc.nextInt();
                            if (miktar > 0) {
                                musteri.ParaEkle(miktar);
                            } else {
                                System.out.println("Girilen sayı 0 veya 0'dan küçük olamaz");
                            }
                            break;
                        default:
                            break;
                    }
                    Bekle();
                } while (komut != 0);
            }
        } while (don);
    }

    public static String Bekle() {
        System.out.println("Devam etmek için ENTER'a basınız");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
}
