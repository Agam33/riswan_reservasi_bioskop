package com.ra.bioskop.firebase;

import com.google.firebase.FirebaseApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FirebaseConfigTest {

    @Autowired
    private FirebaseConfig firebaseConfig;

    @Test
    void testFirebaseApp() throws IOException {
        FirebaseApp app = firebaseConfig.firebaseApp();
        Assertions.assertNotNull(app);
    }
}