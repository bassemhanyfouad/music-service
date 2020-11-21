package com.bold.sing.music.file.control;

import com.bold.sing.music.exception.TechnicalException;
import com.bold.sing.music.file.entity.FileReference;
import com.bold.sing.music.file.entity.FileReferenceRepository;
import com.bold.sing.music.storage.StorageResponse;
import com.bold.sing.music.storage.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class FileService {
    private final StorageService storageService;
    private final FileReferenceRepository fileReferenceRepository;

    public FileReference uploadAndCreateFile(FileWrapper file) {
        String contentType = file.getContentType();
        Optional<StorageResponse> storageResponseOptional = uploadToStorage(file, contentType);
        if (storageResponseOptional.isPresent()) {
            StorageResponse storageResponse = storageResponseOptional.get();
            log.info("upload to storage successful. Response from storage: {} ", storageResponse);
            FileReference fileReference = FileReference.builder()
                    .checkSum(calculateCheckSum(file))
                    .mimeType(contentType)
                    .duration(storageResponse.getDuration())
                    .storageId(storageResponse.getStorageId())
                    .originalFilename(file.getOriginalFilename())
                    .url(storageResponse.getSelfLink())
                    .build();

            fileReferenceRepository.save(fileReference);
            return fileReference;
        }
        throw new TechnicalException("Upload Failed");
    }

    public FileDownloadTO fetchFileById(UUID fileId) {
        FileReference fileReference = fetchById(fileId);
        Optional<Resource> resourceOptional = fetchFileFromStorage(fileReference.getStorageId());
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();

            return new FileDownloadTO(resource, fileReference.getMimeType(), fileReference.getOriginalFilename());
        }
        throw new TechnicalException("Upload Failed");
    }

    public FileReference fetchById(UUID id) {
        Optional<FileReference> fileReferenceOptional = fileReferenceRepository.findById(id);
        return fileReferenceOptional.orElseThrow(NotFoundException::new);
    }

    /**
     * @return the response from storage or empty in case of an error
     */
    private Optional<StorageResponse> uploadToStorage(FileWrapper file, String contentType) {
        try {
            return storageService.store(
                    file, // stream is closed by com.google.cloud.storage.Storage.create(...)
                    contentType);
        } catch (Exception e) {
            // since this is also used in the erp use case we don't want to cause exceptions and abort the flow
            return Optional.empty();
        }
    }

    private Optional<Resource> fetchFileFromStorage(String storageId) {
        try {
            return Optional.of(storageService.retrieve(storageId));
        } catch (Exception e) {
            // since this is also used in the erp use case we don't want to cause exceptions and abort the flow
            return Optional.empty();
        }
    }

    private String calculateCheckSum(FileWrapper file) {
        String checkSum;
        try (InputStream is = file.getInputStream()) {
            checkSum = DigestUtils.md5DigestAsHex(is);
        } catch (IOException e) {
            throw new TechnicalException("Exception during checksum calculation", e);
        }
        return checkSum;
    }
}
