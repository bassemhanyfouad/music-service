create table song_lyrics_line
(
    id                              uuid   not null,
    text                            text   not null,
    start_time_from_music_beginning bigint not null,
    end_time_from_music_beginning   bigint not null,
    song_music_id                   uuid   not null,
    created_date                    timestamptz,
    modified_date                   timestamptz,
    version                         bigint,
    primary key (id)
);
