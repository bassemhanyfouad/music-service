package com.bold.sing.music.songmusic.boundary;

import com.bold.sing.music.songmusic.control.SongMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/song-musics")
@RequiredArgsConstructor
public class SongMusicController {
    private final SongMusicService songMusicService;

    @PostMapping
    public SongMusicDTO createSongMusic(@Valid @RequestBody SongMusicDTO songMusicDTO) {
        return songMusicService.createSongMusic(songMusicDTO).toDTO();
    }
}