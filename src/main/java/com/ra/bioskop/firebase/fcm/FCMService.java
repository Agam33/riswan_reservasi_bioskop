package com.ra.bioskop.firebase.fcm;

import com.ra.bioskop.firebase.model.RequestFCM;

public interface FCMService {
    void topicNotification(RequestFCM requestFCM);

    void tokenNotification(RequestFCM requestFCM);
}
