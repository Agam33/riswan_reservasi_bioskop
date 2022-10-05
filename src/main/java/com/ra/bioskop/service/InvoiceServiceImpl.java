package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.fileDB.FileDB;
import com.ra.bioskop.util.Constants;
import com.ra.bioskop.util.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Override
    public FileDB generateInvoice(String filename) throws JRException {

        // data dummy
        HashMap<String, Object> mp = new HashMap<>();
        mp.put("studioName", "A");
        mp.put("cstName", "Sophie Amundsen");
        mp.put("filmName", "Harry Potter Deathly Hallows (2007)");
        mp.put("date", LocalDate.now().toString());
        mp.put("time", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        mp.put("row", "A");
        mp.put("seatNo", "1");
        mp.put("invoiceNo", "invoice-" + Constants.randomIdentifier("Shopie")[4]);

        JasperReport jspReport = JasperUtil.setJasperReport("/invoice.jrxml");
        JasperPrint jspPrint = JasperUtil.setJasperPrint(jspReport, mp);

        FileDB fileDB = new FileDB();
        fileDB.setData(JasperUtil.toPdf(jspPrint));
        fileDB.setFileName(filename);
        fileDB.setFileType("application/pdf");

        return fileDB;
    }
}
