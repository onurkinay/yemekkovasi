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

    public static void Baslat() {
        Scanner sc = new Scanner(System.in);

        boolean don = true;
        do {
            System.out.println("YemekKovası'na hoşgeldiniz");
            int ka_index;
            do {
                ka_index = Kullanici.KullaniciKontrol();
                if (ka_index == -1) {
                    System.out.println("Böyle bir kullanıcı yok. Lütfen tekrar deneyiniz. Çıkmak için CIK yazarak Enter'layın");

                } else if (ka_index == -100) {
                    System.exit(0);
                }
            } while (ka_index == -1);
            if (Kullanici.Tip(ka_index) == 2) {
                int firma_index = Kullanici.Kimlik(ka_index);

                int komut = -1;
                do {
                    System.out.println("YemekKovası Firma Sayfasına hoşgeldiniz " + Test.firmalar.get(firma_index).ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Yemek menüsü işlemleri");
                    System.out.println("3. Kullanıcı bilgilerimi değiştir");
                    System.out.println("4. Giriş bilgilerimi değiştir");
                    System.out.println("0. Çıkış");
                    komut = sc.nextInt();
                    switch (komut) {
                        case 1:
                            Siparis.Listele(firma_index, true);
                            System.out.println("Görüntülemek istediğiniz siparisi yazınız(Çıkmak için -1 yazınız): ");
                            int gelen_kod = sc.nextInt();
                            if(gelen_kod == -1) break;
                            int siparis_kod = Test.siparisler.get(gelen_kod).kimlik;

                            Siparis.Detay(siparis_kod);
                            Siparis.Durum(siparis_kod);
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
                                    case 4:
                                        Kullanici.GirisDegistirme(ka_index);
                                        break;
                                    default:
                                        break;
                                }

                            } while (alt_komut != -1);
                            break;
                        case 3:
                            System.out.println(Test.firmalar.get(firma_index));
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");
                            String komut_alt = sc.next();
                            if ("E".equals(komut_alt)) {
                                Kullanici.BilgiDegistirme(ka_index);
                            }
                            break;
                        default:
                            break;
                    }

                } while (komut != 0);
            } else {
                int musteri_index = Kullanici.Kimlik(ka_index);
                int komut = -1;
                do {

                    System.out.println("YemekKovası Müşteri Sayfasına hoşgeldiniz " + Test.musteriler.get(musteri_index).ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Sipariş Ver");
                    System.out.println("3. Kullanıcı bilgilerimi görüntüle veya değiştir");
                    System.out.println("4. Giriş bilgilerimi değiştir");
                    System.out.println("0. Çıkış");
                    komut = sc.nextInt();

                    switch (komut) {
                        case 1:
                            if (!Siparis.Listele(musteri_index)) {
                                System.out.println("Herhangi bir siparişiniz bulunmuyor");
                                sc.next();
                                break;
                            }
                            
                            System.out.println("Görüntülemek istediğiniz siparisi yazınız(Çıkış için -1 yazınız): ");
                            int gelen_siparis = sc.nextInt();
                            if(gelen_siparis == -1) break;
                            int siparis_kod = Test.siparisler.get(gelen_siparis).kimlik;

                            Siparis.Detay(siparis_kod);
                            Siparis.Iade(siparis_kod);
                            break;
                        case 2:
                            Siparis.Ekle(musteri_index);
                            break;
                        case 3:
                            System.out.println(Test.musteriler.get(musteri_index));
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");
                            String komut_alt = sc.next();
                            if ("E".equals(komut_alt)) {
                                Kullanici.BilgiDegistirme(ka_index);
                            }
                            break;
                        case 4:
                            Kullanici.GirisDegistirme(ka_index);
                            break;
                        default:
                            break;
                    }
                } while (komut != 0);
            }
        } while (don);
    }
}
