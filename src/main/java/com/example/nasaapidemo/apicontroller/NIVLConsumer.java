package com.example.nasaapidemo.apicontroller;

import com.example.nasaapidemo.Models.MIVL.Collection;
import com.example.nasaapidemo.Models.MIVL.Data;
import com.example.nasaapidemo.Models.MIVL.Item;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class NIVLConsumer extends AbstractHTTPConnect implements APIConsumer<Item> {
    public static final String NIVLRoot="https://images-api.nasa.gov/search";
    private enum parameters{
        q,
        center,
        description,
        keywords,
        nasa_id,
        title,
        media_type
    }
    @Override
    public Item get(Item info) {
        String request = NIVLRoot + buildQueryParameters(buildMapFromModel(info));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());

        return parseJSON(result,Item.class);
    }
    private Map<parameters,String> defaultMap() {
        Map<parameters, String> querySet = new HashMap<>();
        querySet.put(parameters.media_type, "image");
        return querySet;
    }
    private Map<parameters,String> buildMapFromModel(Item info){
        Map<parameters, String> querySet = defaultMap();
        querySet.put(parameters.nasa_id,info.getIdNasa()+"");
        return querySet;
    }
    private String buildQueryParameters(Map<parameters,String> queryValueMap){
        StringBuilder parametricQuery=new StringBuilder("?");
        for (parameters parameters:queryValueMap.keySet())
            if (!queryValueMap.getOrDefault(parameters, "").equalsIgnoreCase("")) {
                if (parametricQuery.codePointBefore(parametricQuery.length()) != '?')
                    parametricQuery.append("&");
                parametricQuery.append(parameters).append("=").append(queryValueMap.get(parameters));
            }

        return parametricQuery.toString();
    }

    @Override
    /*
    * @Javadoc
    * returns only the first element of the query
    *
    * */
    public Item parseJSON(HttpResponse<String> json, Class<Item> aClass) {
        Gson song=new Gson();
        return song.fromJson(json.body(), Collection.class).getItems()[0].item[0];
    }
    public Data[] parseJSONCollectionsArrayOfDatums(HttpResponse<String> json, Class<Data[]> aClass) {
        Gson song=new Gson();
        return song.fromJson(json.body(), Collection.class).getItems();
    }
    public Item[] datumsIntoItems(Data[] dat){
        Item[] items=new Item[dat.length];
        for (int i = 0; i < dat.length; i++) {
            items[i]=dat[i].item[0];
        }
        return items;
    }
}
