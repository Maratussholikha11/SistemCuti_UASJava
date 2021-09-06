package org.javajarvis.SistemCuti_UASJava.controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import org.dom4j.DocumentException;
import org.javajarvis.SistemCuti_UASJava.dto.ResponseData;
import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.model.PengajuanCuti;
import org.javajarvis.SistemCuti_UASJava.model.StatusCuti;
import org.javajarvis.SistemCuti_UASJava.repository.DetailPengajuanCutiRepository;
import org.javajarvis.SistemCuti_UASJava.repository.PengajuanCutiRepository;
import org.javajarvis.SistemCuti_UASJava.repository.StatusCutiRepository;
import org.javajarvis.SistemCuti_UASJava.service.DetailPengajuanCutiService;
import org.javajarvis.SistemCuti_UASJava.service.EmailService;
import org.javajarvis.SistemCuti_UASJava.service.PDFExporter;
import org.javajarvis.SistemCuti_UASJava.service.PengajuanCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pengajuancuti")
public class PengajuanCutiController {

    @Autowired
    private PengajuanCutiRepository repo;

    @Autowired
    private PengajuanCutiService service;


    @Autowired
    DetailPengajuanCutiService detailservice;

    @Autowired
    EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    StatusCutiRepository sr;

    @PostMapping
    public String save(@RequestBody PengajuanCuti pengajuanCuti) throws IOException, DocumentException {

        ResponseData<PengajuanCuti> response = new ResponseData<>();
//        PengajuanCuti pc  = modelMapper.map(pengajuanCuti, PengajuanCuti.class);
        StatusCuti s = sr.findById(1).get();
        System.out.println(s);
        pengajuanCuti.setStatusCuti(s);
        response.setPayload(service.save(pengajuanCuti));
        return "succes save";
    }

    @PutMapping("/cancel")
    public String cancel(@RequestBody PengajuanCuti pengajuanCuti) throws IOException, DocumentException {
        ResponseData<PengajuanCuti> response = new ResponseData<>();
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StatusCuti s = sr.findById(5).get();
        pengajuanCuti.setStatusCuti(s);
        String email = user.getEmail();
        String mailTo = "maratus.solikha55@gmail.com";
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        String text = "Bandung, " + currentDateTime + "\n\nPerihal    : Pembatalan izin cuti kerja\n\n" +
                "Kepada Yth, \nKepala PT PUB Jarvis\nDi tempat\n\nDengan hormat,\nYang bertanda tangan dibawah ini:\n\n" +
                "Nama    : " + user.getNamaLengkap() + "\nNIP       :" + user.getNip()+
                "\nJabatan  :" + user.getDivisi() + "\n\n" +
                "\nBermaksud membatalkan izin  cuti kerja  selama " + pengajuanCuti.getLamaCuti()  +
                " hari, yaitu pada tanggal (detail cuti)." +
                "\nDemikian pembatalan cuti saya sampaikan . Terimakasih atas perhatian Bapak/Ibu\n\n" +
                "Hormat Saya \n\n" + user.getNamaLengkap();
        response.setPayload(service.save(pengajuanCuti));
        emailService.sendEmail(mailTo,"Surat Pembatalan Cuti Kerja",text);

        return "succes save and send";
    }

    @PostMapping("/pdf")
    public void createPdf(HttpServletResponse response, @RequestBody PengajuanCuti pengajuanCuti) throws DocumentException, IOException {
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PengajuanCuti pc  = modelMapper.map(pengajuanCuti, PengajuanCuti.class);

        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        Paragraph alamat  = new Paragraph("Bandung, " + currentDateTime + "\nPerihal    : Permohonan izin cuti\n\n");
        Paragraph tujuan = new Paragraph("Kepada Yth, \nKepala PT PUB Jarvis\nDi tempat\n\nDengan hormat,\nYang bertanda tangan dibawah ini:\n\n");
        Paragraph employee = new Paragraph("Nama    : " + user.getNamaLengkap() + "\nNIP          :" + user.getNip()+
                "\nJabatan  :" + user.getDivisi() + "\n");
        Paragraph cuti = new Paragraph("\nBermaksud mengajukan jenis cuti selama " + pengajuanCuti.getLamaCuti()  +
                " hari, yaitu pada tanggal (detail cuti).");
        Paragraph penutup = new Paragraph("\nDemikian permohonan cuti ini saya ajukan. Terimakasih atas perhatian Bapak/Ibu\n\n");
        Paragraph hormat = new Paragraph("Hormat Saya \n\n" + user.getNamaLengkap());
        doc.add(alamat);
        doc.add(tujuan);
        doc.add(employee);
        doc.add(cuti);
        doc.add(penutup);
        doc.add(hormat);
        doc.close();

    }


//    @PutMapping
//    public PengajuanCuti update(@RequestBody PengajuanCuti pengajuanCuti){
//        return  service.save(pengajuanCuti);
//    }

