package com.example.nasaapidemo.Models;

import com.example.nasaapidemo.Models.MIVL.Item;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("data")
    public Item[] item;
}
