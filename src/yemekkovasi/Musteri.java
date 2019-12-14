package yemekkovasi;


import java.util.ArrayList;
import java.util.Scanner;

public class Musteri extends Kullanici{
    //  int kimlik;

    String ad;
    String soyad;
    String adres;

    Musteri(String ad, String soyad, String telefon, String adres, String kadi, String sifre) {
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.adres = adres;
        this.kadi = kadi;
        this.sifre = sifre;
    }

    public Musteri() {
    }
    
    

    public void BilgiDegistir(Musteri degisim) {
        this.ad = degisim.ad;
        this.soyad = degisim.soyad;
        this.telefon = degisim.telefon;
        this.adres = degisim.adres;
        this.kadi = degisim.kadi;
        this.sifre = degisim.sifre;
    }

    @Override
    public String toString() {
        return  "Adınız: " + ad + "\nSoyadınız: " + soyad + "\nTelefon: " + telefon + "\nAdres: " + adres;
    }
    
    

    ////STATIC FUNCS.
    public static void addSiparis(ArrayList<Firma> firmalar, int musteri_index, ArrayList<Siparis> siparisler) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sipariş vermek istediğiniz firmayi seçiniz:");
        for (int i = 0; i < firmalar.size(); i++) {
            System.out.println((i + 1) + ". " + firmalar.get(i).ad);
        }
        int firma = sc.nextInt() - 1;
        int urun = -1;
        ArrayList<String[]> siparis = new ArrayList<>();
        do {
            System.out.println("Menü: ");
            for (int i = 0; i < firmalar.get(i).menuler.size(); i++) {
                System.out.println((i + 1) + " " + firmalar.get(firma).menuler.get(i).ad + " Ücret:" + firmalar.get(firma).menuler.get(i).fiyat);
            }
            System.out.println("Ürün seçin(Devam etmek için -1 yazınız): ");
            urun = sc.nextInt() - 1;
            if (urun == -2) {
                break;
            }
            System.out.println("Porsiyon: ");
            int porsiyon = sc.nextInt();
            siparis.add(new String[]{firmalar.get(firma).menuler.get(urun).ad, Integer.toString(porsiyon)});
        } while (true);

        System.out.println("Siparişiniz: ");

        for (int i = 0; i < siparis.size(); i++) {
            System.out.println("Ürün adı: " + siparis.get(i)[0] + " Porsiyon: " + siparis.get(i)[1]);
        }
        System.out.println("Toplam tutar: " + Siparis.toplamTutar(firmalar.get(firma), siparis));
        System.out.println("Siparişi onaylıyor musunuz?");
        String yeniDurum = sc.next();
        if (yeniDurum.equals("E")) {
            siparisler.add(new Siparis(firma, musteri_index, siparis.toArray(new String[0][0])));
            System.out.println("Siparişiniz alındı");
        } else {
            System.out.println("Siparişiniz onaylamadınız");
        }

    }

    public static void SiparisDetay(ArrayList<Firma> firmalar, int siparis_kod, ArrayList<Siparis> musteri_siparisler) {

        System.out.println("Siparis detay:"
                + "\nFirma adı:" + firmalar.get(musteri_siparisler.get(siparis_kod).firma_kimlik).ad
                + "\nAdresi: " + firmalar.get(musteri_siparisler.get(siparis_kod).firma_kimlik).adres
                + "\nTelefonu:" + firmalar.get(musteri_siparisler.get(siparis_kod).firma_kimlik).telefon);

        for (int i = 0; i < musteri_siparisler.get(siparis_kod).siparis.length; i++) {
            int porsiyon = Integer.parseInt(musteri_siparisler.get(siparis_kod).siparis[i][1]);
            System.out.println(musteri_siparisler.get(siparis_kod).siparis[i][0] + " Porsiyon: " + porsiyon);
        }

        System.out.println("Sipariş durumu: " + musteri_siparisler.get(siparis_kod).getDurum());
    }

    public static void Iade(ArrayList<Firma> firmalar, int siparis_kod, ArrayList<Siparis> siparisler, ArrayList<Siparis> musteri_siparisler) {
        Scanner sc = new Scanner(System.in);

        if (musteri_siparisler.get(siparis_kod).durum == 0) {
            System.out.println("Siparişi iade etmek istiyor musunuz? (Evet için E - Hayır için H)");
            String iade = sc.next();
            if (iade.equals("E")) {
                siparisler.remove(siparis_kod);
                System.out.println("İade basarili");
            }
        } else {
            System.out.println("Bu aşamada sipariş iptal edilemez");
        }
    }

    public static void SiparisListele(ArrayList<Firma> firmalar, ArrayList<Siparis> musteri_siparisler) {
        for (int i = 0; i < musteri_siparisler.size(); i++) {
            System.out.println((i + 1) + ". " + firmalar.get(musteri_siparisler.get(i).firma_kimlik).ad + " (toplam Tutar:" + musteri_siparisler.get(i).toplamTutar(firmalar.get(musteri_siparisler.get(i).firma_kimlik)) + ")");// i. müsterinin siparişi (toplam TL)
        }
    }

    public static ArrayList<Siparis> GetSiparisler(ArrayList<Siparis> siparisler, int musteri_index) {
        ArrayList<Siparis> musteri_siparisler = new ArrayList<>();
        for (int i = 0; i < siparisler.size(); i++) {
            if (siparisler.get(i).musteri_kimlik == musteri_index) {
                musteri_siparisler.add(siparisler.get(i));
            }
        }
        return musteri_siparisler;
    }
    //////STATIC FUNCS.
}
