package com.bold.sing.music.file.boundary;

import com.bold.sing.music.file.entity.FileReference;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileReferenceDTO {

    private UUID id;
    private String mimeType;
    private String originalFilename;
    private String url;

    public static FileReferenceDTO from(FileReference fileReference) {
        return FileReferenceDTO.builder()
                .id(fileReference.getId())
                .mimeType(fileReference.getMimeType())
                .originalFilename(fileReference.getOriginalFilename())
                .url(fileReference.getUrl())
                .build();
    }
}
