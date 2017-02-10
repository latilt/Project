INSERT user (user_name) VALUES ('user1');
INSERT user (user_name) VALUES ('user2');

INSERT board (title, user_id) VALUES ('trello', 1);

INSERT lists (title, position, board_id) VALUES ('mike', 1, 1);
INSERT lists (title, position, board_id) VALUES ('bao', 2, 1);

INSERT card (title, position, board_id, lists_id) VALUES ('card1', 1, 1, 1);
INSERT card (title, position, board_id, lists_id) VALUES ('card2', 2, 1, 1);
INSERT card (title, position, board_id, lists_id) VALUES ('card3', 3, 1, 1);
INSERT card (title, position, board_id, lists_id) VALUES ('card4', 1, 1, 2);
INSERT card (title, position, board_id, lists_id) VALUES ('card5', 2, 1, 2);


INSERT board (title, user_id) VALUES ('naver', 2);

INSERT lists (title, position, board_id) VALUES ('list1', 1, 2);
INSERT lists (title, position, board_id) VALUES ('list2', 2, 2);
INSERT lists (title, position, board_id) VALUES ('list3', 3, 2);

INSERT card (title, position, board_id, lists_id) VALUES ('card1', 1, 2, 3);
INSERT card (title, position, board_id, lists_id) VALUES ('card2', 2, 2, 3);
INSERT card (title, position, board_id, lists_id) VALUES ('card3', 2, 2, 4);
INSERT card (title, position, board_id, lists_id) VALUES ('card4', 1, 2, 4);
INSERT card (title, position, board_id, lists_id) VALUES ('card5', 1, 2, 5);