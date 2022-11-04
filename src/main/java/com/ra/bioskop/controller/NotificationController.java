package com.ra.bioskop.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.firebase.fcm.FCMServiceImpl;
import com.ra.bioskop.firebase.model.RequestFCM;
import com.ra.bioskop.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(Constants.NOTIFICATION_ENDPOINT)
public class NotificationController {

    private final FCMServiceImpl fcmService;

    public NotificationController(FCMServiceImpl fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/topic")
    public ResponseEntity<?> sendTopicNotification(@RequestBody RequestFCM requestFCM) {
        try {
            fcmService.topicNotification(requestFCM);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(),
                    new Date(), "success", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(null, new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<?> sendTokenNotification(@RequestBody RequestFCM requestFCM) {
        try {
            fcmService.tokenNotification(requestFCM);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(),
                    new Date(), "success", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(null, new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
