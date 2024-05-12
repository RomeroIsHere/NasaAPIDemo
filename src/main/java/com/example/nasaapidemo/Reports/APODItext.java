package com.example.nasaapidemo.Reports;

import com.example.nasaapidemo.Models.APOD;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import javax.print.Doc;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class APODItext {

    public void createPdf(String dest,List<APOD> p_Apod) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);


        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{0.03f,0.03f,0.03f,0.1f}))
                .useAllAvailableWidth();


        process(table, null, bold, true);

        for(APOD v_registro: p_Apod)
            process(table, v_registro, font, false);

        document.add(new Paragraph("APOD Service").setTextAlignment(TextAlignment.CENTER).setFont(bold));
        document.add(table);

        openFile(dest);
        //Close document
        document.close();
    }

    public void process(Table table, APOD p_apod, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("date").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("media type").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Service Vercion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("hdurl").setFont(font)));


        } else {
            table.addCell(new Cell().add(new Paragraph(p_apod.getDate()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(p_apod.getCveMedia().getName()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(p_apod.getServiceVersion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(p_apod.getHdUrl()).setFont(font)));

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
