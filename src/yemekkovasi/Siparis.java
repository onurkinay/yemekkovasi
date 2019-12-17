package yemekkovasi;

import java.util.ArrayList;
import java.util.Scanner;

public class Siparis {

    static int id = 0;
    int kimlik;
    int firma_kimlik;
    int musteri_kimlik;
    String[][] siparis; // yemek adı - porsiyon
    int durum;

    Siparis(int firma_kimlik, int musteri_kimlik, String[][] siparis) {
        this.kimlik = id++;
        this.firma_kimlik = firma_kimlik;
        this.musteri_kimlik = musteri_kimlik;
        this.siparis = siparis;
        this.durum = 0;
    }

    @Override
    public String toString() {
        return "Siparis{" + "firma_kimlik=" + firma_kimlik + ", musteri_kimlik=" + musteri_kimlik + ", siparis=" + siparis + ", durum=" + durum + '}';
    }

    public int toplamTutar(Firma firma) {
        int tutar = 0;
        for (int i = 0; i < siparis.length; i++) {
            for (int j = 0; j < firma.menuler.size(); j++) {
                if (siparis[i][0].equals(firma.menuler.get(j).ad)) {
                    tutar += (Integer.parseInt(siparis[i][1]) * firma.menuler.get(j).fiyat);
                }
            }

        }
        return tutar;
    }
    
       public static void Ekle(int musteri_index) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sipariş vermek istediğiniz firmayi seçiniz:");
        for (int i = 0; i < Test.firmalar.size(); i++) {
            System.out.println((i + 1) + ". " + Test.firmalar.get(i).ad);
        }
        int firma = sc.nextInt() - 1;
        int urun = -1;
        ArrayList<String[]> siparis = new ArrayList<>();
        do {
            System.out.println("Menü: ");
            for (int i = 0; i < Test.firmalar.get(i).menuler.size(); i++) {
                System.out.println((i + 1) + " " + Test.firmalar.get(firma).menuler.get(i).ad + " Ücret:" + Test.firmalar.get(firma).menuler.get(i).fiyat);
            }
            System.out.println("Ürün seçin(Devam etmek için -1 yazınız): ");
            urun = sc.nextInt() - 1;
            if (urun == -2) {
                break;
            }
            System.out.println("Porsiyon: ");
            int porsiyon = sc.nextInt();
            siparis.add(new String[]{Test.firmalar.get(firma).menuler.get(urun).ad, Integer.toString(porsiyon)});
        } while (true);

        System.out.println("Siparişiniz: ");

