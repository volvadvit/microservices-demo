create table if not exists conversation (
                              id serial primary key,
                              created_at timestamp,
                              name varchar(255) not null check (length(trim(name)) >= 2)
);

create table if not exists message (
                         id serial not null,
                         body varchar(2048),
                         created_at timestamp,
                         conversation_id bigint not null,
                         sender_id bigint not null,
                         primary key (id)
);

create table if not exists user_details (
                              id serial primary key,
                              name varchar(255)
);

create table if not exists user_conversations (
                                    user_id serial not null,
                                    conversation_id serial not null
);

alter table user_details add constraint user_details_username_unique unique (name);
alter table user_conversations add constraint convers_id foreign key (conversation_id) references conversation (id);
alter table user_conversations add constraint convers_user foreign key (user_id) references user_details (id);
alter table message add constraint message_convers_fk foreign key (conversation_id) references conversation (id);
alter table message add constraint message_send_fk foreign key (sender_id) references user_details (id);

alter sequence user_details_id_seq restart with 4;
alter sequence message_id_seq restart with 5;
alter sequence conversation_id_seq restart with 3;