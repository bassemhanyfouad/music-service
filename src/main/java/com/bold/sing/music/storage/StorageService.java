package com.bold.sing.music.storage;

import com.bold.sing.music.file.control.FileWrapper;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Optional;

public interface StorageService {

    @SneakyThrows
    Optional<StorageResponse> store(FileWrapper file, String contentType);

    @SneakyThrows
    Optional<StorageResponse> store(String originalFilename, InputStream inputStream, String contentType);

    Resource retrieve(String storageId);

}
