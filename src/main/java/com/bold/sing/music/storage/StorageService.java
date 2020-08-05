package com.bold.sing.music.storage;

import com.bold.sing.music.storage.StoragePage;
import com.bold.sing.music.storage.StorageResponse;
import lombok.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;

public interface StorageService {
    /**
     * Read and store the data of the input stream.
     * The input stream is closed a a result.
     *
     * @param is          the input stream.
     * @param contentType the content type of the data.
     * @return the optional response to the storing request, may be empty if the store operation failed
     */
    Optional<StorageResponse> store(InputStream is, String contentType);

    Optional<StorageResponse> store(byte[] data, String contentType);

    Optional<StorageResponse> store(MultipartFile file);

    InputStream retrieve(String storageId);

    StoragePage getStoragePageForDirectory(@NonNull String directory,
                                           @Nullable String nextPageToken);
}
