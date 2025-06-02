INSERT INTO tbl_user(user_id, user_pwd, user_name, user_role)
VALUES ('admin', '$2a$10$0XNNASU7TH10m.imEzdeUek4QoRnbdHsrcyQSfmVHwjD5QjPiQBUu', '관리자', 'ADMIN');

INSERT INTO tbl_user(user_id, user_pwd, user_name, user_role)
VALUES ('user01', '$2a$10$eF.9f3bNxi1KMfShz8cPi.tAcpqDYGsbotWQvOUiiv2Z7eRYHY60u', '일반인1', 'USER');

INSERT INTO tbl_user(user_id, user_pwd, user_name, user_role)
VALUES ('user02', '$2a$10$XOqpkZFnT1HvKhZzftjiEuVoQ9ony8MVJddKIA0VBe79lKs8zx0Du', '일반인2', 'USER');

COMMIT;