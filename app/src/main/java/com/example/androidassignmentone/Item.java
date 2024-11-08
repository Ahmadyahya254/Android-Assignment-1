package com.example.androidassignmentone;

public class Item {
    String titel;
    String value;

    public Item(String titel,String value){
        this.titel=titel;
        this.value=value;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
