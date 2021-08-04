DROP TABLE IF EXISTS sample_board CASCADE;

CREATE TABLE sample_board
(
    id		    bigint          AUTO_INCREMENT,	-- 사용자 PK
    title		varchar(30)     NOT NULL,		-- 제목
    description	varchar(100),			        -- 내용
    PRIMARY KEY (id)
);