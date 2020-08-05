package com.bold.sing.music.storage;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
//@ConditionalOnProperty(value = "", havingValue = "false")
@RequiredArgsConstructor
public class LocalStorageService implements StorageService {

    private final Environment environment;

    @Override
    @SneakyThrows
    public Optional<StorageResponse> store(InputStream is, String contentType) {
        return store(IOUtils.toByteArray(is), null);
    }

    @Override
    @SneakyThrows
    public Optional<StorageResponse> store(byte[] data, String contentType) {

        String fileName = UUID.randomUUID().toString() + ".bin";
        File file = new File(FileUtils.getUserDirectory(), fileName);
        FileUtils.writeByteArrayToFile(file, data);

        log.info("################### Storing file to local filesystem path: {}" + file.getPath());

        // this is needed so that downloads also work in spring boot tests which use a random port
        // See https://stackoverflow.com/questions/30312058/spring-boot-how-to-get-the-running-port
        String port = environment.getProperty("local.server.port");
        return Optional.of(StorageResponse.builder()
                .storageId(fileName)
                // we need the full url here so that e.g. portal uploads also work with attachments
                .selfLink("http://localhost:" + port + "/music-svc/api/attachments/" + fileName)
                .contentType(contentType)
                .md5Checksum(DigestUtils.md5Hex(data))
                .build()
        );
    }

    @Override
    @SneakyThrows
    public Optional<StorageResponse>  store(MultipartFile file) {
        return store(file.getBytes(), file.getContentType());
    }

    @Override
    @SneakyThrows
    public InputStream retrieve(String name) {
        File file = new File(FileUtils.getTempDirectory(), name);
        return new FileInputStream(file);
    }

    @Override
    public StoragePage getStoragePageForDirectory(String directory, @Nullable String nextPageToken) {
        throw new UnsupportedOperationException("Retrieving a bucket page isn't supported yet locally.");
    }
}