    @DeleteMapping
    public void delete(@RequestBody Integer id){
        repo.deleteById(id);
    }

    @GetMapping
    public Iterable<PengajuanCuti> findAll(){
        return repo.findAll();
    }

    @GetMapping("/statuscuti/{id}")
    public List<PengajuanCuti> getPengajuanByStatusOpen(@PathVariable Integer id){
        return service.findByStatusCutiOpen(id);
    }

//    @GetMapping("/pdf")
//    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename = pengajuan" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        PDFExporter exporter = new PDFExporter ();
//        exporter.createPdf(response);
//
//    }

    @PutMapping("/send")
    public String send(@RequestBody PengajuanCuti pengajuanCuti) throws IOException, DocumentException {

        ResponseData<PengajuanCuti> response = new ResponseData<>();
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StatusCuti s = sr.findById(2).get();
        pengajuanCuti.setStatusCuti(s);
        String email = user.getEmail();
        String mailTo = "maratus.solikha55@gmail.com";
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        String text = "Bandung, " + currentDateTime + "\n\nPerihal    : Permohonan ijin cuti kerja\n\n" +
                "Kepada Yth, \nKepala PT PUB Jarvis\nDi tempat\n\nDengan hormat,\nYang bertanda tangan dibawah ini:\n\n" +
                "Nama    : " + user.getNamaLengkap() + "\nNIP      :" + user.getNip()+
                "\nJabatan  :" + user.getDivisi() + "\n\n" +
                "\nBermaksud mengajukan jenis cuti selama " + pengajuanCuti.getLamaCuti()  +
                " hari, yaitu pada tanggal (detail cuti)." +
                "\nDemikian permohonan cuti ini saya ajukan. Terimakasih atas perhatian Bapak/Ibu\n\n" +
                "Hormat Saya \n\n" + user.getNamaLengkap();

        response.setPayload(service.save(pengajuanCuti));
        emailService.sendEmail(mailTo,"Surat Permohonan Cuti ",text);
        return "succes send";
    }

    @PutMapping("/approve")
    public String approve(@RequestBody PengajuanCuti pengajuanCuti) throws IOException, DocumentException {

        ResponseData<PengajuanCuti> response = new ResponseData<>();
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StatusCuti s = sr.findById(3).get();
        pengajuanCuti.setStatusCuti(s);
        String email = user.getEmail();
        String mailTo = pengajuanCuti.getEmployee().getEmail();
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        String text = "Bandung, " + currentDateTime + "\n\nPerihal    : Konfirmasi izin cuti kerja\n\n" +
                "Kepada Yth, \nSaudara "+ user.getNamaLengkap() + "\nDi tempat\n\nMerujuk pada surat yang saudara ajukan, perihal permohonan izin cuti\n\n" +
                "Berkenaan dengan perihal tersebut kami atas nama HRD PT PUB Jarvis menyatakan 'MEMBERIKAN IZIN' atas permohonan izin cuti kerja selama" + pengajuanCuti.getLamaCuti() +
                " hari. " +
                "\nDemikian surat ini kami sampaikan agar digunakan sebagaimana mestinya. " +
                " HRD PT PUB Jarvis \n\n" + user.getNamaLengkap();

        response.setPayload(service.save(pengajuanCuti));
        emailService.sendEmail(mailTo,"Surat Konfirmasi Izin Cuti",text);
        return "succes send";
    }

    @PutMapping("/reject")
    public String reject(@RequestBody PengajuanCuti pengajuanCuti) throws IOException, DocumentException {

        ResponseData<PengajuanCuti> response = new ResponseData<>();
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StatusCuti s = sr.findById(5).get();
        pengajuanCuti.setStatusCuti(s);
        String email = user.getEmail();
        String mailTo = pengajuanCuti.getEmployee().getEmail();
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        String text = "Bandung, " + currentDateTime + "\n\nPerihal    : Konfirmasi izin cuti kerja\n\n" +
                "Kepada Yth, \nSaudara "+ user.getNamaLengkap() + "\nDi tempat\n\nMerujuk pada surat yang saudara ajukan, perihal permohonan izin cuti\n\n" +
                "Berkenaan dengan perihal tersebut kami atas nama HRD PT PUB Jarvis menyatakan 'MENOLAK ' atas permohonan izin cuti kerja selama" + pengajuanCuti.getLamaCuti() +
                " hari. " +
                "\nDemikian surat ini kami sampaikan agar digunakan sebagaimana mestinya. " +
                " HRD PT PUB Jarvis \n\n" + user.getNamaLengkap();

        response.setPayload(service.save(pengajuanCuti));
        emailService.sendEmail(mailTo,"Surat Konfirmasi Izin Cuti",text);
        return "succes send";
    }

}

