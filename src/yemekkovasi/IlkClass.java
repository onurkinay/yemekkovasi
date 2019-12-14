package yemekkovasi;

import java.util.ArrayList;
import java.util.Scanner; 

public class IlkClass {

    public static void main(String[] args) {
        boolean don = true;
        Scanner sc = new Scanner(System.in);
        Musteri[] musteriler = {
            new Musteri("Onur", "Kınay", "0555", "Halıcıoğlu", "onur123", "o1234"),
            new Musteri("Ahmet", "Yılmaz", "0554", "Fatih", "ahmet4", "ay144"),
            new Musteri("Mehmet", "Yenilmez", "0553", "Küçükçamlıca", "mehmet44", "mehyen456"),
            new Musteri("Hakan", "İpek", "0552", "Mecidiyeköy", "hakan61", "urfaligenc"),
            new Musteri("Ayşe", "Çalışkan", "0011", "Beylidüzü", "ayse12", "ayse987")
        };
        
        Firma[] firmalar = {
            new Firma("Urfalı Dayı'nın Yeri", "Fatih", "0566", new Yemek[]{new Yemek("Lahmacun", 5), new Yemek("Mercimek Çorbası", 6), new Yemek("Urfa DÜrüm", 5)}, "urfali", "qwerty1"),
            new Firma("Adanalı Amca'nın Yeri", "Halıcıoğlu", "0544",new Yemek[]{new Yemek("Lahmacun", 5), new Yemek("İşkembe Çorbası", 6), new Yemek("Adana DÜrüm", 5)}, "adanali", "asdfgh1"),
            new Firma("Japon Recep'in Mekanı", "Acıbadem", "0558", new Yemek[]{new Yemek("Suşi", 15), new Yemek("Ramen", 30), new Yemek("Noodle", 15)}, "japonya4", "osaka11"),
            new Firma("PizzaLove", "Beyoğlu", "0548", new Yemek[]{new Yemek("Küçük Pizza", 5), new Yemek("Orta Pizza", 15), new Yemek("Büyük Pizza", 25)}, "pizza6", "italya"),
            new Firma("Bağcılar Döner", "Bağcılar", "0566", new Yemek[]{new Yemek("Yarım Döner", 5), new Yemek("Lavaş Döner", 15), new Yemek("Porsion Döner", 20)}, "bagcilar", "demirkapi")
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
                    System.out.println("YemekKovası Firma Sayfasına hoşgeldiniz " + firmalar[firma_index].ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Yemek menüsü işlemleri");
                    System.out.println("3. Kullanıcı bilgilerimi değiştir");
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
                            //YEMEK MENÜ İŞLEMLERİ
                              //menü ekle
                              //menü düzenle
                              //menü sil
                            break;
                        case 3:
                              System.out.println(firmalar[firma_index]);
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");//ADINIZ DİREK ATLIYOR - MÜŞTERİDE AYNI
                            String komut_alt = sc.next();
                            if("E".equals(komut_alt)){
                                System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
                                Firma degisen_firma = new Firma();
                                System.out.println("Adınız: ");
                                String yeni_ad = sc.nextLine();
                                degisen_firma.ad = yeni_ad.equals("") ? firmalar[firma_index].ad : yeni_ad;
                                
                                System.out.println("Telefon: ");
                                String yeni_telefon = sc.nextLine();
                                degisen_firma.telefon = yeni_telefon.equals("") ? firmalar[firma_index].telefon : yeni_telefon;
                                
                                System.out.println("Adres: ");
                                String yeni_Adres = sc.nextLine();
                                degisen_firma.adres = yeni_Adres.equals("") ? firmalar[firma_index].adres : yeni_Adres;
                                
                                firmalar[firma_index] = degisen_firma;
                                
                            }
                            break;
                        default:
                            break;
                    }
                } while (komut != -1);
                
            } else {
                int musteri_index = -1;
                do {
                   musteri_index = Kullanici.KullaniciKontrol(musteriler);
                    if (musteri_index == -1) {
                        System.out.println("Böyle bir kullanıcı yok. Lütfen tekrar deneyiniz. Çıkmak için CIK yazarak Enter'layın");
                        
                    }
                } while (musteri_index == -1);
                
                int komut = -1;
                do {
                    
                    ArrayList<Siparis> musteri_siparisler = Musteri.GetSiparisler(siparisler, musteri_index);
                    
                    System.out.println("YemekKovası Müşteri Sayfasına hoşgeldiniz " + musteriler[musteri_index].ad);
                    System.out.println("1. Alınan siparişleri görüntüle");
                    System.out.println("2. Sipariş Ver");
                    System.out.println("3. Kullanıcı bilgilerimi görüntüle veya değiştir");
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
                            System.out.println(musteriler[musteri_index]);
                            System.out.println("Bilgilerinizi değiştirmek için E yazarak devam ediniz");
                            String komut_alt = sc.next();
                            if("E".equals(komut_alt)){
                                System.out.println("Değiştirmek istemediğiniz bilgi için boş bırakarak devam edin");
                                Musteri degisen_musteri = new Musteri();
                                System.out.println("Adınız: ");
                                String yeni_ad = sc.nextLine();
                                degisen_musteri.ad = yeni_ad.equals("") ? musteriler[musteri_index].ad : yeni_ad;
                                
                                System.out.println("Soyadınız: ");
                                 String yeni_soyad = sc.nextLine();
                                degisen_musteri.soyad = yeni_soyad.equals("") ? musteriler[musteri_index].soyad : yeni_soyad;
                                
                                System.out.println("Telefon: ");
                                String yeni_telefon = sc.nextLine();
                                degisen_musteri.telefon = yeni_telefon.equals("") ? musteriler[musteri_index].telefon : yeni_telefon;
                                
                                System.out.println("Adres: ");
                                String yeni_Adres = sc.nextLine();
                                degisen_musteri.adres = yeni_Adres.equals("") ? musteriler[musteri_index].adres : yeni_Adres;
                                
                                musteriler[musteri_index] = degisen_musteri;
                                
                            }
                            break;
                        default:
                            break;
                    }
                } while (don);
            }
        } while (true);
    }
}
