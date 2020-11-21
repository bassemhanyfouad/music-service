package com.bold.sing.music.songmusic.entity;

import com.bold.sing.music.file.entity.FileReference;
import com.bold.sing.music.songmusic.boundary.SongMusicDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "song_music")
@Builder
@AllArgsConstructor
public class SongMusic extends BaseEntity {

    @Column(name = "cover_photo_url")
    private String coverPhotoUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "song_music_moods",
            joinColumns = @JoinColumn(name = "song_music_id"),
            uniqueConstraints = {@UniqueConstraint(name = "uc_song_music_moods",
                    columnNames = {"song_music_id", "mood"})})
    @Column(name = "mood")
    private Set<String> moods = new HashSet<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "song_music_genres",
            joinColumns = @JoinColumn(name = "song_music_id"),
            uniqueConstraints = {@UniqueConstraint(name = "uc_song_music_genres",
                    columnNames = {"song_music_id", "genre"})})
    @Column(name = "genre")
    private Set<String> genres = new HashSet<>();

    @Builder.Default
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "songMusic")
    private List<SongLyricsLine> songLyricsLines = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "file_reference_id")
    private FileReference fileReference;

    public static SongMusic from(SongMusicDTO songMusicDTO) {
        SongMusic songMusic = SongMusic.builder()
                .artist(songMusicDTO.getArtist())
                .genres(songMusicDTO.getGenres())
                .moods(songMusicDTO.getMoods())
                .title(songMusicDTO.getTitle())
                .build();
        songMusic.addSongLyricsLines(songMusicDTO.getSongLyricsLines().stream()
                .map(SongLyricsLine::from)
                .collect(Collectors.toList()));
        return songMusic;
    }

    public SongMusicDTO toDTO() {
        return SongMusicDTO.builder()
                .artist(artist)
                .genres(genres)
                .moods(moods)
                .title(title)
                .fileReferenceId(fileReference.getId())
                .songLyricsLines(songLyricsLines.stream()
                        .map(SongLyricsLine::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public void addSongLyricsLines(List<SongLyricsLine> songLyricsLines) {
        songLyricsLines.stream()
                .forEach(songLyricsLine -> songLyricsLine.setSongMusic(this));
        this.songLyricsLines.addAll(songLyricsLines);
    }
}
