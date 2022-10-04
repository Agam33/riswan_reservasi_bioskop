package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.fileDB.FileDB;
import com.ra.bioskop.util.Constants;
import com.ra.bioskop.util.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Override
    public FileDB generateInvoice(String filename) throws JRException {

        HashMap<String, Object> mp = new HashMap<>();
        mp.put("studioName", "A");
        mp.put("cstName", "Shopie");
        mp.put("filmName", "Harry Potter");
        mp.put("date", "2022-10-10");
        mp.put("time", "12:00");
        mp.put("row", "A");
        mp.put("seatNo", "1");
        mp.put("invoiceNo", "invoice-" + Constants.randomIdentifier("Shopie")[4]);

        FileDB fileDB = new FileDB();

        JasperReport jsReport = JasperUtil.setJasperReport("/invoice.jrxml");
        JasperPrint jsPrint = JasperUtil.setJasperPrint(jsReport, mp);

        fileDB.setData(JasperUtil.toPdf(jsPrint));
        fileDB.setFileName(filename);
        fileDB.setFileType("application/pdf");

        return fileDB;
    }
}
