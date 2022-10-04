package com.ra.bioskop.dto.model.fileDB;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FileDB {
    private String fileName;
    private String fileType;
    private byte[] data;
}
