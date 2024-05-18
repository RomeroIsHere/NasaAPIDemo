package com.example.nasaapidemo.apicontroller;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;

public class ImageRetriever extends AbstractHTTPConnect{
    public Image getFromInputStream(InputStream stream){
        return new Image(stream);
    }

    public Image getFromURL(String imageUrl) throws IOException, URISyntaxException {
        URL url = new URI(imageUrl).toURL();
        InputStream is = url.openStream();
        return getFromInputStream(is);
    }

}
