package com.bold.sing.music.songmusic.boundary;


import com.bold.sing.music.file.boundary.FileReferenceDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SongMusicDTO {
    private String title;
    private String artist;
    private Set<String> moods;
    private Set<String> genres;
    private FileReferenceDTO fileReference;
    private FileReferenceDTO coverPhoto;

    @Builder.Default
    private List<SongLyricsLineDTO> songLyricsLines = new ArrayList<>();
}
