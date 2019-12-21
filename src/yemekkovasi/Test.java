package yemekkovasi;

import java.util.ArrayList;
import java.util.Scanner;

 
public class Test {

    public static ArrayList<Musteri> musteriler = new ArrayList<>();
    public static ArrayList<Firma> firmalar = new ArrayList<Firma>();
    public static ArrayList<Siparis> siparisler = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        musteriler = new ArrayList<Musteri>() {
            {
                add(new Musteri("Onur Kınay", "0555", "Halıcıoğlu", "onur123", "o1234", 100));
                add(new Musteri("Ahmet Yılmaz", "0554", "Fatih", "ahmet4", "ay144", 200));
                add(new Musteri("Mehmet Yenilmez", "0553", "Küçükçamlıca", "mehmet44", "mehyen456", 300));
                add(new Musteri("Hakan İpek", "0552", "Mecidiyeköy", "hakan61", "urfaligenc", 400));
                add(new Musteri("Ayşe Çalışkan", "0011", "Beylidüzü", "ayse12", "ayse987", 500));
            }
        };

        firmalar = new ArrayList<Firma>() {
            {
                add(new Firma("Urfalı Dayı'nın Yeri", "Fatih", "0566",
                        new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Lahmacun", 5));
                        add(new Yemek("Mercimek Çorbası", 6));
                        add(new Yemek("Urfa DÜrüm", 5));
                    }
                }, "urfali", "qwerty1", 50));
                add(new Firma("Adanalı Amca'nın Yeri", "Halıcıoğlu", "0544", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Lahmacun", 5));
                        add(new Yemek("İşkembe Çorbası", 6));
                        add(new Yemek("Adana Dürüm", 5));
                    }
                }, "adanali", "asdfgh1", 50));
                add(new Firma("Japon Recep'in Mekanı", "Acıbadem", "0558", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Suşi", 15));
                        add(new Yemek("Ramen", 30));
                        add(new Yemek("Noodle", 15));
                    }
                }, "japonya4", "osaka11", 50));
                add(new Firma("PizzaLove", "Beyoğlu", "0548", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Küçük Pizza", 5));
                        add(new Yemek("Orta Pizza", 15));
                        add(new Yemek("Büyük Pizza", 25));
                    }
                }, "pizza6", "italya", 50));
                add(new Firma("Bağcılar Döner", "Bağcılar", "0566", new ArrayList<Yemek>() {
                    {
                        add(new Yemek("Yarım Döner", 5));
                        add(new Yemek("Lavaş Döner", 15));
                        add(new Yemek("Porsion Döner", 20));
                    }
                }, "bagcilar", "demirkapi", 50));
            }
        };

        siparisler = new ArrayList<>();

        siparisler.add(new Siparis(0, 0, new String[][]{{"Lahmacun", "5"}}));
        siparisler.add(new Siparis(1, 0, new String[][]{{"Adana Dürüm", "10"}}));

        //Konsol ekranı için 
        Sistem.Baslat();
        //Kullanıcı girişi: kullanıcı var ise kullanıcının index numarasını döner, yok ise -1 döner
        int ka_index = Kullanici.KullaniciKontrol("kullanici adi", "şifre");

        //Kullanici bilgileri değiştirme
        Kullanici.BilgiDegistirme(ka_index, "Yeni isim", "000", "Halıcıoğlu");

        //Kullanıcı giriş bilgileri değiştirme
        Kullanici.GirisDegistirme(ka_index, "ka", "sifre");

        //Firma menü listeleme
        Menu.Getir(0);//firma index

        //Firma menüye yemek ekleme
        Menu.YemekEkle("", 0, 0);

        //Firma menüden yemek silme
        Menu.YemekSil(0, 0);

        //Firma menüden yemek düzenleme
        Menu.YemekDuzenle("", 0, 0, 0);

        //Sipariş verme
        Siparis.Ekle(0, 0, new ArrayList<String[]>() {//format --> Yemek adı - porsiyon sayısı
            {
                add(new String[]{"Yarım Döner", "5"});
                add(new String[]{"Lavaş Döner", "15"});
                add(new String[]{"Porsion Döner", "20"});
            }
        });

        //Siparis görüntüleme -- siparis bulunamazsa ise false döndürür
        Siparis.Listele(0);

        //Siparis getirme -> siparisler, array index değil özel kimlik numaraları ile getirir.
        Siparis siparis = Siparis.Getir(0);
        //Siparisi görüntüleme
        System.out.println(siparis);

        //Siparis iade etme
        siparis.Iade();// belirli siparisi iade etme

        //Siparisin durumu
        siparis.getDurum();

        //Siparisin durumu güncelleme -> komut her çalıştığında sipariş durumu bir adım sonrası olarak degistirecektir
        siparis.Durum(true);

        //Müsteri hesabına para ekleme
        musteriler.get(0).ParaEkle(0);

        //Firma hesabından para çekme
        firmalar.get(0).ParaCek(0);
    }

}
