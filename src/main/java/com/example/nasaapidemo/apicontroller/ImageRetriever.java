package com.example.nasaapidemo.apicontroller;

import com.itextpdf.io.image.ImageData;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import com.itextpdf.io.image.ImageDataFactory;

public class ImageRetriever extends AbstractHTTPConnect{
    public Image getFromInputStream(InputStream stream){
        return new Image(stream);
    }

    public Image getFromURL(String imageUrl) throws IOException, URISyntaxException {
        URL url = new URI(imageUrl).toURL();
        InputStream is = url.openStream();
        return getFromInputStream(is);
    }
    public ImageData getDataFromURL(String imageUrl) throws MalformedURLException {
        return ImageDataFactory.create(imageUrl);
    }

}
