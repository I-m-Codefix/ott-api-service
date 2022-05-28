create table tbl_account (
     id bigint not null,
     name varchar(255) not null,
     email varchar(255) not null,
     password varchar(255) not null,
     platform_type VARCHAR(10) DEFAULT 'IMCF' not null,
     profile_image varchar(255),
     is_auth CHAR(1) DEFAULT 'N' not null,
     access_token varchar(255),
     refresh_token varchar(255),
     created_date datetime,
     modified_date datetime,
     primary key (id)
)