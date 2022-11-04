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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final UserService userService;

    private final FilmService filmService;

    private final JasperUtil jasperUtil;

    public InvoiceServiceImpl(UserService userService, FilmService filmService, JasperUtil jasperUtil) {
        this.userService = userService;
        this.filmService = filmService;
        this.jasperUtil = jasperUtil;
    }

    @Override
    public FileDB generateInvoice(String filename) throws JRException {

        // data dummy
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("studioName", "A");
        parameters.put("cstName", "Sophie Amundsen");
        parameters.put("filmName", "Harry Potter Deathly Hallows (2007)");
        parameters.put("date", LocalDate.now().toString());
        parameters.put("time", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        parameters.put("row", "A");
        parameters.put("seatNo", "1");
        parameters.put("invoiceNo", "invoice-" + Constants.randomIdentifier("Sophie")[4]);

        JasperReport jspReport = jasperUtil.setJasperReport("/invoice/invoice.jrxml");
        JasperPrint jspPrint = jasperUtil.setJasperPrint(jspReport, parameters);

        FileDB fileDB = new FileDB();
        fileDB.setData(jasperUtil.toPdf(jspPrint));
        fileDB.setFileName(filename + "-invoice-" + Constants.randomIdentifier("Sophie")[4]);
        fileDB.setFileType("application/pdf");

        return fileDB;
    }
}
