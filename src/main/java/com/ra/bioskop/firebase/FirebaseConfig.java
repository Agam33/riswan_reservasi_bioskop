package com.ra.bioskop.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${app.firebase.file}")
    private String fbFile = "";

    @Primary
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        System.out.println(fbFile);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(
                        new ClassPathResource("/firebase/"+fbFile).getInputStream()))
                .build();
        if(FirebaseApp.getApps().isEmpty())
            FirebaseApp.initializeApp(options);
        return FirebaseApp.getInstance();
    }
}