        for (int i = 0; i < siparis.size(); i++) {
            System.out.println("Ürün adı: " + siparis.get(i)[0] + " Porsiyon: " + siparis.get(i)[1]);
        }
        System.out.println("Toplam tutar: " + Siparis.toplamTutar(Test.firmalar.get(firma), siparis));
        System.out.println("Siparişi onaylıyor musunuz?");
        String yeniDurum = sc.next();
        if (yeniDurum.equals("E")) {
            Test.siparisler.add(new Siparis(firma, musteri_index, siparis.toArray(new String[0][0])));
            System.out.println("Siparişiniz alındı");
        } else {
            System.out.println("---Siparişiniz onaylamadınız---");
        }

    }

    public static int toplamTutar(Firma firma, ArrayList<String[]> siparisler) {
        int tutar = 0;
        for (int i = 0; i < siparisler.size(); i++) {
            for (int j = 0; j < firma.menuler.size(); j++) {
                if (siparisler.get(i)[0].equals(firma.menuler.get(j).ad)) {
                    tutar += (Integer.parseInt(siparisler.get(i)[1]) * firma.menuler.get(j).fiyat);
                }
            }

        }
        return tutar;
    }

    public String getDurum() {
        String dur = "";
        switch (this.durum) {
            case 0:
                dur = "Sipariş hazırlanıyor";
                break;
            case 1:
                dur = "Teslim ediliyor";
                break;
            case 2:
                dur = "Teslim edildi";
                break;
        }
        return dur;
    }

    public void setDurum() {

    }

    public static boolean Listele(int musteri_index) {
        boolean listelendi = false;
        for (int i = 0; i < Test.siparisler.size(); i++) {

            if (musteri_index == Test.siparisler.get(i).musteri_kimlik) {
                System.out.println((Test.siparisler.get(i).kimlik) + ". " + Test.firmalar.get(Test.siparisler.get(i).firma_kimlik).ad + " (toplam Tutar:" + Test.siparisler.get(i).toplamTutar(Test.firmalar.get(Test.siparisler.get(i).firma_kimlik)) + ")");// i. müsterinin siparişi (toplam TL)

                listelendi = true;
            }

        }
        return listelendi;
    }

    public static void Listele(int firma_index, boolean firma) {

        for (int i = 0; i < Test.siparisler.size(); i++) {
            if (firma_index == Test.siparisler.get(i).firma_kimlik) {
                System.out.println((Test.siparisler.get(i).kimlik) + ". " + Test.musteriler.get(Test.siparisler.get(i).musteri_kimlik).ad
                        + " (toplam Tutar:" + Test.siparisler.get(i).toplamTutar(Test.firmalar.get(firma_index)) + ")");// i. müsterinin siparişi (toplam TL)

            }
        }
    }

    public static void Detay(int siparis_kod, boolean firma) {

        Siparis siparis = Test.siparisler.stream().filter(p -> p.kimlik == siparis_kod).findFirst().get();
        System.out.println("Siparis detay:"
                + "\nMüşteri adı:" + Test.musteriler.get(siparis.musteri_kimlik).ad
                + "\nAdresi: " + Test.musteriler.get(siparis.musteri_kimlik).adres
                + "\nTelefonu:" + Test.musteriler.get(siparis.musteri_kimlik).telefon);

        PorsiyonListele(siparis);
        System.out.println("Sipariş durumu: " + siparis.getDurum());

    }

    public static void Detay(int siparis_kod) {
        Siparis siparis = Test.siparisler.stream().filter(p -> p.kimlik == siparis_kod).findFirst().get();
        System.out.println("Siparis detay:"
                + "\nFirma adı:" + Test.firmalar.get(siparis.firma_kimlik).ad
                + "\nAdresi: " + Test.firmalar.get(siparis.firma_kimlik).adres
                + "\nTelefonu:" + Test.firmalar.get(siparis.firma_kimlik).telefon);

        PorsiyonListele(siparis);
        System.out.println("Sipariş durumu: " + siparis.getDurum());
    }

    public static void Iade(int siparis_kod) {
        Scanner sc = new Scanner(System.in);

        Siparis siparis = Test.siparisler.stream().filter(p -> p.kimlik == siparis_kod).findFirst().get();
        if (siparis.durum == 0) {
            System.out.println("Siparişi iade etmek istiyor musunuz? (Evet için E - Hayır için H)");
            String iade = sc.next();
            if (iade.equals("E")) {
                Test.siparisler.remove(siparis.kimlik);
                System.out.println("İade basarili");
            }
        } else {
            System.out.println("Bu aşamada sipariş iptal edilemez");
            sc.next();
        }
    }

    public static void PorsiyonListele(Siparis siparis) {
        for (int i = 0; i < siparis.siparis.length; i++) {
            int porsiyon = Integer.parseInt(siparis.siparis[i][1]);
            System.out.println(siparis.siparis[i][0] + " Porsiyon: " + porsiyon);

        }
    }
    
      public static void Durum(int siparis_kod) {
        Scanner sc = new Scanner(System.in);
        String yeniDurum = "";
        
        Siparis siparis = Test.siparisler.stream().filter( p-> p.kimlik == siparis_kod).findFirst().get();
        if (siparis.durum == 0) {
            System.out.println("Sipariş yola çıktı mı? (Evet için E - Hayır için H)");
            yeniDurum = sc.next();
            if (yeniDurum.equals("E")) {
                siparis.durum = 1;
            }
        } else if (siparis.durum == 1) {
            System.out.println("Sipariş teslim edildi mi? (Evet için E - Hayır için H)");
            yeniDurum = sc.next();
            if (yeniDurum.equals("E")) {
                siparis.durum = 2;
            }
        }
    }
   

}
