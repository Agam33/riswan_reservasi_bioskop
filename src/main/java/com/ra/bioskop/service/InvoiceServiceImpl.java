package com.ra.bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Override
    public void generateInvoice() {

    }
}
