package com.ra.bioskop.firebase.fcm;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ra.bioskop.firebase.model.RequestFCM;

import java.util.concurrent.ExecutionException;

public interface FCMService {
    void topicNotification(RequestFCM requestFCM);

    void tokenNotification(RequestFCM requestFCM);
}
