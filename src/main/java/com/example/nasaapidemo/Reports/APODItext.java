package com.example.nasaapidemo.Reports;


import com.example.nasaapidemo.Models.MAPOD.APOD;
import com.example.nasaapidemo.apicontroller.ImageRetriever;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;

import javax.print.Doc;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Phaser;


public class APODItext {

    private ImageRetriever a_image;



    public APODItext(){
        a_image=new ImageRetriever();
    }



    public void createPdf(String dest,List<APOD> p_Apod) throws IOException, URISyntaxException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setStrokeColor(ColorConstants.BLUE);


        m_setStylDocument(document);
        for (APOD v_apod : p_Apod) {
            document.add(m_setTitle(v_apod.getTitle()));
         //   document.add(new Paragraph().add(v).setTextAlignment(TextAlignment.CENTER));
            document.add(m_setTicket("Date: ").add(m_setResults(v_apod.getDate())));

            if(v_apod.getCveMedia().getName().equalsIgnoreCase("Image"))
            document.add(m_setImage(v_apod.getUrl()));
            else
               document.add(m_setImage("https://img.freepik.com/vector-premium/no-hay-foto-disponible-icono-vector-simbolo-imagen-predeterminado-imagen-proximamente-sitio-web-o-aplicacion-movil_87543-10615.jpg?w=1060"));


            document.add(new Paragraph(""));
            document.add(m_setTicket("URL: ").add(m_setResults(v_apod.getUrl()).setFontColor(ColorConstants.BLUE)));
            document.add(new Paragraph(""));
            document.add(m_setTicket("getExplanation:\n"));
            document.add(new Paragraph(""));
            document.add(m_setResults(v_apod.getExplanation() + "\n"));
            m_createNewPage(pdf, document);
        }



        pdf.removePage(pdf.getLastPage());


        openFile(dest);
        //Close document
        document.close();
    }


    private Paragraph m_setImage(String p_imagUrl) throws IOException {
        Paragraph v_respuesta;
        Image v_image;
        v_respuesta=new Paragraph();

        v_image=new Image(a_image.getDataFromURL(p_imagUrl));
        v_image.setHeight(150);
        v_respuesta.setTextAlignment(TextAlignment.CENTER);

        v_respuesta.add(v_image);

        return v_respuesta;
    }

     private void m_createNewPage(PdfDocument p_pages,Document p_document){
        int v_contador;
        v_contador=p_pages.getNumberOfPages();

        while(v_contador== p_pages.getNumberOfPages())
            p_document.add(new Paragraph("\n"));

     }

    private Paragraph m_setTitle(String p_text) throws IOException {
        Paragraph v_respuesta;
            Text v_text=new Text(p_text+"\n");
            v_text.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
            v_text.setBold();
            v_text.setUnderline();
            v_text.setFontColor(ColorConstants.BLACK);
            v_text.setFontSize(25);

        v_respuesta=new Paragraph(v_text);

        v_respuesta.setTextAlignment(TextAlignment.CENTER);

          return v_respuesta;
        }

        private Paragraph m_setTicket(String p_text) throws IOException {
            Paragraph v_respuesta;
            Text v_text=new Text(p_text);
            v_text.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
            v_text.setFontColor(ColorConstants.BLACK);
            v_text.setFontSize(12);

            v_respuesta=new Paragraph(v_text);

            v_respuesta.setTextAlignment(TextAlignment.CENTER);

            return v_respuesta;
        }


    private Paragraph m_setResults(String p_text) throws IOException {
        Paragraph v_respuesta;
        Text v_text=new Text(p_text);
        v_text.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN));
        v_text.setFontSize(12);

        v_respuesta=new Paragraph(v_text);

        v_respuesta.setTextAlignment(TextAlignment.CENTER);

        return v_respuesta;
    }



    private void openFile(String filename)
    {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(filename);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }


    private void m_setStylDocument(Document p_document){
        p_document.setMargins(20, 20, 20, 20);
        p_document.setBorder(Border.NO_BORDER);

    }










}
