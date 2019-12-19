package yemekkovasi;

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

  

   
}
