package com.ra.bioskop.util;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class JasperUtil {

    // #1
    public JasperReport setJasperReport(String pathFile) throws JRException {
        InputStream invoiceInputStream = JasperUtil.class.getResourceAsStream(pathFile);
        return JasperCompileManager.compileReport(invoiceInputStream);
    }

    // #2
    public JasperPrint setJasperPrint(JasperReport sourceFileName,
            Map<String, Object> parameters) throws JRException {
        return JasperFillManager.fillReport(sourceFileName, parameters);
    }

    // #3
    public byte[] toPdf(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
