package com.bold.sing.music.songmusic.boundary;

import com.bold.sing.music.file.control.FileService;
import com.bold.sing.music.file.control.FileWrapper;
import com.bold.sing.music.file.control.MultipartFileWrapper;
import com.bold.sing.music.file.entity.FileReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/files")
public class FilesController {
    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileReferenceDTO uploadFile(@RequestPart MultipartFile file) {
        FileWrapper fileWrapper = MultipartFileWrapper.builder()
                .contentType(file.getContentType())
                .multipartFile(file)
                .originalFilename(file.getOriginalFilename())
                .build();

        FileReference fileReference = fileService.uploadAndCreateFile(fileWrapper);
        return FileReferenceDTO.from(fileReference);
    }

}
