--! User Queries !--

INSERT INTO dopad_platform.user (id, username, password)
VALUES ('78cec5db-6396-4fd9-803f-1fd469d76330'::uuid, 'yellowisk', 'senha');

INSERT INTO dopad_platform.user (id, username, password)
VALUES ('78cec5db-6396-4fd9-803f-1fd469d77777'::uuid, 'blueisk', 'senha');

--! Page Queries !--
INSERT INTO dopad_platform.page (id, owner_id, title, text,
                                 status, change_code, upload_date,
                                 is_private)
VALUES ('21bf2268-ecd3-4f6c-85e9-2a1031530c0e'::uuid, '78cec5db-6396-4fd9-803f-1fd469d76330'::uuid,
        'Yellow Page',
        to_json('[{"text": "Line 1", "author": "yellowisk", "changeCode": "vS7pk2rYyvR1lWAav7g2SYvn3i0GWSVvZCrViadV48M=", "date": "2023-06-09T07:45:01Z"},
                  {"text": "Line 2", "author": "blueisk", "changeCode": "bTMKLH7dg4BesarRpyZo+NSu83BxkQ+gZBwDchDF9Xo=", "date": "2023-06-09T07:45:00Z"}
                  ]'::jsonb), 'SENT'::dopad_platform.page_status, 'vgwU4dvrLRfZgJgvQQ2nZnQQg95nAdDJJee+1C2KBXo=',
        '2023-06-09 07:45:00', false);

INSERT INTO dopad_platform.page (id, owner_id, title, text,
                                 status, change_code, upload_date,
                                 is_private)
VALUES ('7db864df-13a2-44f1-94d5-6949b2410405'::uuid, '78cec5db-6396-4fd9-803f-1fd469d77777'::uuid,
        'Blue Page',
        to_json('[{"text": "Line 1", "author": "blueisk", "changeCode": "Ee3vehoh11SHHFjA/5pkL1nNKQyBxa1M+fnnIu56OrE=", "date": "2023-06-09T09:24:00Z"},
                  {"text": "Line 2", "author": "yellowisk", "changeCode": "DaL1FqwFhV5wl3+XoI+Qoi150ITTbKvHAQUqs0TCkHY=", "date": "2023-06-09T10:45:00Z"}
                  ]'::jsonb), 'SENT'::dopad_platform.page_status, 'vgwU4dvrLRfZgJgvQQ2nZnQQg95nAdDJJee+1C2KBXo=',
        '2023-06-09 07:45:00', false);

--! Membership Queries !--
INSERT INTO dopad_platform.page_membership (id, user_id, page_id, status)
VALUES ('7f7c27ba-cb6f-4420-8c39-acfa4e69cf48'::uuid, '78cec5db-6396-4fd9-803f-1fd469d76330'::uuid,
        '21bf2268-ecd3-4f6c-85e9-2a1031530c0e'::uuid, 'ACCEPTED'::dopad_platform.page_membership_status);

INSERT INTO dopad_platform.page_membership (id, user_id, page_id, status)
VALUES ('b235d73d-05cf-4074-926b-fe18e72372b8'::uuid, '78cec5db-6396-4fd9-803f-1fd469d77777'::uuid,
        '21bf2268-ecd3-4f6c-85e9-2a1031530c0e'::uuid, 'PENDING'::dopad_platform.page_membership_status);