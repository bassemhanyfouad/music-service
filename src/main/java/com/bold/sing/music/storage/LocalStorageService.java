package com.bold.sing.music.storage;

import com.bold.sing.music.exception.TechnicalException;
import com.bold.sing.music.file.control.FileWrapper;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sound.sampled.AudioFileFormat;
import javax.ws.rs.NotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
//@ConditionalOnProperty(value = "", havingValue = "false")
@RequiredArgsConstructor
public class LocalStorageService implements StorageService {

    private final Environment environment;

    @Override
    @SneakyThrows
    public Optional<StorageResponse> store(FileWrapper file, String contentType) {
        return store(file.getOriginalFilename(), file.getInputStream(), contentType);
    }

    @Override
    @SneakyThrows
    public Optional<StorageResponse> store(String originalFilename, InputStream inputStream, String contentType) {

        // Normalize file name
        String fileName = StringUtils.cleanPath(originalFilename);
        try {

            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path fileStorageLocation = Paths.get(FileUtils.getUserDirectory().toURI())
                    .toAbsolutePath().normalize();
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            File createdFile = new File(targetLocation.toUri());

            Duration trackDuration = null;
            if (contentType.equals("audio/mpeg")) {
                MpegAudioFileReader mpegAudioFileReader = new MpegAudioFileReader();
                AudioFileFormat format = mpegAudioFileReader.getAudioFileFormat(createdFile);

                long seconds = (long) format.properties().get("duration") / 1000000;
                trackDuration = Duration.ofSeconds(seconds);
            }
            String port = environment.getProperty("local.server.port");

            return Optional.of(StorageResponse.builder()
                    .storageId(fileName)
                    // we need the full url here so that e.g. portal uploads also work with attachments
                    .selfLink("http://localhost:" + port + "/music-svc/api/attachments/" + fileName)
                    .duration(trackDuration)
                    .contentType(contentType)
                    .md5Checksum(DigestUtils.md5Hex(inputStream))
                    .build()
            );
        } catch (IOException ex) {
            throw new TechnicalException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    @SneakyThrows
    public Resource retrieve(String name) {
        try {
            File file = new File(FileUtils.getUserDirectory(), name);

            Path filePath = file.toPath();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new NotFoundException("File not found " + name);
            }
        } catch (MalformedURLException ex) {
            throw new NotFoundException("File not found " + name, ex);
        }
    }
}
