package com.bold.sing.music.songmusic.boundary;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Duration;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SongLyricsLineDTO {
    private String text;
    private Duration startTimeFromMusicBeginning;
}
