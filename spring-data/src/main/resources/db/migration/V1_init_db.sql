create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 2 );

create table conversation (
                              id bigint not null,
                              created_at timestamp,
                              name varchar(255),
                              primary key (id)
);

create table message (
                         id bigint not null,
                         body varchar(2048),
                         created_at timestamp,
                         conversation_id bigint not null,
                         sender_id bigint not null,
                         primary key (id)
);

create table user_details (
                              id bigint not null,
                              name varchar(255),
                              primary key (id)
);

create table user_conversations (
                                    user_id bigint not null,
                                    conversation_id bigint not null
);

alter table user_details add constraint user_details_username_unique unique (name);
alter table user_conversations add constraint convers_id foreign key (conversation_id) references conversation (id);
alter table user_conversations add constraint convers_user foreign key (user_id) references user_details (id);
alter table message add constraint message_convers_fk foreign key (conversation_id) references conversation (id);
alter table message add constraint message_send_fk foreign key (sender_id) references user_details (id);