package com.ra.bioskop.controller;

import com.ra.bioskop.dto.model.fileDB.FileDB;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.service.InvoiceService;
import com.ra.bioskop.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(name = "Invoice")
@RestController
@RequestMapping(Constants.INVOICE_ENDPOINT)
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "Download invoice file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "berhasil download"),
            @ApiResponse(responseCode = "204", description = "tidak ada content",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class))}) })
    @GetMapping(value = "/download")
    public ResponseEntity<?> downloadInvoiceFile(@RequestParam("fileName") String fileName) {
        try {
            FileDB fileDB = invoiceService.generateInvoice(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileDB.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + fileDB.getFileName() + "\"")
                    .body(new ByteArrayResource(fileDB.getData()));
        } catch (JRException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseError(HttpStatus.NO_CONTENT.value(), new Date(), Constants.ERROR_MSG));
        }
    }
}
