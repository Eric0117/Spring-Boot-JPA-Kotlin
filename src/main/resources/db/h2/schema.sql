CREATE TABLE IF NOT EXISTS member
(
    id uuid not null,
    username varchar(255),
    created_at datetime(6) not null,
    modified_at datetime(6) not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS post
(
    id uuid not null,
    content TEXT not null,
    title varchar(255) not null,
    writer_id uuid not null,
    created_at datetime(6) not null,
    modified_at datetime(6) not null,
    primary key (id)
)