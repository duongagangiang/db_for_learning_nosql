package com.example.pcc.appringtone.Model;

/**
 * Created by pcc on 02/09/2017.
 */

public class Mp3 {

    private String tieude;
    private String nguon;
    private String soluotdownload;
    private String link;
    //constructor
    public Mp3(String tieude, String nguon, String soluotdownload, String link) {
        this.tieude = tieude;
        this.nguon = nguon;
        this.soluotdownload = soluotdownload;
        this.link = link;
    }

   //method get and set
    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getSoluotdownload() {
        return soluotdownload;
    }

    public void setSoluotdownload(String soluotdownload) {
        this.soluotdownload = soluotdownload;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



}
