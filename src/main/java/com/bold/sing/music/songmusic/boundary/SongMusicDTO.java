package com.bold.sing.music.songmusic.boundary;


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
    private UUID fileReferenceId;

    @Builder.Default
    private List<SongLyricsLineDTO> songLyricsLines = new ArrayList<>();
}
