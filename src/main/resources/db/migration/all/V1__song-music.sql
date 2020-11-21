create table song_music
(
    id              uuid not null,
    title           text not null,
    artist          text not null,
    cover_photo_url text,
    created_date    timestamptz,
    modified_date   timestamptz,
    version         bigint,
    primary key (id)
);

create table if not exists song_music_moods
(
    song_music_id uuid not null references song_music,
    mood          text not null,
    primary key (song_music_id, mood)
);

create table if not exists song_music_genres
(
    song_music_id uuid not null references song_music,
    genre         text not null,
    primary key (song_music_id, genre)
);