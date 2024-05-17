package com.example.nasaapidemo.apicontroller;

import  com.google.gson.Gson;

import java.net.http.HttpResponse;

public interface APIConsumer<T> {
    T get(T info);
    default T parseJSON(HttpResponse<String> json, Class<T> tClass){
        Gson song=new Gson();
        return song.fromJson(json.body(),tClass);
    }
}
