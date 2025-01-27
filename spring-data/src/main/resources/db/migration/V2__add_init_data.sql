insert into user_details (id, name)
values
    (1, 'peter'),
    (2, 'ivan'),
    (3, 'pumba');

insert into conversation (id, created_at, name)
values
    (1, current_timestamp, 'Chat'),
    (2, current_timestamp, 'Trade');

insert into user_conversations (user_id, conversation_id)
values
    (1, 1),
    (2, 1),
    (3, 1),
    (1, 2),
    (2, 2);

insert into message (id, body, created_at, conversation_id, sender_id)
values
    (1, 'test message body', current_timestamp, 1, 1),
    (2, 'for CHat test body message test text', current_timestamp, 1, 2),
    (3, 'SHALALALALALLALALALA BLALALALALAALAALLALL TRAATATTATATATATTATTAT skgjsgnsjkgngj jwnejfnwjej  jwj j. LKEAfLFK????/kfwelmk mm 234  mm km2 3m  k3m', current_timestamp, 1, 3),
    (4, 'test message body new', current_timestamp, 2, 1);