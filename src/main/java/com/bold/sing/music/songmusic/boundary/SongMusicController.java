package com.bold.sing.music.songmusic.boundary;

import com.bold.sing.music.songmusic.control.SongMusicService;
import com.bold.sing.music.songmusic.entity.SongMusic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping
    public List<SongMusicDTO> getAllSongMusics() {
        return songMusicService.getAllSongMusics().stream().map(SongMusic::toDTO).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public SongMusicDTO fetchSongMusicById(@PathVariable("id") UUID songMusicId) {
        return songMusicService.fetchSongMusicById(songMusicId).toDTO();
    }
}