alter table song_music
    add column file_reference_id uuid not null
        references file_reference;