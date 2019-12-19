package yemekkovasi;


public class Musteri extends Kullanici {
    Musteri(String ad, String telefon, String adres, String kadi, String sifre, int hesap) {
        this.ad = ad;
        this.telefon = telefon;
        this.adres = adres;
        this.kadi = kadi;
        this.sifre = sifre;
        this.hesap = hesap;
        this.k_tip = 1;
    }

    public Musteri() {
    }

    public void BilgiDegistir(Musteri degisim) {
        this.ad = degisim.ad;
        this.telefon = degisim.telefon;
        this.adres = degisim.adres;
        this.kadi = degisim.kadi;
        this.sifre = degisim.sifre;
    }

    @Override
    public String toString() {
        return "Ad Soyad: " + ad + "\nTelefon: " + telefon + "\nAdres: " + adres+"\nHesap bakiyesi:"+ hesap;
    }
    
    public void ParaEkle(int para){
        this.hesap += para;
    }

}
