package yemekkovasi;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author onur
 */
public class Yemek {
    
    String ad;
    int fiyat;
    int firma_index;

    public Yemek(String ad, int fiyat, int firma_index) {
        
        this.ad = ad;
        this.fiyat = fiyat;
        this.firma_index = firma_index;
    }

    public Yemek(String ad, int fiyat) {
        this.ad = ad;
        this.fiyat = fiyat;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getFirma_index() {
        return firma_index;
    }

    public void setFirma_index(int firma_index) {
        this.firma_index = firma_index;
    }

   
}
