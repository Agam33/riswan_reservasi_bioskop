package com.ra.bioskop.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRSaver;

import java.io.InputStream;
import java.util.HashMap;

public class JasperUtil {

    // #1
    public static JasperReport setJasperReport(String pathFile) throws JRException {
        InputStream ticketReportStream = JasperUtil.class.getResourceAsStream(pathFile);
        return JasperCompileManager.compileReport(ticketReportStream);
    }

    // #2
    public static JasperPrint setJasperPrint(JasperReport sourceFileName,
                                             HashMap<String, Object> parameters,
                                             JRBeanArrayDataSource dataSource) throws JRException {
        return JasperFillManager.fillReport(sourceFileName, parameters, dataSource);
    }

    // #2
    public static JasperPrint setJasperPrint(JasperReport sourceFileName,
                                             HashMap<String, Object> parameters) throws JRException {
        return JasperFillManager.fillReport(sourceFileName, parameters);
    }

    // #3
    public static byte[] toPdf(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public static void saveJasperReport(JasperReport jasperReport, String fileName) throws JRException {
        JRSaver.saveObject(jasperReport, fileName);
    }
}
