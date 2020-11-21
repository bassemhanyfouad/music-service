package com.bold.sing.music.songmusic.entity;

import com.bold.sing.music.songmusic.boundary.SongLyricsLineDTO;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "song_lyrics_line")
@Builder
@AllArgsConstructor
public class SongLyricsLine extends BaseEntity {
    @Column(name = "text")
    private String text;

    @Column(name = "start_time_from_music_beginning")
    private Duration startTimeFromMusicBeginning;

    @Column(name = "end_time_from_music_beginning")
    private Duration endTimeFromMusicBeginning;

    @ManyToOne
    @JoinColumn(name = "song_music_id")
    private SongMusic songMusic;


    public static SongLyricsLine from(SongLyricsLineDTO songLyricsLineDTO) {
        return SongLyricsLine.builder()
                .startTimeFromMusicBeginning(songLyricsLineDTO.getStartTimeFromMusicBeginning())
                .endTimeFromMusicBeginning(songLyricsLineDTO.getStartTimeFromMusicBeginning())
                .text(songLyricsLineDTO.getText())
                .build();
    }

    public SongLyricsLineDTO toDTO() {
        return SongLyricsLineDTO.builder()
                .startTimeFromMusicBeginning(this.startTimeFromMusicBeginning)
                .text(this.text)
                .build();
    }
}
