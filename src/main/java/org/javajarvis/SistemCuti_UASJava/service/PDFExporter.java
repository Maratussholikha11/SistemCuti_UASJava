package org.javajarvis.SistemCuti_UASJava.service;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.dom4j.DocumentException;
import org.javajarvis.SistemCuti_UASJava.model.PengajuanCuti;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PDFExporter {

    private PengajuanCuti p;

    public void createPdf(HttpServletResponse response) throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

//        Paragraph alamat  = new Paragraph("Bandung, " + currentDateTime + "\nPerihal    : Permohonan izin cuti\n\n");
//        Paragraph tujuan = new Paragraph("Kepada Yth, \nKepala PT PUB Jarvis\nDi tempat\nDengan hormat,\nYang bertanda tangan dibawah ini:\n\n");
//        Paragraph employee = new Paragraph("Nama    : " + p.getEmployee().getNamaLengkap() + "\nNIP :" + p.getEmployee().getNip()+
//                "\nJabatan  :" + p.getEmployee().getDivisi() + "\n");
//        doc.add(alamat);
//        doc.add(tujuan);
//        doc.add(employee);
        Paragraph p = new Paragraph("pdf");
        doc.add(p);
        doc.close();

    }
}
