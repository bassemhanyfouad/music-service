package com.bold.sing.music.file.control;

import lombok.*;
import org.springframework.core.io.Resource;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class FileDownloadTO {

    @ToString.Exclude
    private Resource resource;
    private String mimeType;
    private String originalFileName;

}
