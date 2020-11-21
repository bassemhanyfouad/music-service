package com.bold.sing.music.file.boundary;

import com.bold.sing.music.file.control.FileDownloadTO;
import com.bold.sing.music.file.control.FileService;
import com.bold.sing.music.file.control.FileWrapper;
import com.bold.sing.music.file.control.MultipartFileWrapper;
import com.bold.sing.music.file.entity.FileReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

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

    @GetMapping("{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") UUID fileId)  {
        FileDownloadTO fileDownloadTO = fileService.fetchFileById(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDownloadTO.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDownloadTO.getResource().getFilename() + "\"")
                .body(fileDownloadTO.getResource());
    }
}

