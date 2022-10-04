package com.ra.bioskop.controller;

import com.ra.bioskop.dto.model.fileDB.FileDB;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.service.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/download")
    public ResponseEntity<?> downloadInvoiceFile(@RequestParam String fileName) {
        try {
            FileDB fileDB = invoiceService.generateInvoice(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileDB.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename\"" + fileDB.getFileName() + "\"")
                    .body(new ByteArrayResource(fileDB.getData()));
        } catch (JRException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseError(HttpStatus.NO_CONTENT.value(), new Date(), "error"));
        }
    }
}
