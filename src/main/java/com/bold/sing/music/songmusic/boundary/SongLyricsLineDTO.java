package com.bold.sing.music.songmusic.boundary;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SongLyricsLineDTO {
    private String text;
    private Long startTimeFromMusicBeginning;
}
