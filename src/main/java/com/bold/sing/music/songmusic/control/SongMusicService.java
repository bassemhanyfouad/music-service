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

import javax.ws.rs.NotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class SongMusicService {
    private final SongMusicRepository songMusicRepository;
    private final FileService fileService;

    @Transactional
    public SongMusic createSongMusic(SongMusicDTO songMusicDTO) {
        SongMusic songMusic = SongMusic.from(songMusicDTO);
        FileReference fileReference = fileService.fetchById(songMusicDTO.getFileReference().getId());
        FileReference coverPhoto = fileService.fetchById(songMusicDTO.getCoverPhoto().getId());
        songMusic.setFileReference(fileReference);
        songMusic.setCoverPhoto(coverPhoto);
        songMusicRepository.save(songMusic);
        return songMusic;
    }


    @Transactional(readOnly = true)
    public List<SongMusic> getAllSongMusics() {
        List<SongMusic> songMusics = songMusicRepository.findAll();
        songMusics.sort(Comparator.comparing(SongMusic::getCreatedDate).reversed());
        return songMusics;
    }

    @Transactional(readOnly = true)
    public SongMusic fetchSongMusicById(UUID id) {
        return songMusicRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}
