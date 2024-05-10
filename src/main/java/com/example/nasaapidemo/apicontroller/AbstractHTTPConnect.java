package com.example.nasaapidemo.apicontroller;
import com.example.nasaapidemo.Models.Model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractHTTPConnect {
    public abstract Model getRequest(Object info);
    //Not Deprecated, just Non-Functional
    @Deprecated
    public void test(){
        String clave="";
        String busqueda="";
        String direccion = "https://www.omdbapi.com/?t="+busqueda+"&apikey="+clave;

        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Something went Wrong, and api was Interrupted");
            throw new RuntimeException(e);

        }

        System.out.println(response.body());
    }


}
