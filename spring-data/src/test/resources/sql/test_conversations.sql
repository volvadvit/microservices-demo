insert into user_details (id, name)
values
    (1, 'pumba');

insert into conversation (id, created_at, name)
values
    (1, current_timestamp, 'Chat'),
    (2, current_timestamp, 'Trade');

insert into user_conversations (user_id, conversation_id)
values
    (1, 1),
    (1, 2);

insert into message (id, body, created_at, conversation_id, sender_id)
values
    (1, 'test message body', current_timestamp, 1, 1),
    (4, 'test message body new', current_timestamp, 2, 1);