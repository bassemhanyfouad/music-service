package com.bold.sing.music.songmusic.boundary;

import com.bold.sing.music.songmusic.control.SongMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

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