package com.example.nasaapidemo.apicontroller;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractHTTPConnect {
    protected String APIkey="DEMO_KEY";
    protected HttpClient client;
    public AbstractHTTPConnect() {
        System.out.println(APIkey);
        this.client = HttpClient.newHttpClient();
    }
    public AbstractHTTPConnect(String key) {
        this.client = HttpClient.newHttpClient();
        APIkey=key;
        System.out.println(key);
    }

    public void setAPIkey(String APIkey) {
        this.APIkey = APIkey;
    }
    public HttpResponse<String> fetchRequest(HttpRequest request){

        HttpResponse<String> response;

        try {
            System.out.println("Fetching");
            System.out.println(request);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Something went Wrong, and api was Interrupted");
            throw new RuntimeException(e);
        }
        return response;
    }

}
