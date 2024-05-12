package com.example.nasaapidemo.Reports;

import com.example.nasaapidemo.Models.APOD;
import com.example.nasaapidemo.Models.Item;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.example.nasaapidemo.Models.Item;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageItext {

    public void createPdf(String dest, List<Item> p_Apod) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);


        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{0.1f}))
                .useAllAvailableWidth();


        process(table, null, bold, true);

        for(Item v_registro: p_Apod)
            process(table, v_registro, font, false);

        document.add(new Paragraph("NASA Image And video Service").setTextAlignment(TextAlignment.CENTER).setFont(bold));
        document.add(table);

        openFile(dest);
        //Close document
        document.close();
    }

    public void process(Table table, Item p_image, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Href").setFont(font)));

        } else {
            table.addCell(new Cell().add(new Paragraph(p_image.getHref()).setFont(font)));

        }

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



}
