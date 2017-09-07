package com.example.pcc.appringtone.Model;

/**
 * Created by pcc on 04/09/2017.
 */

public class ModelCategory {
    String name;
    String link;

    public ModelCategory(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
