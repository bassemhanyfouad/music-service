create table file_reference
(
    id                 uuid not null,
    checksum           text not null,
    mime_type          text not null,
    original_file_name text not null,
    storage_id         text not null,
    url                text not null,
    created_date       timestamptz,
    modified_date      timestamptz,
    version            bigint,
    primary key (id)
);

