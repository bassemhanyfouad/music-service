package com.bold.sing.music.songmusic.boundary;

import com.bold.sing.music.songmusic.entity.SongGenre;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/song-music-genres")
@RequiredArgsConstructor
public class SongGenresController {


    @GetMapping
    public List<String> getGenres(@RequestParam(value = "key", required = false) String key) {
        if (key != null && key.isEmpty()) {
            return Lists.newArrayList();
        }
        if (key != null) {
            return Arrays.stream(SongGenre.values())
                    .map(SongGenre::getDisplayName)
                    .filter(songMusicGenre -> songMusicGenre
                            .toLowerCase()
                            .contains(key.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Arrays.stream(SongGenre.values())
                .map(SongGenre::getDisplayName)
                .collect(Collectors.toList());
    }
}
