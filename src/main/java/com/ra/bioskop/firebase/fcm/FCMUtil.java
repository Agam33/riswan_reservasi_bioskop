package com.ra.bioskop.firebase.fcm;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Component;

@Component
public class FCMUtil {

    public Notification getNotification(String title,
                                        String body,
                                        String imgUrl) {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .setImage(imgUrl)
                .build();
    }

    public AndroidConfig getAndroidConfig(String title,
                                          String body,
                                          String setIcon,
                                          String setColor) {
        return AndroidConfig.builder()
                .setTtl(3600000)
                .setNotification(AndroidNotification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .setIcon(setIcon)
                        .setColor(setColor)
                        .build())
                .build();
    }

    public ApnsConfig getApnsConfig(String thread) {
        return ApnsConfig.builder()
                .setAps(Aps.builder()
                        .setBadge(42)
                        .setThreadId(thread)
                        .build())
                .build();
    }
}
