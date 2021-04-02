alter table song_music
    add column cover_photo_id uuid
        references file_reference;

alter table song_music
    drop column cover_photo_url;