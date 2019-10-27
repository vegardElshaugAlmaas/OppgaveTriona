package com.example.mastermindtriona;

public class ListViewItem {
    private String gues;
    private int image;

    public ListViewItem(String s, int i){
        this.gues = s;
        this.image = i;

    }

    public String getGues(){
        return this.gues;
    }

    public int getImage(){
        return image;
    }
}
