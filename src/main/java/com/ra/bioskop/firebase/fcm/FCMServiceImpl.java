package com.ra.bioskop.firebase.fcm;

import com.google.firebase.messaging.*;
import com.ra.bioskop.firebase.model.RequestFCM;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class FCMServiceImpl implements FCMService {

    @Override
    public void topicNotification(RequestFCM requestFCM) {
        Message message = Message.builder()
                .setNotification(getNotification(requestFCM.getTitle(),
                        requestFCM.getMessage()))
                .setAndroidConfig(getAndroidConfig(requestFCM.getTopic()))
                .setTopic(requestFCM.getTopic())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Override
    public void tokenNotification(RequestFCM requestFCM) {
        Message message = Message.builder()
                .setNotification(getNotification(requestFCM.getTitle(),
                        requestFCM.getMessage()))
                .setAndroidConfig(getAndroidConfig(requestFCM.getTopic()))
                .setToken(requestFCM.getToken())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    private Notification getNotification(String title,
                                        String body) {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMillis(2).toMillis())
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(topic)
                        .build())
                .build();
    }
}
