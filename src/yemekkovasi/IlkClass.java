package yemekkovasi;

import java.util.ArrayList;
import java.util.Scanner;

public class IlkClass {

    public static void main(String[] args) {
        boolean don = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<Musteri> musteriler = new ArrayList<Musteri>() {
            {
                add(new Musteri("Onur", "Kınay", "0555", "Halıcıoğlu", "onur123", "o1234"));
                add(new Musteri("Ahmet", "Yılmaz", "0554", "Fatih", "ahmet4", "ay144"));
                add(new Musteri("Mehmet", "Yenilmez", "0553", "Küçükçamlıca", "mehmet44", "mehyen456"));
                add(new Musteri("Hakan", "İpek", "0552", "Mecidiyeköy", "hakan61", "urfaligenc"));
                add(new Musteri("Ayşe", "Çalışkan", "0011", "Beylidüzü", "ayse12", "ayse987"));
            }
        };

        ArrayList<Firma> firmalar = new ArrayList<Firma>() {
            {
                add(new Firma("Urfalı Dayı'nın Yeri", "Fatih", "0566",
                        new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Lahmacun", 5));
                        add(new Yemek("Mercimek Çorbası", 6));
                        add(new Yemek("Urfa DÜrüm", 5));
                    }
                }, "urfali", "qwerty1"));
                add(new Firma("Adanalı Amca'nın Yeri", "Halıcıoğlu", "0544", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Lahmacun", 5));
                        add(new Yemek("İşkembe Çorbası", 6));
                        add(new Yemek("Adana DÜrüm", 5));
                    }
                }, "adanali", "asdfgh1"));
                add(new Firma("Japon Recep'in Mekanı", "Acıbadem", "0558", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Suşi", 15));
                        add(new Yemek("Ramen", 30));
                        add(new Yemek("Noodle", 15));
                    }
                }, "japonya4", "osaka11"));
                add(new Firma("PizzaLove", "Beyoğlu", "0548", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Küçük Pizza", 5));
                        add(new Yemek("Orta Pizza", 15));
                        add(new Yemek("Büyük Pizza", 25));
                    }
                }, "pizza6", "italya"));
                add(new Firma("Bağcılar Döner", "Bağcılar", "0566", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Yarım Döner", 5));
                        add(new Yemek("Lavaş Döner", 15));
                        add(new Yemek("Porsion Döner", 20));
                    }
                }, "bagcilar", "demirkapi"));
            }
        };

        ArrayList<Siparis> siparisler = new ArrayList<>();

        siparisler.add(new Siparis(0, 0, new String[][]{{"Lahmacun", "5"}}));
        siparisler.add(new Siparis(1, 0, new String[][]{{"Adana Dürüm", "10"}}));

        do {
            System.out.println("YemekKovası'na hoşgeldiniz");
            System.out.println("Kullanıcı tipi seçiniz: \n1. Firma\n2. Müşteri");
            if (sc.nextInt() == 1) {

                int firma_index = -1;
                do {
                    firma_index = Kullanici.KullaniciKontrol(firmalar);
                    if (firma_index == -1) {
                        System.out.println("Böyle bir kullanıcı yok. Lütfen tekrar deneyiniz. Çıkmak için CIK yazarak Enter'layın");

                    }
                } while (firma_index == -1);

                ArrayList<Siparis> firma_siparisler = Firma.GetSiparisler(siparisler, firma_index);

                int komut = -1;
                do {
                    System.out.println("YemekKovası Firma Sayfasına hoşgeldiniz " + firmalar.get(firma_index).ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Yemek menüsü işlemleri");
                    System.out.println("3. Kullanıcı bilgilerimi değiştir");
                    System.out.println("4. Giriş bilgilerimi değiştir");
                    komut = sc.nextInt();
                    switch (komut) {
                        case 1:
                            Firma.SiparisListele(firmalar, firma_siparisler, musteriler, firma_index);
                            System.out.println("Görüntülemek istediğiniz siparisi yazınız: ");
                            int siparis_kod = sc.nextInt() - 1;
                            Firma.SiparisDetay(musteriler, siparisler, siparis_kod);
                            Firma.SiparisDurumu(siparisler, siparis_kod);
                            break;
                        case 2:
                            int alt_komut = -2;
                            do {
                                System.out.println("--Menü Listesi--");
                                for (int i = 0; i < firmalar.get(firma_index).menuler.size(); i++) {
                                    Yemek menu = firmalar.get(firma_index).menuler.get(i);

                                    System.out.println((i + 1) + ". " + menu.getAd() + " Ücret: " + menu.fiyat);
                                }
                                System.out.println("--Menü Listesi--");
                                System.out.println("1. Menüye yemek ekle");
                                System.out.println("2. Menüden yemek sil");
                                System.out.println("3. Menüden yemek düzenle");
                                System.out.println("Çıkmak için -1 yazın");
                                alt_komut = sc.nextInt();
                                switch (alt_komut) {
                                    case 1:
                                        System.out.println("Menüye Yemek Ekleme");
                                        System.out.println("Yemek adı: ");
                                        String yemek_ad = sc.next();
                                        System.out.println("Porsiyon ücreti: ");
                                        int yemek_fiyat = sc.nextInt();
                                        firmalar.get(firma_index).menuler.add(new Yemek(yemek_ad, yemek_fiyat));
                                        System.out.println("Yemek menüye eklenmiştir");
                                        break;
                                    case 2:
                                        System.out.println("Menüden yemek Silme");
                                        System.out.println("Silmek istediğiniz yemek numarasını yazınız");
                                        int yemek_index = sc.nextInt() - 1;
                                        if (yemek_index >= 0) {
                                            firmalar.get(firma_index).menuler.remove(yemek_index);
                                        } else {
                                            System.out.println("Yemek numarası negatif olamaz");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Menüden Yemek Düzenleme");
                                        System.out.println("Düzenlemek istediğiniz yemek numarasını yazınız");
                                        int yemekd_index = sc.nextInt() - 1;
                                        if (yemekd_index >= 0) {
                                            System.out.println("Değiştirmek istemediğiniz özelliği boş bırakarak devam ediniz");
                                            System.out.println("Yemek adı: ");
                                            String yeni_yemek = sc.next();
                                            System.out.println("Fiyatı: ");
                                            int yeni_fiyat = sc.nextInt();

                                            firmalar.get(firma_index).menuler.set(yemekd_index, new Yemek(yeni_yemek, yeni_fiyat));

                                        } else {
                                            System.out.println("Yemek numarası negatif olamaz");
                                        }
                                    case 4:
                                        System.out.println("Giriş bilgilerinizi düzenliyorsunuz.");
                                        System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
                                        System.out.println("-----------------------------");
                                        System.out.println("Kullanıcı adınız: ");
                                        String ka = sc.next();

                                        System.out.println("Şifre: ");
                                        String sifre = sc.next();

                                        firmalar.get(firma_index).kadi = ka;
                                        firmalar.get(firma_index).sifre = sifre;
                                        System.out.println("Giriş bilgileriniz değiştirdiniz.");
                                        break;
                                    default:
                                        break;
                                }

                            } while (alt_komut != -1);
                            break;
                        case 3:
                            System.out.println(firmalar.get(firma_index));
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");
                            String komut_alt = sc.next();
                            if ("E".equals(komut_alt)) {
                                System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
                                Firma degisen_firma = new Firma();
                                System.out.println("Adınız: ");
                                String yeni_ad = sc.next();
                                degisen_firma.ad = yeni_ad.equals("") ? firmalar.get(firma_index).ad : yeni_ad;

                                System.out.println("Telefon: ");
                                String yeni_telefon = sc.next();
                                degisen_firma.telefon = yeni_telefon.equals("") ? firmalar.get(firma_index).telefon : yeni_telefon;

                                System.out.println("Adres: ");
                                String yeni_Adres = sc.next();
                                degisen_firma.adres = yeni_Adres.equals("") ? firmalar.get(firma_index).adres : yeni_Adres;

                                firmalar.set(firma_index, degisen_firma);

                            }
                            break;
                        default:
                            break;
                    }

                } while (komut != -1);

            } else {
                int musteri_index = -1;
                do {
                    musteri_index = Kullanici.KullaniciKontrol(musteriler, 0);
                    if (musteri_index == -1) {
                        System.out.println("Böyle bir kullanıcı yok. Lütfen tekrar deneyiniz. Çıkmak için CIK yazarak Enter'layın");

                    }
                } while (musteri_index == -1);

                int komut = -1;
                do {

                    ArrayList<Siparis> musteri_siparisler = Musteri.GetSiparisler(siparisler, musteri_index);

                    System.out.println("YemekKovası Müşteri Sayfasına hoşgeldiniz " + musteriler.get(musteri_index).ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Sipariş Ver");
                    System.out.println("3. Kullanıcı bilgilerimi görüntüle veya değiştir");
                    System.out.println("4. Giriş bilgilerimi değiştir");

                    komut = sc.nextInt();

                    switch (komut) {
                        case 1:
                            Musteri.SiparisListele(firmalar, musteri_siparisler);
                            System.out.println("Görüntülemek istediğiniz siparisi yazınız: ");
                            int siparis_kod = sc.nextInt() - 1;
                            Musteri.SiparisDetay(firmalar, siparis_kod, musteri_siparisler);
                            Musteri.Iade(firmalar, siparis_kod, siparisler, musteri_siparisler);
                            break;
                        case 2:
                            Musteri.addSiparis(firmalar, musteri_index, siparisler);
                            break;
                        case 3:
                            System.out.println(musteriler.get(musteri_index));
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");
                            String komut_alt = sc.next();
                            if ("E".equals(komut_alt)) {
                                System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
                                Musteri degisen_musteri = new Musteri();
                                System.out.println("Adınız: ");
                                String yeni_ad = sc.next();
                                degisen_musteri.ad = yeni_ad.equals("") ? musteriler.get(musteri_index).ad : yeni_ad;

                                System.out.println("Soyadınız: ");
                                String yeni_soyad = sc.next();
                                degisen_musteri.soyad = yeni_soyad.equals("") ? musteriler.get(musteri_index).soyad : yeni_soyad;

                                System.out.println("Telefon: ");
                                String yeni_telefon = sc.next();
                                degisen_musteri.telefon = yeni_telefon.equals("") ? musteriler.get(musteri_index).telefon : yeni_telefon;

                                System.out.println("Adres: ");
                                String yeni_Adres = sc.next();
                                degisen_musteri.adres = yeni_Adres.equals("") ? musteriler.get(musteri_index).adres : yeni_Adres;

                                degisen_musteri.kadi = musteriler.get(musteri_index).kadi;
                                degisen_musteri.sifre = musteriler.get(musteri_index).sifre;

                                musteriler.set(musteri_index, degisen_musteri);

                            }
                            break;
                        case 4:
                            System.out.println("Giriş bilgilerinizi düzenliyorsunuz.");
                            System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
                            System.out.println("-----------------------------");
                            System.out.println("Kullanıcı adınız: ");
                            String ka = sc.next();

                            System.out.println("Şifre: ");
                            String sifre = sc.next();

                            musteriler.get(musteri_index).kadi = ka;
                            musteriler.get(musteri_index).sifre = sifre;
                            System.out.println("Giriş bilgileriniz değiştirdiniz.");
                            break;
                        default:
                            break;
                    }
                } while (don);
            }
        } while (true);
    }

}
