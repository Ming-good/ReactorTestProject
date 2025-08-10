CREATE SEQUENCE MEMBER_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE MEMBER (
                        MEMBER_ID BIGINT PRIMARY KEY,
                        USERNAME  VARCHAR(50),
                        AGE       INT
);
