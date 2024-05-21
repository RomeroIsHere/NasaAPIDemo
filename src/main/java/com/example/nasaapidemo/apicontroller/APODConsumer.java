package com.example.nasaapidemo.apicontroller;

import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
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
    /*Este es necesario porque API de Nasa solo permite los query en este orden*/
    private static final parameters[] ordered={parameters.date,parameters.start_date,parameters.end_date,parameters.count,parameters.thumbs,parameters.api_key};
    @Override
    public APOD get(APOD info) {
        String request = APODUrl + buildQueryParameters(buildMapFromModel(info));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());

        return parseJSON(result);
    }

    public APOD getByDateString(String date){//yyyy-mm-dd
        Map<parameters,String> queryMap =mapFromAPIkey();
        queryMap.put(parameters.date,date);

        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSON(result);
    }
    public APOD[] getByDateRange(String startDate){
        Map<parameters,String> queryMap =mapFromAPIkey();
        queryMap.put(parameters.start_date,startDate);

        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSONList(result,1);
    }
    public APOD[] getByDateRange(String startDate,String endDate){
        Map<parameters,String> queryMap =mapFromAPIkey();
        queryMap.put(parameters.start_date,startDate);
        queryMap.put(parameters.end_date,endDate);
        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSONList(result,1);
    }

    public APOD[] getRandomByCount(int count){
        Map<parameters,String> queryMap = mapFromAPIkey();
        queryMap.put(parameters.count,count+"");
        String request = APODUrl + buildQueryParameters(queryMap);
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSONList(result,count);
    }

    private APOD[] parseJSONList(HttpResponse<String> result, int count) {

        Gson gson= new Gson();
        APOD[] recollect = new APOD[count];
        return gson.fromJson(result.body(),recollect.getClass());
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
        for (APODConsumer.parameters parameters : ordered) {
            if (!queryValueMap.getOrDefault(parameters, "default").equalsIgnoreCase("default")) {
                if (parametricQuery.codePointBefore(parametricQuery.length()) != '?')
                    parametricQuery.append("&");
                parametricQuery.append(parameters).append("=").append(queryValueMap.get(parameters));
            }
        }

        return parametricQuery.toString();
    }
    public APODConsumer() {
        super();
    }

    public APODConsumer(String key) {
        super(key);
    }
}
