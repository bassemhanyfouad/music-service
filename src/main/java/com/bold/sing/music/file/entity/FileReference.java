package com.bold.sing.music.file.entity;

import com.bold.sing.music.songmusic.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Duration;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "file_reference")
@Builder
@AllArgsConstructor
public class FileReference extends BaseEntity {
    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "storage_id")
    private String storageId;

    @Column(name = "original_file_name")
    private String originalFilename;

    @Column(name = "checksum")
    private String checkSum;

    @Column(name = "url")
    private String url;

    /**
     * only relevant for audio files
     */
    @Column(name = "duration")
    private Duration duration;

}
