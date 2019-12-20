package yemekkovasi;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Siparis() {
    }

    @Override
    public String toString() {
        if (Kullanici.Tip(Sistem.ka_index) == 2) {
            return "Siparis detay:"
                    + "\nMüşteri adı:" + Test.musteriler.get(this.musteri_kimlik).ad
                    + "\nAdresi: " + Test.musteriler.get(this.musteri_kimlik).adres
                    + "\nTelefonu:" + Test.musteriler.get(this.musteri_kimlik).telefon
                    + "\n" + PorsiyonListele(this)
                    + "Sipariş durumu: " + this.getDurum();
        } else {
            return "Siparis detay:"
                    + "\nFirma adı:" + Test.firmalar.get(this.firma_kimlik).ad
                    + "\nAdresi: " + Test.firmalar.get(this.firma_kimlik).adres
                    + "\nTelefonu:" + Test.firmalar.get(this.firma_kimlik).telefon
                    + "\n" + PorsiyonListele(this)
                    + "Sipariş durumu: " + this.getDurum();

        }
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
    ////////////

    public static Siparis Getir(int siparis_kimlik) {
        return Test.siparisler.stream().filter(p -> p.kimlik == siparis_kimlik).findFirst().get();
    }

    public static void Ekle(int musteri_index) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sipariş vermek istediğiniz firmayi seçiniz(Çıkmak için -1):");
        for (int i = 0; i < Test.firmalar.size(); i++) {
            System.out.println((i + 1) + ". " + Test.firmalar.get(i).ad);
        }
        int firma = sc.nextInt() - 1;
        if (firma == -2) {
            System.out.println("Sipariş verme işlemi iptal");
            return;
        }
        if (Test.firmalar.size() < firma + 1 || firma < 0) {
            System.out.println("Geçersiz firma kodu");
            Sistem.Bekle();
            return;
        }
        int urun = -1;
        ArrayList<String[]> siparis = new ArrayList<>();
        do {
            System.out.println("Menü: ");
            ArrayList<Yemek> firma_menusu = Test.firmalar.get(firma).menuler;
            for (int i = 0; i < firma_menusu.size(); i++) {
                System.out.println((i + 1) + " " + firma_menusu.get(i).ad + " Ücret:" + firma_menusu.get(i).fiyat);
            }
            System.out.println("Ürün seçin(Devam etmek için -1 yazınız): ");
            urun = sc.nextInt() - 1;
            if (urun >= firma_menusu.size() || urun < -2) {
                System.out.println("Geçersiz giriş");
                continue;
            }
            if (urun == -2) {
                break;
            }
            System.out.println("Porsiyon: ");
            int porsiyon = sc.nextInt();
            if (porsiyon < 1) {
                System.out.println("Porsiyon 0'dan büyük olmalıdır.");
                continue;
            }
            siparis.add(new String[]{Test.firmalar.get(firma).menuler.get(urun).ad, Integer.toString(porsiyon)});
        } while (true);
        if (siparis.size() > 0) {
            System.out.println("Siparişiniz: ");

            for (int i = 0; i < siparis.size(); i++) {
                System.out.println("Ürün adı: " + siparis.get(i)[0] + " Porsiyon: " + siparis.get(i)[1]);
            }
            System.out.println("Toplam tutar: " + Siparis.toplamTutar(Test.firmalar.get(firma), siparis));
            System.out.println("Siparişi onaylıyor musunuz? (Evet için E)");
            String yeniDurum = sc.next();
            if (yeniDurum.equals("E")) {
                if (Ekle(musteri_index, firma, siparis)) {
                    System.out.println("Siparişiniz alındı");
                } else {
                    System.out.println("Siparişiniz onaylanmadı çünkü hesabınızda yeterli para bulunmamaktadır.");

                }
            } else {
                System.out.println("---Siparişiniz onaylamadınız---");
            }
        } else {
            System.out.println("Herhangi bir siparişte bulunmadınız");
        }

    }

    public static boolean Ekle(int musteri_index, int firma, ArrayList<String[]> siparis) {
        if (Siparis.toplamTutar(Test.firmalar.get(firma), siparis) < Test.musteriler.get(musteri_index).hesap) {
            Test.siparisler.add(new Siparis(firma, musteri_index, siparis.toArray(new String[0][0])));
            Test.musteriler.get(musteri_index).hesap -= Siparis.toplamTutar(Test.firmalar.get(firma), siparis);
            Test.firmalar.get(firma).hesap += Siparis.toplamTutar(Test.firmalar.get(firma), siparis);
            return true;
        } else {
            return false;
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

    public static boolean Listele(int firma_index, boolean firma) {

        boolean listelendi = false;
        for (int i = 0; i < Test.siparisler.size(); i++) {

            if (firma_index == Test.siparisler.get(i).firma_kimlik) {
                System.out.println((Test.siparisler.get(i).kimlik) + ". " + Test.musteriler.get(Test.siparisler.get(i).musteri_kimlik).ad + " (toplam Tutar:" + Test.siparisler.get(i).toplamTutar(Test.firmalar.get(Test.siparisler.get(i).firma_kimlik)) + ")");// i. müsterinin siparişi (toplam TL)

                listelendi = true;
            }

        }
        return listelendi;
    }

    public static void Iade(int siparis_kod) {
        Scanner sc = new Scanner(System.in);

        Siparis siparis = Getir(siparis_kod);
        if (siparis.durum == 0) {
            System.out.println("Siparişi iade etmek istiyor musunuz? (Evet için E)");
            String iade = sc.next();
            if (iade.equals("E")) {
                if (Iade(siparis)) {
                    System.out.println("İade basarılı");
                } else {
                    System.out.println("İade başarısız");
                }

            }
        } else {
            System.out.println("Bu aşamada sipariş iptal edilemez");

        }
    }

    public static boolean Iade(Siparis siparis) {
        if (siparis.durum == 0) {
            Test.musteriler.get(siparis.musteri_kimlik).hesap += Siparis.toplamTutar(Test.firmalar.get(siparis.firma_kimlik), new ArrayList<>(Arrays.asList(siparis.siparis)));
            Test.firmalar.get(siparis.firma_kimlik).hesap -= Siparis.toplamTutar(Test.firmalar.get(siparis.firma_kimlik), new ArrayList<>(Arrays.asList(siparis.siparis)));
            Test.siparisler.remove(siparis.kimlik);
            return true;
        } else {
            return false;
        }

    }

    public static String PorsiyonListele(Siparis siparis) {
        String porsiyonlar = "";
        for (int i = 0; i < siparis.siparis.length; i++) {
            int porsiyon = Integer.parseInt(siparis.siparis[i][1]);
            porsiyonlar += siparis.siparis[i][0] + " Porsiyon: " + porsiyon + "\n";

        }
        return porsiyonlar;
    }

    public static void Durum(int siparis_kod) {
        Scanner sc = new Scanner(System.in);
        String yeniDurum = "";

        Siparis siparis = Getir(siparis_kod);
        if (siparis.durum == 0) {
            System.out.println("Sipariş yola çıktı mı? (Evet için E)");
            yeniDurum = sc.next();
            if (yeniDurum.equals("E")) {
                siparis.durum = 1;
            }
        } else if (siparis.durum == 1) {
            System.out.println("Sipariş teslim edildi mi? (Evet için E)");
            yeniDurum = sc.next();
            if (yeniDurum.equals("E")) {
                siparis.durum = 2;
            }
        }
    }

}
