DROP TABLE IF EXISTS todo_model CASCADE;

CREATE TABLE todo_model
(
    id		    bigint        NOT NULL AUTO_INCREMENT,	-- 사용자 PK
    title		varchar(100)  NOT NULL,			        -- 제목
    PRIMARY KEY (id)
);