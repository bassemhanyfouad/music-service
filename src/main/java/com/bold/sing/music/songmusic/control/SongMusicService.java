package com.bold.sing.music.songmusic.control;

import com.bold.sing.music.file.control.FileService;
import com.bold.sing.music.file.entity.FileReference;
import com.bold.sing.music.songmusic.boundary.SongMusicDTO;
import com.bold.sing.music.songmusic.entity.SongMusic;
import com.bold.sing.music.songmusic.entity.SongMusicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class SongMusicService {
    private final SongMusicRepository songMusicRepository;
    private final FileService fileService;

    @Transactional
    public SongMusic createSongMusic(SongMusicDTO songMusicDTO) {
        SongMusic songMusic = SongMusic.from(songMusicDTO);
        FileReference fileReference = fileService.fetchById(songMusicDTO.getFileReferenceId());
        songMusic.setFileReference(fileReference);
        songMusicRepository.save(songMusic);
        return songMusic;
    }

}
