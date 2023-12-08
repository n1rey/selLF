# selLF


VSCODE EXTENSIONS

1. Debugger for Java v0.55.0
2. Extension Pack for Java v0.25.15
3. Maven for Java
4. Language Support for Java(TM) by Red Hat v1.24.0
5. Project Manager for Java v0.23.2
6. Test Runner for Java v0.40.1
7. IntelliCode
8. IntelliCode API Usage Examples v0.2.8

OPTIONS
1. indent-rainbow : 들여쓰기 색깔
2. Night Owl : vscode 다크모드
3. Power Mode : 타자 입력 시 combo

RULES
1. 들여쓰기 : 스페이스 2번
2. 카멜표기법

java 1.8.0_291, Spring boot 2.6.2, PostgreSQL 15.5.1




SQL
CREATE TABLE IF NOT EXISTS sellf.tb_user
(
    user_no integer NOT NULL,
    user_id character(20) COLLATE pg_catalog."default" NOT NULL,
    user_password character(300) COLLATE pg_catalog."default" NOT NULL,
    user_email character(50) COLLATE pg_catalog."default" NOT NULL,
    user_name character(20) COLLATE pg_catalog."default" NOT NULL,
    user_profile_image character(100) COLLATE pg_catalog."default",
    user_join_date date,
    user_drop_date timestamp(6) with time zone,
    user_role character(10) COLLATE pg_catalog."default",
    user_login_date timestamp(6) with time zone,
    user_drop_yn "char" DEFAULT '0'::"char",
    user_join_time time(6) without time zone,
    CONSTRAINT tb_user_pkey PRIMARY KEY (user_no),
    CONSTRAINT tb_user_unique UNIQUE (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS sellf.tb_user
    OWNER to test;

GRANT ALL ON TABLE sellf.tb_user TO test;

----------------------------------------
CREATE TABLE IF NOT EXISTS sellf.tb_log
(
    log_no integer NOT NULL,
    log_type character(10) COLLATE pg_catalog."default" NOT NULL,
    log_ip character(50) COLLATE pg_catalog."default" NOT NULL,
    log_info text COLLATE pg_catalog."default" NOT NULL,
    log_user_no integer,
    log_drop_yn "char" DEFAULT '0'::"char",
    log_uri character(30) COLLATE pg_catalog."default",
    log_date date NOT NULL,
    log_time time(6) without time zone NOT NULL,
    CONSTRAINT tb_log_pkey PRIMARY KEY (log_no)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS sellf.tb_log
    OWNER to test;