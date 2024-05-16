package com.example.nasaapidemo.apicontroller;

import com.example.nasaapidemo.Models.APOD;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class APODConsumer extends AbstractHTTPConnect implements APIConsumer<APOD>{
    public static final String APODUrl = "https://api.nasa.gov/planetary/apod";
    private enum parameters{
        date,
        start_date,
        end_date,
        count,
        thumbs,
        api_key
    }
    @Override
    public APOD get(APOD info) {
        String request = APODUrl + buildQueryParameters(buildMapFromModel(info));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());

        return parseJSON(result);
    }

    public APOD getByDateString(String date){
        Map<parameters,String> queryMap =mapFromAPIkey();
        queryMap.put(parameters.date,date);

        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSON(result);
    }
    public APOD getByDateRange(String startDate){
        Map<parameters,String> queryMap =mapFromAPIkey();
        queryMap.put(parameters.start_date,startDate);

        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSON(result);
    }
    public APOD getByDateRange(String startDate,String endDate){
        Map<parameters,String> queryMap =mapFromAPIkey();
        queryMap.put(parameters.start_date,startDate);
        queryMap.put(parameters.end_date,endDate);
        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSON(result);
    }
    public Collection<APOD> getRandomByCount(int count){
        Map<parameters,String> queryMap =mapFromAPIkey();
        queryMap.put(parameters.count,count+"");
        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSONList(result);
    }

    private Collection<APOD> parseJSONList(HttpResponse<String> result) {
        TypeToken<Collection<APOD>> collectionType = new TypeToken<>(){};
        Gson gson= new Gson();
        return gson.fromJson(result.body(), collectionType);
    }

    public APOD parseJSON(HttpResponse<String> json) {
        return APIConsumer.super.parseJSON(json, APOD.class);
    }

    private Map<parameters,String> buildMapFromModel(APOD tao){
        Map<parameters,String> querySet=mapFromAPIkey();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        querySet.put(parameters.date, formatter.format(tao.getDate()));
        return querySet;
    }
    private Map<parameters,String> mapFromAPIkey(){
        Map<parameters,String> querySet=new HashMap<>();
        querySet.put(parameters.api_key,APIkey);
        return querySet;
    }


    private String buildQueryParameters(Map<parameters,String> queryValueMap){
        StringBuilder parametricQuery= new StringBuilder("?");
        for (parameters s:queryValueMap.keySet()){
            if (parametricQuery.codePointBefore(parametricQuery.length())!='?')
                parametricQuery.append("&");
            parametricQuery.append(s).append("=").append(queryValueMap.get(s));
        }

        return parametricQuery.toString();
    }
    public APODConsumer() {
        super();
    }
}
