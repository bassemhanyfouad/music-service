package com.bold.sing.music.file.control;

import com.bold.sing.music.exception.TechnicalException;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Data
@Builder
public class MultipartFileWrapper implements FileWrapper {
    private String contentType;
    private String originalFilename;
    private MultipartFile multipartFile;

    @Override
    public InputStream getInputStream() {
        if (multipartFile != null) {
            try {
                return multipartFile.getInputStream();
            } catch (IOException e) {
                throw new TechnicalException("error reading inputstream", e);
            }
        }
        throw new TechnicalException("MultipartFile was not set for this file: " + originalFilename);
    }

    public static MultipartFileWrapper from(MultipartFile multipartFile) {
        return MultipartFileWrapper.builder()
                .contentType(multipartFile.getContentType())
                .originalFilename(multipartFile.getOriginalFilename())
                .multipartFile(multipartFile)
                .build();
    }
}
