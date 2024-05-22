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
    private Map<parameters,String> parametersStringMap =defaultMap();
    public enum parameters{
        q,
        center,
        description,
        keywords,
        nasa_id,
        page_size,
        title,
        year_start,
        year_end,
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
    public void addParameter(NIVLConsumer.parameters parametro, String value){
        parametersStringMap.put(parametro,value);
    }
    public void resetParameters(){
        parametersStringMap=defaultMap();
    }
    public Item[] executePreparedParameters(){
        String request = NIVLRoot + buildQueryParameters(parametersStringMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return datumsIntoItems(parseJSONCollectionsArrayOfDatums(result));
    }

    public Item[] searchByDesc(String desc){
        Map<parameters,String> stringMap=defaultMap();
        stringMap.put(parameters.q,desc);
        String request = NIVLRoot + buildQueryParameters(stringMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return datumsIntoItems(parseJSONCollectionsArrayOfDatums(result));
    }
    public Item[] searchByKeywords(String... keywords){
        Map<parameters,String> stringMap=defaultMap();
        StringBuilder keys= new StringBuilder();
        if (keywords.length!=1) {
            for (String s : keywords)
                keys.append(s).append(",");
            keys.deleteCharAt(keys.length());
        }else {
            keys = new StringBuilder(keywords[0]);
        }
        stringMap.put(parameters.keywords,keys.toString());
        String request = NIVLRoot + buildQueryParameters(stringMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return datumsIntoItems(parseJSONCollectionsArrayOfDatums(result));
    }
    public Item[] searchByPageSize(int count){
        Map<parameters,String> stringMap=defaultMap();
        stringMap.put(parameters.description,count+"");
        String request = NIVLRoot + buildQueryParameters(stringMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return datumsIntoItems(parseJSONCollectionsArrayOfDatums(result));
    }
    public Item[] searchbyYearRange(String yearStart,String yearEnd){
        Map<parameters,String> stringMap=defaultMap();
        stringMap.put(parameters.year_end,yearEnd);
        stringMap.put(parameters.year_start,yearStart);
        String request = NIVLRoot + buildQueryParameters(stringMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return datumsIntoItems(parseJSONCollectionsArrayOfDatums(result));
    }
    public Item searchByNasaID(String nasaID){
        Map<parameters,String> stringMap=defaultMap();
        stringMap.put(parameters.nasa_id,nasaID);
        String request = NIVLRoot + buildQueryParameters(stringMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSON(result, Item.class);
    }
    public Item searchBytitle(String title){
        Map<parameters,String> stringMap=defaultMap();
        stringMap.put(parameters.title,title);
        String request = NIVLRoot + buildQueryParameters(stringMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSON(result, Item.class);
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
    public Data[] parseJSONCollectionsArrayOfDatums(HttpResponse<String> json) {
        Gson song=new Gson();
        return song.fromJson(json.body(), Collection.class).getItems();
    }
    public Item[] datumsIntoItems(Data[] dat){
        Item[] items=new Item[dat.length];
        for (int i = 0; i < dat.length; i++) {
            items[i]=dat[i].item[0];
            items[i].setHref(dat[i].linkWrapper[0].href);
        }
        return items;
    }
}
