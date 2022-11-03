package com.ra.bioskop.controller;

import com.ra.bioskop.firebase.fcm.FCMService;
import com.ra.bioskop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.NOTIFICATION_ENDPOINT)
public class NotificationController {

    @Autowired
    private FCMService fcmService;

}
