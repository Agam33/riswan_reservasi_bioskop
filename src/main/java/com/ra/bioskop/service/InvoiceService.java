package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.fileDB.FileDB;
import net.sf.jasperreports.engine.JRException;

public interface InvoiceService {

    FileDB generateInvoice(String fileName) throws JRException;
}
