CREATE TABLE message
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    message VARCHAR(255),
    CONSTRAINT pk_message PRIMARY KEY (id)
);