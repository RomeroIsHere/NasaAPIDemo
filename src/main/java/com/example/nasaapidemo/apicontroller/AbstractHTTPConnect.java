package com.example.nasaapidemo.apicontroller;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractHTTPConnect {
    protected String APIkey;
    protected HttpClient client;
    public AbstractHTTPConnect() {
        this.client = HttpClient.newHttpClient();
    }
    public AbstractHTTPConnect(String key) {
        this.client = HttpClient.newHttpClient();
        APIkey=key;
    }

    public void setAPIkey(String APIkey) {
        this.APIkey = APIkey;
    }
    public HttpResponse<String> fetchRequest(HttpRequest request){

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Something went Wrong, and api was Interrupted");
            throw new RuntimeException(e);
        }

        System.out.println(response.body());
        return response;
    }

}
