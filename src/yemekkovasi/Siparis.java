package yemekkovasi;


import java.util.ArrayList;

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

}
