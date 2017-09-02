package com.example.pcc.appringtone.Model;

/**
 * Created by pcc on 03/09/2017.
 */

public class Image {
    private String tieude;
    private String link;
    //constructor
    public Image(String tieude, String link) {
        this.tieude = tieude;
        this.link = link;
    }

    //method get and set
    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
