package com.example.nasaapidemo.apicontroller;

import com.example.nasaapidemo.Models.MMars.Photos;

import java.net.http.HttpResponse;

public class MarsConsumer extends AbstractHTTPConnect implements APIConsumer<Photos>{
    @Override
    public Photos get(Photos info) {
        return null;
    }

    @Override
    public Photos parseJSON(HttpResponse<String> json, Class<Photos> photosClass) {
        return APIConsumer.super.parseJSON(json, photosClass);
    }
}
