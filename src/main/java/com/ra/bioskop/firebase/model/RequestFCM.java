package com.ra.bioskop.firebase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestFCM {
    private String title;
    private String message;
    private String topic;
    private String token;
}
