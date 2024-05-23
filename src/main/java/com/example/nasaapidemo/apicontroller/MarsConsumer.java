package com.example.nasaapidemo.apicontroller;

import com.example.nasaapidemo.Models.MIVL.Item;
import com.example.nasaapidemo.Models.MMars.LatestPhotos;
import com.example.nasaapidemo.Models.MMars.ManifestWrapper;
import com.example.nasaapidemo.Models.MMars.Photos;
import com.example.nasaapidemo.Models.MMars.Rover;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class MarsConsumer extends AbstractHTTPConnect implements APIConsumer<Photos>{
    public MarsConsumer() {
    }
    public MarsConsumer(String key) {
        super(key);
    }



    public enum rovers{
        curiosity,
        opportunity,
        spirit
    }
    Map<parameters,String> parametersStringMap;
    public enum parameters{
        sol,
        earth_date,
        camera,
        page,
        api_key
    }
    public enum endpoint{
        photos,
        latest_photos,
        manifest
    }

    private static final String marsPhotoRoot="https://api.nasa.gov/mars-photos/api/v1/rovers/%s/photos%s";
    private static final String marsManifestRoot="https://api.nasa.gov/mars-photos/api/v1/manifests/%s%s";
    private static final String marsLatestRoot="https://api.nasa.gov/mars-photos/api/v1/rovers/%s/latest_photos?api_key=%s";

    public Photos[] getLatest(rovers rover){
        String request =String.format(marsLatestRoot,rover,buildQueryParameters(defaultMap()));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSONPhotosArray(result);
    }
    public Photos[] getLatest(String rover){
        String request =String.format(marsLatestRoot,rover.toLowerCase(),buildQueryParameters(defaultMap()));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSONPhotosArray(result);
    }
    public void addParameter(parameters parametro, String value){
        parametersStringMap.put(parametro,value);
    }
    public void resetParameters(){
        parametersStringMap=defaultMap();
    }
    public Photos[] executePreparedParameters(rovers rover){
        String request =String.format(marsPhotoRoot, rover,buildQueryParameters(parametersStringMap));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(request))
                .build());
        return parseJSONPhotosArray(result);
    }
    public Rover getManifestRover(MarsConsumer.rovers rovers){
        String requestURL=String.format(marsManifestRoot,rovers,buildQueryParameters(defaultMap()));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(requestURL))
                .build());
        return parseJSONManifest(result);
    }

    public Photos[] getByParameter(rovers rovers,parameters parameters,String input){
        Map<parameters,String> mapa=defaultMap();
        mapa.put(parameters,input);
        String requestURL=String.format(marsPhotoRoot,rovers,buildQueryParameters(mapa));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(requestURL))
                .build());
        return parseJSONPhotosArray(result);
    }


    private String buildQueryParameters(Map<parameters, String> queryValueMap) {
        StringBuilder parametricQuery=new StringBuilder("?");
        for (parameters parameters:queryValueMap.keySet())
            if (!queryValueMap.getOrDefault(parameters, "").equalsIgnoreCase("")) {
                if (parametricQuery.codePointBefore(parametricQuery.length()) != '?')
                    parametricQuery.append("&");
                parametricQuery.append(parameters).append("=").append(queryValueMap.get(parameters));
            }

        return parametricQuery.toString();
    }

    public HttpResponse<String> getByEnumerators(endpoint end,rovers rover){
        String url=switch (end){
            case endpoint.photos:
                yield marsPhotoRoot;
            case endpoint.latest_photos:
                yield marsLatestRoot;
            case endpoint.manifest:
                yield marsManifestRoot;
        };
        String requestURL=String.format(url,rover,buildQueryParameters(defaultMap()));
        return fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(requestURL))
                .build());
    }

    public Map<parameters,String> defaultMap(){
        Map<parameters,String> parametersMap=new HashMap<>();
        parametersMap.put(parameters.api_key,APIkey);
        return parametersMap;
    }

    @Override
    public Photos get(Photos info) {
        Map<parameters,String> heka=defaultMap();
        heka.put(parameters.sol,info.getSol()+"");
        heka.put(parameters.camera,info.getIdCamera().getNombre());
        String requestURL=String.format(marsManifestRoot,info.getIdRover().getName(),buildQueryParameters(heka));
        HttpResponse<String> result = fetchRequest(HttpRequest.newBuilder()
                .uri(URI.create(requestURL))
                .build());
        return parseJSONPhotosArray(result)[0];
    }

    @Override
    public Photos parseJSON(HttpResponse<String> json, Class<Photos> photosClass) {
        return new Gson().fromJson(json.body(), Photos[].class)[0];
    }

    public Photos[] parseJSONPhotosArray(HttpResponse<String> json) {
        return new Gson().fromJson(json.body(), LatestPhotos.class).latest_photos;
    }
    public Rover parseJSONManifest(HttpResponse<String> json){

        Rover nuRover=new Gson().fromJson(json.body(), ManifestWrapper.class).getPhoto_manifest();
        try{
            nuRover.updateByJson();
        }catch(Exception e){
            System.out.println("Could not update");
            System.out.println(e.getCause());
        }
        return nuRover;
    }
}
