package com.example.feliscatus.ui.favourites;

import com.example.feliscatus.Cat;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FavouriteList {

    @SerializedName("list_name")
    private String listName;


    // Like with Article, I have put this in its own class rather than an inner class.
    // This is because a Book is conceptually not exclusively belonging to a BestsellerList. I could
    // have a book that's not part of a bestseller list, so it's not accurate to define it as an
    // inner class of BestsellerList.
    private ArrayList<Cat> cats;


    public String getListName() {
        return listName;
    }


    public ArrayList<Cat> getCats() {
        return cats;
    }
}
